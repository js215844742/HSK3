package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;

/**
 * 用户协议
 * Created by Administrator on 2018/3/22 0022.
 */

public class MineAboutAgreementActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_about_agreement);
        initView();
    }

    private void initView() {
        setTitle("");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
    }
}
