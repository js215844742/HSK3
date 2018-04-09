package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MyActivityManager;
import hsk3.jane.cn.hsk3.base.MyApplication;
import hsk3.jane.cn.hsk3.data.WordData;
import hsk3.jane.cn.hsk3.db.WordDBAdapter;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.DialogUtils;

/**
 * 个人中心*设置
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);
        initView();
    }

    private void initView() {
        setTitle("设置");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.view_data:
                AndroidUtils.startActivity(this, MineSettingWordsActivity.class, true);
                break;
            case R.id.view_theme:
                AndroidUtils.startActivity(this, MineSettingThemeActivity.class, true);
                break;
            case R.id.btn_exit:
                MyActivityManager.clearActivities();
                break;
        }
    }
}
