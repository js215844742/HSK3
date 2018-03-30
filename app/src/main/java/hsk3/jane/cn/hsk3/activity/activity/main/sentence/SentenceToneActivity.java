package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.view.FluidLayout;

/**
 * 声调练习
 * Created by Jane on 2018/3/28.
 */

public class SentenceToneActivity extends BaseActivity {
    private FluidLayout fluidLayout;
    private int index;//句型序号
    private int position = 0;//当前序号
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_tone);
        index = getIntent().getIntExtra("index", 0);
        initView();
    }

    private void initView() {
        setTitle("声调练习-句法"+ (index+1));
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        fluidLayout = findViewById(R.id.view_fluid);
        fluidLayout.removeAllViews();

        View view = LayoutInflater.from(this).inflate(R.layout.item_question_tone, null);
//            ((TextView) view.findViewById(R.id.tv_pinyin)).setText(SentenceQuestionData.PINYINS[index][position][i]);
        ((TextView) view.findViewById(R.id.tv_pinyin)).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tv_hanzi)).setText("脑");
        fluidLayout.addView(view);

    }
}
