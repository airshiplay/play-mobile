package com.airlenet.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lig on 16/8/8.
 */
public interface DelegateFragment {
    void onStart(BaseFragment fragment);

    void onPause(BaseFragment fragment);

    void onResume(BaseFragment fragment);

    void onStop(BaseFragment fragment);

    void onLowMemory();

    void onDestroyView(BaseFragment fragment);

    void onDestroy(BaseFragment fragment);

    void onDetach(BaseFragment fragment);

    void onConfigurationChanged(BaseFragment fragment, Configuration newConfig);

    void onSaveInstanceState(BaseFragment fragment, Bundle outState);

    void onViewStateRestored(BaseFragment fragment, @Nullable Bundle savedInstanceState);

    void onActivityCreated(BaseFragment fragment, Bundle savedInstanceState);

    void onRequestPermissionsResult(BaseFragment fragment, int requestCode, String[] permissions, int[] grantResults);

    void onActivityResult(BaseFragment fragment, int requestCode, int resultCode, Intent data);

    void onCreate(BaseFragment fragment, @Nullable Bundle savedInstanceState);

    void onCreateView(BaseFragment fragment, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void onViewCreated(BaseFragment fragment, View view, Bundle savedInstanceState);

    void onAttach(BaseFragment fragment, Context context);
}
