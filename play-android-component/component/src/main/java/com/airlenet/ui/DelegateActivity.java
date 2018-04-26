package com.airlenet.ui;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by lig on 16/8/8.
 */
public interface DelegateActivity {


    public void onCreate(BaseFragmentActivity activity, Bundle savedInstanceState);

    public void onStart(BaseFragmentActivity activity);

    public void onPause(BaseFragmentActivity activity);

    public void onStop(BaseFragmentActivity activity);

    public void onRestart(BaseFragmentActivity activity);

    public void onResume(BaseFragmentActivity activity);

    public void onSaveInstanceState(BaseFragmentActivity activity, Bundle outState);

    public void onDestroy(BaseFragmentActivity activity);

    public void onActivityResult(BaseFragmentActivity activity, int requestCode, int resultCode, Intent data);

    public void onPostResume();

    public void onRestoreInstanceState(BaseFragmentActivity activity, Bundle savedInstanceState);

    public void onPostCreate(BaseFragmentActivity activity, Bundle savedInstanceState);

    public void onNewIntent(Intent intent);

    public void onDetachedFromWindow(BaseFragmentActivity activity);


    public void onAttachedToWindow(BaseFragmentActivity activity);


    public void onBackPressed(BaseFragmentActivity activity);


    public void onTrimMemory(int level);


    public void onLowMemory();


    public void onAttachFragment(BaseFragmentActivity activity, Fragment fragment);


    public void onConfigurationChanged(BaseFragmentActivity activity, Configuration newConfig);
}
