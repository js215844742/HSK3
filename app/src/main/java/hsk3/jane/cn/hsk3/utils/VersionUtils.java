package hsk3.jane.cn.hsk3.utils;

import android.content.Context;
import android.content.pm.PackageManager;

import hsk3.jane.cn.hsk3.base.AppConfig;

/**
 * Created by Jane on 2018/4/16.
 */

public class VersionUtils {
    public static int getVerCode (Context context) {
        int versionCode = -1;
        try {
            versionCode = context.getPackageManager().getPackageInfo(AppConfig.BASE_PACKAGE, 0).versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVerName (Context context){
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(AppConfig.BASE_PACKAGE, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
