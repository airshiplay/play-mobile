package com.airlenet.pushsdk;

import android.app.Application;
import android.os.Build;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by lig on 16/8/10.
 */
public class PushSDK {


    public static void onCreate(Application application, boolean debug) {
        MobclickAgent.setDebugMode(debug);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(application, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }


    public enum Platform {
        HUAWEI, XIAOMI, SAMSUNG, OTHER
    }

    public Platform getPlatform() {

        //FINGERPRINT=Xiaomi/2013022/HM2013022:4.2.1/HM2013022/JHACNBA13.0:user/release-keys
        //MANUFACTURER=Xiaomi,BRAND=Xiaomi
        if ("xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            return Platform.XIAOMI;
        }
        if (Build.MANUFACTURER.toLowerCase().contains("huawei")) {
            return Platform.HUAWEI;
        }
        return Platform.OTHER;

    }
}
