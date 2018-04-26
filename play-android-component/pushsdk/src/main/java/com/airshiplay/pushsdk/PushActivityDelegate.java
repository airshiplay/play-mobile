package com.airlenet.pushsdk;

import android.app.Activity;
import android.app.Fragment;
import android.app.TaskStackBuilder;
import android.app.assist.AssistContent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.WindowManager;

import com.airlenet.ui.BaseFragmentActivity;
import com.umeng.analytics.MobclickAgent;

import com.airlenet.ui.DelegateActivity;

/**
 * Created by lig on 16/8/10.
 */
public class PushActivityDelegate implements DelegateActivity {

    @Override
    public void onResume(BaseFragmentActivity activity) {
        MobclickAgent.onPageStart(activity.getClass().getSimpleName());
        MobclickAgent.onResume(activity);
    }
    @Override
    public void onPause(BaseFragmentActivity activity) {
        MobclickAgent.onPageEnd(activity.getClass().getSimpleName());
        MobclickAgent.onPause(activity);
    }

    @Override
    public void onPostResume() {

    }

    @Override
    public void onCreate(BaseFragmentActivity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onRestoreInstanceState(BaseFragmentActivity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onPostCreate(BaseFragmentActivity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onStart(BaseFragmentActivity activity) {

    }

    @Override
    public void onRestart(BaseFragmentActivity activity) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onSaveInstanceState(BaseFragmentActivity activity, Bundle outState) {

    }

    @Override
    public void onStop(BaseFragmentActivity activity) {

    }

    @Override
    public void onDestroy(BaseFragmentActivity activity) {

    }

    @Override
    public void onActivityResult(BaseFragmentActivity activity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onDetachedFromWindow(BaseFragmentActivity activity) {

    }

    @Override
    public void onAttachedToWindow(BaseFragmentActivity activity) {

    }

    @Override
    public void onBackPressed(BaseFragmentActivity activity) {

    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onAttachFragment(BaseFragmentActivity activity, Fragment fragment) {

    }

    @Override
    public void onConfigurationChanged(BaseFragmentActivity activity, Configuration newConfig) {

    }
}
