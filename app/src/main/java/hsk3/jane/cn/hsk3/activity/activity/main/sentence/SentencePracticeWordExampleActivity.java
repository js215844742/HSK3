package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.data.SentenceData;
import hsk3.jane.cn.hsk3.utils.AudioUtils;

/**
 * Created by Jane on 2018/3/21.
 */

public class SentencePracticeWordExampleActivity extends BaseActivity implements View.OnClickListener {
    private TextView syntaxTv, contextTv, translationTv, translateBtn;
    private ImageView playImg;
    private Button beginBtn;

    private int index;//句型序号
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_practice_word_example);
        index = getIntent().getIntExtra("index", 0);
        initView();
    }

    private void initView() {
        setTitle("连词成句-句法"+ (index+1));
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        syntaxTv = findViewById(R.id.tv_syntax);
        contextTv = findViewById(R.id.tv_context);
        translationTv = findViewById(R.id.tv_translation);
        translateBtn = findViewById(R.id.btn_translate);
        playImg = findViewById(R.id.img_play);
        beginBtn = findViewById(R.id.btn_begin);

        syntaxTv.setText("句法公式："+ SentenceData.SYNTAX[index]);
        contextTv.setText(SentenceData.EXAMPLE[index]);
        translationTv.setText(SentenceData.EXAMPLE_TRANSLATION[index]);

        playImg.setOnClickListener(this);
        translateBtn.setOnClickListener(this);
        beginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_play:
                AudioUtils.getInstance().init(this);
                AudioUtils.getInstance().speakText(contextTv.getText().toString());
                break;
            case R.id.btn_translate:
                if (translationTv.getVisibility() == View.INVISIBLE){
                    translationTv.setVisibility(View.VISIBLE);
                }else{
                    translationTv.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.btn_begin:
                Intent intent = new Intent(SentencePracticeWordExampleActivity.this, SentencePracticeWordActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
                break;
        }
    }
}
