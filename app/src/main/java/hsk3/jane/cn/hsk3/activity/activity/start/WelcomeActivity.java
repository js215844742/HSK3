package hsk3.jane.cn.hsk3.activity.activity.start;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

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
        handler.postDelayed(runnable,2000);
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            AndroidUtils.startActivity(WelcomeActivity.this,FixNameActivity.class,true);
        }
    };
}
