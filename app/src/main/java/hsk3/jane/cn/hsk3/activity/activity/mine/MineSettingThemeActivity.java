package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineSettingThemeActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting_theme);
        initView();
    }

    private void initView() {
        setTitle("主题");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.view_theme_moren://默认主题
                AndroidUtils.Toast(this, "暂时只开放默认主题，更多主题敬请期待！");
                break;
            case R.id.view_theme_china://中国风主题
                AndroidUtils.Toast(this, "中国风主题");
//                AndroidUtils.startActivity(this, MineSettingThemeActivity.class, true);
                break;
        }
    }
}
