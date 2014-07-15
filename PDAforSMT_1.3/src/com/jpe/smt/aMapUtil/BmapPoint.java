package com.jpe.smt.aMapUtil;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用于构造地图中的经纬度点
 * 
 * @author oywf
 * 
 */
public class BmapPoint implements Parcelable {
	private double lng;// 经度
	private double lat;// 纬度

	public BmapPoint(Parcel p) {
		lng = p.readDouble();
		lat = p.readDouble();
	}

	public BmapPoint(double lng, double lat) {
		this.lng = lng;
		this.lat = lat;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BmapPoint) {
			BmapPoint bmapPoint = (BmapPoint) obj;
			return (bmapPoint.getLng() == lng && bmapPoint.getLat() == lat) ? true
					: false;
		} else {
			return false;
		}
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public static final Parcelable.Creator<BmapPoint> CREATOR = new Parcelable.Creator<BmapPoint>() {
		public BmapPoint createFromParcel(Parcel p) {
			return new BmapPoint(p);
		}

		public BmapPoint[] newArray(int size) {
			return new BmapPoint[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(lat);
		dest.writeDouble(lng);
	}
}