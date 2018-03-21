package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * 个人中心*帮助和反馈
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineHelpActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_help);
        initView();
    }

    private void initView() {
        setTitle("帮助与反馈");
        initToolbar((Toolbar)findViewById(R.id.toolbar));

    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.view_instruction:
                AndroidUtils.startActivity(this, MineHelpInstructionActivity.class, true);
                break;
            case R.id.view_question:
                AndroidUtils.startActivity(this, MineHelpQuestionActivity.class, true);
                break;
            case R.id.view_feedback:
                AndroidUtils.startActivity(this, MineHelpFeedbackActivity.class, true);
                break;
        }
    }
}
