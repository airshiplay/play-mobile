package com.airletnet.mobile.component;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by lig on 2018/3/30.
 */

public class BasicActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_basic);
        findViewById(R.id.item_1).setOnClickListener(this);
        findViewById(R.id.item_2).setOnClickListener(this);
        findViewById(R.id.item_deviceInfo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_deviceInfo:
                startActivity(new Intent(getApplicationContext(),DeviceInfoActivity.class));
                break;
        }
    }
}
