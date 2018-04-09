package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * 意见反馈
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineHelpFeedbackActivity extends BaseActivity {
    private TextView telTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_help_feedback);
        initView();
    }

    private void initView() {
        setTitle("联系我们");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
        telTv = findViewById(R.id.tv_tel);
    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.view_qq:
                break;
            case R.id.view_wechat:
                break;
            case R.id.view_email:
                break;
            case R.id.view_tel://打电话
                requestPermission(new String[]{Manifest.permission.CALL_PHONE}, new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telTv.getText().toString().trim()));
                        if (ActivityCompat.checkSelfPermission(MineHelpFeedbackActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            return;
                        }
                        startActivity(intent2);
                    }
                });
                break;
        }
    }
}
