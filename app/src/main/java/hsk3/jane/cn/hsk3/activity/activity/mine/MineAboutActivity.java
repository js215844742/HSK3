package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.VersionUtils;
import hsk3.jane.cn.hsk3.view.CornersTransform;

/**
 * 个人中心*关于
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineAboutActivity extends BaseActivity {
    private TextView versionTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_about);
        initView();
    }

    private void initView() {
        setTitle("关于");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
        Glide.with(this).load(R.mipmap.logo).transform(new CornersTransform(this, 20)).into((ImageView) findViewById(R.id.img_logo));
        versionTv = findViewById(R.id.tv_version);
        versionTv.setText("当前版本："+VersionUtils.getVerName(this));
    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.tv_agreement:
                AndroidUtils.startActivity(this, MineAboutAgreementActivity.class, true);
                break;
        }
    }
}
