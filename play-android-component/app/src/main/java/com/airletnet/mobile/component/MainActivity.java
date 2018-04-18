package com.airletnet.mobile.component;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airletnet.mobile.svprogresshud.SVProgressHUD;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SVProgressHUD(this).showWithStatus("loading");
        startActivity(new Intent(getApplicationContext(),BasicActivity.class));
        startActivity(new Intent(getApplicationContext(),PullToRefreshActivity.class));
        startActivity(new Intent(getApplicationContext(),DeviceInfoActivity.class));
    }
}
