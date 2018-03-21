package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * 个人中心*个人信息
 * Created by Jane on 2018/3/21.
 */

public class MineInfoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        initView();
    }

    private void initView() {
        setTitle("个人信息");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.view_head:
                AndroidUtils.Toast(this, "换头像");
                break;
            case R.id.view_name:
                AndroidUtils.startActivity(this, MineInfoNameActivity.class, true);
                break;
            case R.id.view_gender:
                AndroidUtils.Toast(this, "选择性别");
                break;
        }
    }
}