package com.airlenet.pushsdk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.airlenet.ui.BaseFragment;
import com.airlenet.ui.DelegateFragment;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by lig on 16/8/10.
 */
public class PushFragmentDelegate implements DelegateFragment {

    @Override
    public void onPause(BaseFragment fragment) {
        MobclickAgent.onPageEnd(fragment.getClass().getSimpleName());
    }

    @Override
    public void onResume(BaseFragment fragment) {
        MobclickAgent.onPageStart(fragment.getClass().getSimpleName());
    }

    @Override
    public void onStart(BaseFragment fragment) {

    }

    @Override
    public void onStop(BaseFragment fragment) {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onDestroyView(BaseFragment fragment) {

    }

    @Override
    public void onDestroy(BaseFragment fragment) {

    }

    @Override
    public void onDetach(BaseFragment fragment) {

    }

    @Override
    public void onConfigurationChanged(BaseFragment fragment, Configuration newConfig) {

    }

    @Override
    public void onSaveInstanceState(BaseFragment fragment, Bundle outState) {

    }

    @Override
    public void onViewStateRestored(BaseFragment fragment, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(BaseFragment fragment, Bundle savedInstanceState) {

    }

    @Override
    public void onRequestPermissionsResult(BaseFragment fragment, int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void onActivityResult(BaseFragment fragment, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onCreate(BaseFragment fragment, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onCreateView(BaseFragment fragment, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(BaseFragment fragment, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(BaseFragment fragment, Context context) {

    }
}
