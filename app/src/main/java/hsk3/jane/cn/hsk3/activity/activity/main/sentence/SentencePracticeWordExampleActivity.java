package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.data.SentenceData;
import hsk3.jane.cn.hsk3.utils.AudioUtils;

/**
 * Created by Jane on 2018/3/21.
 */

public class SentencePracticeWordExampleActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout syntaxView, exampleView;
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
        syntaxView = findViewById(R.id.view_syntax);
//        contextTv = findViewById(R.id.tv_context);
//        translationTv = findViewById(R.id.tv_translation);
//        translateBtn = findViewById(R.id.btn_translate);
        beginBtn = findViewById(R.id.btn_begin);
        exampleView = findViewById(R.id.view_example);
        initData();

//        translateBtn.setOnClickListener(this);
        beginBtn.setOnClickListener(this);
    }

    private void initData() {
        for (int i = 0; i < SentenceData.SYNTAX[index].length; i++) {
            TextView syntaxTv = new TextView(this);
            syntaxTv.setText(SentenceData.SYNTAX[index][i]);
            syntaxView.addView(syntaxTv);
        }
        for (int i = 0; i < SentenceData.EXAMPLE[index].length; i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.view_sentence_example, null);
            TextView lijuTv = view.findViewById(R.id.tv_liju);
            final TextView contextTv = view.findViewById(R.id.tv_context);
            ImageView playImg = view.findViewById(R.id.img_play);
            final TextView translationTv = view.findViewById(R.id.tv_translation);;
            TextView translateBtn = view.findViewById(R.id.btn_translate);;
            contextTv.setText(SentenceData.EXAMPLE[index][i]);
            translationTv.setText(SentenceData.EXAMPLE_TRANSLATION[index]);
            lijuTv.setText("例句"+(i+1)+"：");
            playImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AudioUtils.getInstance().init(SentencePracticeWordExampleActivity.this);
                    AudioUtils.getInstance().speakText(contextTv.getText().toString());
                }
            });
            translateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (translationTv.getVisibility() == View.GONE){
                        translationTv.setVisibility(View.VISIBLE);
                    }else{
                        translationTv.setVisibility(View.GONE);
                    }
                }
            });
            exampleView.addView(view);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.img_play:
//                AudioUtils.getInstance().init(this);
//                AudioUtils.getInstance().speakText(contextTv.getText().toString());
//                break;
//            case R.id.btn_translate:
//                if (translationTv.getVisibility() == View.INVISIBLE){
//                    translationTv.setVisibility(View.VISIBLE);
//                }else{
//                    translationTv.setVisibility(View.INVISIBLE);
//                }
//                break;
            case R.id.btn_begin:
                Intent intent = new Intent(SentencePracticeWordExampleActivity.this, SentencePracticeWordActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioUtils.getInstance().stopSpeak();
    }
}
