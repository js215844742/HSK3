package hsk3.jane.cn.hsk3.base;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechUtility;

import hsk3.jane.cn.hsk3.db.WordDBAdapter;

/**
 * Created by Jane on 2018/3/9.
 */

public class MyApplication extends Application {
    private static WordDBAdapter dbAdapter;
    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SpeechUtility.createUtility(getApplicationContext(), "appid=5aa88833");

        if (dbAdapter == null){
            dbAdapter = new WordDBAdapter(mContext);
            dbAdapter.open();
        }
    }

    public static WordDBAdapter getDbAdapter(){
        if (dbAdapter == null){
            dbAdapter = new WordDBAdapter(mContext);
            dbAdapter.open();
        }
        return dbAdapter;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (dbAdapter!=null){
            dbAdapter.close();
        }
    }
}
