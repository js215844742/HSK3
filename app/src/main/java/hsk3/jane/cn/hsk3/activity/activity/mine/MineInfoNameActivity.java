package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.SpUtils;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineInfoNameActivity extends BaseActivity {
    private TextView saveTv;
    private EditText nameEdt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info_name);
        initView();
    }

    private void initView() {
        setTitle("个人信息");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
        saveTv = findViewById(R.id.tv_right);
        nameEdt = findViewById(R.id.edt_name);
        saveTv.setText("保存");
        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtils.saveStringPreference(MySpKey.SP_USER_NAME_KEY, nameEdt.getText().toString().trim());
                finish();
            }
        });
    }
}
