package hsk3.jane.cn.hsk3.activity.activity.start;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.activity.main.MainActivity;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.SpUtils;

/**
 * Created by Jane on 2018/3/7.
 */

public class WelcomeActivity extends BaseActivity{
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler = new Handler();
        handler.postDelayed(runnable,3000);
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (SpUtils.getStringPreference(MySpKey.SP_USER_NAME_KEY).length()>0) {
                AndroidUtils.startActivity(WelcomeActivity.this, MainActivity.class, true);
            }else{
                AndroidUtils.startActivity(WelcomeActivity.this, FixNameActivity.class, true);
            }
            finish();
        }
    };
}
