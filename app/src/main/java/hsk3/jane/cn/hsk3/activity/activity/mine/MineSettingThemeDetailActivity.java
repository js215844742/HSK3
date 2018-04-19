package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.SpUtils;

/**
 * Created by Jane on 2018/4/19.
 */

public class MineSettingThemeDetailActivity extends BaseActivity {
    private int theme;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting_theme_detail);
        theme = getIntent().getIntExtra("theme", 0);
        initView();
    }

    private void initView() {
        setTitle(getIntent().getStringExtra("title"));
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        findViewById(R.id.btn_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpUtils.saveIntPreference(MySpKey.SP_SETTING_THEME_KEY, theme);
                finish();
            }
        });
    }
}
