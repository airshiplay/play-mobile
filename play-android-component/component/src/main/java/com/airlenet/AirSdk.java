package com.airlenet;

import android.app.Application;

import com.facebook.stetho.Stetho;
//import com.facebook.stetho.okhttp3.StethoInterceptor;
//import com.squareup.picasso.Picasso;
//
//import okhttp3.OkHttpClient;

/**
 * Created by lig on 16/6/21.
 */
public class AirSdk {

    public static void onCreate(Application application){
        Stetho.initializeWithDefaults(application);
        Stetho.initialize(
                Stetho.newInitializerBuilder(application)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(application))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(application))
                        .build());
    }
}
