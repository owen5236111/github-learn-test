package com.jpe.smt.aMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.AMap.OnMapLongClickListener;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.jpe.smt.R;
import com.jpe.smt.aMapUtil.AMapUtil;
import com.jpe.smt.aMapUtil.BmapPoint;
import com.jpe.smt.aMapUtil.ToastUtil;
import com.jpe.smt.activity.SMT_MoreActivity;

/**
 * @author oywf 判断地理信息是否在工作区域内demo
 */
public class AroundActivity extends Activity implements LocationSource,
		AMapLocationListener, OnMapClickListener, OnMapLongClickListener,
		OnCameraChangeListener, OnGeocodeSearchListener {
	// 定位请求码
	public static int LOCATION_REQUEST_CODE = 101;

	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;

	// 设置一个金鹏集团的地理坐标
	private static LatLng jpeLocation = new LatLng(23.169494, 113.431587);
	// 点击地图的时候获取的坐标点
	private BmapPoint cPoint = null;
	// 地理信息编码查询
	private GeocodeSearch geocoderSearch;
	// 查询地理信息的进度条
	private ProgressDialog progDialog = null;
	// 点击地图查询出的街道的名称
	private String addressName = "";
	// 显示街道信息的view
	private TextView text_text;
	// 添加标记信号
	private final static int ADD_MARKER = 3000;

	// 设置一个handler 用于在标记上方显示街道地理信息
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == ADD_MARKER) {
				onClearMap();
				aMap.setMyLocationStyle(initLoctionIcon());
				aMap.addMarker(new MarkerOptions()
						.position(AMapUtil.convertToBMapPoint(cPoint))
						.icon(BitmapDescriptorFactory
								.fromBitmap(getViewBitmap(getView(addressName)))));

			}
		}
	};

	public View getView(String text) {
		View view = getLayoutInflater().inflate(R.layout.pop, null);
		text_text = (TextView) view.findViewById(R.id.text2);
		text_text.setText(text);
		return view;
	}

	/**
	 * 把一个view转化成bitmap对象
	 * */
	public static Bitmap getViewBitmap(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}

	// 以上用来编辑标记
	// ********************************************************************
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.location_activity);
		initialTitle();
		initBack();
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {

		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
	}

	/**
	 * 设置一些amap的属性 和地图点击监听事件（长按和短按）
	 */
	private void setUpMap() {
		aMap.setMyLocationStyle(initLoctionIcon());
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

		// 地图初始就7号级别显示金鹏电子的地理位置
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jpeLocation, 12));// 设置指定的可视区域地图
		aMap.setOnMapClickListener(this);// 对amap添加单击地图事件监听器
		aMap.setOnMapLongClickListener(this);// 对amap添加长按地图事件监听器
		aMap.setOnCameraChangeListener(this);// 对amap添加移动地图事件监听器

		// 对逆地理查询进行初始化
		geocoderSearch = new GeocodeSearch(this);
		geocoderSearch.setOnGeocodeSearchListener(this);
		progDialog = new ProgressDialog(this);

	}

	/**
	 * 初始化定位小蓝点
	 */
	public MyLocationStyle initLoctionIcon() {
		// 自定义系统定位小蓝点
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.icon_geo));// 设置小蓝点的图标
		myLocationStyle.strokeColor(Color.TRANSPARENT);// 设置圆形的边框颜色
		myLocationStyle.radiusFillColor(Color.TRANSPARENT);// 设置圆形的填充颜色为透明
		// myLocationStyle.anchor(int,int)//设置小蓝点的锚点
		// myLocationStyle.strokeWidth(0.0f);// 设置圆形的边框粗细

		return myLocationStyle;
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * 此方法已经废弃
	 */
	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		if (mListener != null && aLocation != null) {
			mListener.onLocationChanged(aLocation);// 显示系统小蓝点
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			/*
			 * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
			 * API定位采用GPS和网络混合定位方式
			 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
			 */
			mAMapLocationManager.requestLocationUpdates(
					LocationProviderProxy.AMapNetwork, 2000, 10, this);
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destory();
		}
		mAMapLocationManager = null;
	}

	// *********************以下是地图上面点击或者拖动的事件监听处理**************************************
	/**
	 * 对正在移动地图事件回调
	 */
	@Override
	public void onCameraChange(CameraPosition cameraPosition) {
	}

	/**
	 * 对移动地图结束事件回调
	 */
	@Override
	public void onCameraChangeFinish(CameraPosition cameraPosition) {

	}

	/**
	 * 对长按地图事件回调
	 */
	@Override
	public void onMapLongClick(LatLng point) {
		// 逆地理编码查询位置
		cPoint = new BmapPoint(point.longitude, point.latitude);
		getAddress(AMapUtil.convertToLatLonPoint(point));

	}

	/*
	 * 对单击地图事件回调
	 */
	@Override
	public void onMapClick(LatLng point) {
	}

	// ***************************地理查询******************************
	/**
	 * 清空地图上所有已经标注的marker
	 */
	public void onClearMap() {
		if (AMapUtil.checkReady(this, aMap)) {
			aMap.clear();
		}
	}

	/**
	 * 显示进度条对话框
	 */
	public void showDialog() {
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setIndeterminate(false);
		progDialog.setCancelable(true);
		progDialog.setMessage("正在获取地址");
		progDialog.show();
	}

	/**
	 * 隐藏进度条对话框
	 */
	public void dismissDialog() {
		if (progDialog != null) {
			progDialog.dismiss();
		}
	}

	/**
	 * 响应地理编码(这个方法 此demo目前不用)
	 */
	public void getLatlon(final String name) {
		showDialog();
		GeocodeQuery query = new GeocodeQuery(name, "010");// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
		geocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
	}

	/**
	 * 响应逆地理编码
	 */
	public void getAddress(final LatLonPoint latLonPoint) {
		showDialog();
		RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
				GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
		geocoderSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
	}

	/**
	 * 地理编码查询回调
	 */
	@Override
	public void onGeocodeSearched(GeocodeResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * 逆地理编码回调
	 */
	@Override
	public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
		dismissDialog();
		if (rCode == 0) {
			if (result != null && result.getRegeocodeAddress() != null
					&& result.getRegeocodeAddress().getFormatAddress() != null) {
				addressName = result.getRegeocodeAddress().getFormatAddress()
						+ "附近";
				handler.sendMessage(Message.obtain(handler, ADD_MARKER));
			} else {
				ToastUtil.show(AroundActivity.this, "未找到结果！");
			}
		} else if (rCode == 27) {
			ToastUtil.show(AroundActivity.this, "错误的网络连接！");
		} else if (rCode == 32) {
			ToastUtil.show(AroundActivity.this, "key错误!");
		} else {
			ToastUtil.show(AroundActivity.this, "其它未知错误!");
		}

	}

	// 设置界面标题栏目
	private void initialTitle() {
		((TextView) findViewById(R.id.middle_title_text)).setText("地图定位");
		Button moreinfo = ((Button) findViewById(R.id.personal_info_bt));
		moreinfo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Intent localIntent = new Intent(AroundActivity.this,
						SMT_MoreActivity.class);
				AroundActivity.this.startActivity(localIntent);
			}
		});
	}

	// 设置确认返回按钮
	private void initBack() {
		Button back = ((Button) findViewById(R.id.item_bar_topic_type));
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				// 判断addressName 是否为空 不为空就返回
				if (!"".equals(addressName) && addressName != null) {
					Intent intent = new Intent();
					intent.putExtra("addressName", addressName);
					intent.putExtra("point", cPoint);
					setResult(LOCATION_REQUEST_CODE, intent);

					AroundActivity.this.finish();
				} else {
					ToastUtil.show(AroundActivity.this, "街道地址不能为空，请重新长按选择！");
				}
			}
		});
	}

}
