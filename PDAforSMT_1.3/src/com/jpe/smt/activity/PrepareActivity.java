package com.jpe.smt.activity;
import com.jpe.smt.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;


/**
 * @author oywf
 * 显示主界面前的activity可以用来设置第一次登陆的动画 或者展示登陆界面
 */
public class PrepareActivity extends Activity
{

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.whcg_main_view);
    Intent intent =new Intent(PrepareActivity.this,SMT_MainActivity.class);
    startActivity(intent);
    finish();
  }
}