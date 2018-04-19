package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.SpUtils;

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
                SpUtils.saveIntPreference(MySpKey.SP_SETTING_THEME_KEY, 0);
                break;
            case R.id.view_theme_china://中国风主题
//                AndroidUtils.Toast(this, "中国风主题");
                Intent intent = new Intent(this, MineSettingThemeDetailActivity.class);
                intent.putExtra("title", "中国风");
                intent.putExtra("theme", 1);
                AndroidUtils.startActivity(this, intent, true);
                break;
        }
    }
}
