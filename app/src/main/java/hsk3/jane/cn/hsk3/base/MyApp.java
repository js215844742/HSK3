package hsk3.jane.cn.hsk3.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jane on 2018/3/9.
 */

public class MyApp extends Application {
    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
