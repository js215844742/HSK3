package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;

/**
 * Created by Jane on 2018/3/20.
 */

public class SentenceStyleActivity extends BaseActivity {
    private Button styleBtn1, styleBtn2, styleBtn3, styleBtn4;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_style);
        initView();
    }

    private void initView() {
        setTitle("选择练习");
        initToolbar((Toolbar) findViewById(R.id.toolbar));
    }
}
