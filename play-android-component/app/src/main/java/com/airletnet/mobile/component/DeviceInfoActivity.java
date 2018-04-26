package com.airletnet.mobile.component;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.airlenet.component.PlayItemView;

/**
 * Created by lig on 2018/4/16.
 */

public class DeviceInfoActivity extends AppCompatActivity implements View.OnClickListener {
    View contentView;
    Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviceinfo);
        ((PlayItemView)findViewById(R.id.sys_version)).setValue(Build.VERSION.RELEASE);
        ((PlayItemView)findViewById(R.id.sys_brand)).setValue(Build.BRAND);
        ((PlayItemView)findViewById(R.id.sys_model)).setValue(Build.MODEL);
        ((PlayItemView)findViewById(R.id.sys_sdk)).setValue(Build.VERSION.SDK_INT+"");

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        displayMetrics.densityDpi;
        ((PlayItemView)findViewById(R.id.sys_density)).setValue(displayMetrics.density+"");
        ((PlayItemView)findViewById(R.id.sys_densityDpi)).setValue(displayMetrics.densityDpi+"");
        ((PlayItemView)findViewById(R.id.sys_scaledDensity)).setValue(displayMetrics.scaledDensity+"");
        ((PlayItemView)findViewById(R.id.sys_heightPixels)).setValue(displayMetrics.heightPixels+"");
        ((PlayItemView)findViewById(R.id.sys_widthPixels)).setValue(displayMetrics.widthPixels+"");

//        ((PlayItemView)findViewById(R.id.sys_contentHeight)).setOnClickListener(this);
          contentView = getWindow().getDecorView().findViewById(android.R.id.content);

          ;



    }


    @Override
    protected void onResume() {
        super.onResume();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((PlayItemView)findViewById(R.id.sys_actionBarHeight)).setValue(getSupportActionBar().getHeight()+"");
                ((PlayItemView)findViewById(R.id.sys_content)).setValue( "高度:"+contentView.getHeight()+"  宽度:"+contentView.getWidth()+"  顶部:"+contentView.getTop());
            }
        },2000);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sys_content:
                break;
        }
    }
}
