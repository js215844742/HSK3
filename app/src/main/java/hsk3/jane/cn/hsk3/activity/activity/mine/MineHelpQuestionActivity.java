package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.adapter.MineHeleQuestionAdapter;
import hsk3.jane.cn.hsk3.base.BaseActivity;

/**
 * 常见问题解答
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineHelpQuestionActivity extends BaseActivity {
    private ExpandableListView expandableListView;
    private MineHeleQuestionAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_help_question);
        initView();
    }

    private void initView() {
        setTitle("常见问题");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
        expandableListView = findViewById(R.id.expandableListView);
        adapter = new MineHeleQuestionAdapter(this);
        expandableListView.setAdapter(adapter);
    }
}
