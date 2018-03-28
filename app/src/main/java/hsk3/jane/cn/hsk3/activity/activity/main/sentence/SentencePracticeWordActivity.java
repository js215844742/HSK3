package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.data.SentenceQuestionData;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.AudioUtils;
import hsk3.jane.cn.hsk3.view.FluidLayout;

/**
 * Created by Jane on 2018/3/21.
 */

public class SentencePracticeWordActivity extends BaseActivity implements View.OnClickListener {

    private TextView positionTv, myAnswerTv, rightAnswerTv, rightAnswerTvTv, translationTv, translateBtn;
    private FluidLayout questionView;
    private EditText answerEdt;
    private ImageView playImg;
    private Button seeAnswerBtn, redoBtn, nextBtn;
    private LinearLayout answerView, rightAnswerView;

    private int index = 0;//所选句型序号
    private int position = 0;//当前所选题目的序号
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_practice_word);
        index = getIntent().getIntExtra("index", 0);
        initView();
    }

    private void initView() {
        setTitle("连词成句-句法"+ (index+1));
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        positionTv = findViewById(R.id.tv_position);
        myAnswerTv = findViewById(R.id.tv_my_answer);
        rightAnswerTv = findViewById(R.id.tv_right_answer);
        rightAnswerTvTv = findViewById(R.id.tv_right_answer_tv);
        translateBtn = findViewById(R.id.btn_translate);
        questionView = findViewById(R.id.view_timu);
        answerEdt = findViewById(R.id.edt_answer);
        playImg = findViewById(R.id.img_play);
        seeAnswerBtn = findViewById(R.id.btn_see_answer);
        redoBtn = findViewById(R.id.btn_redo);
        nextBtn = findViewById(R.id.btn_next);
        answerView = findViewById(R.id.view_answer);
        rightAnswerView = findViewById(R.id.view_right_answer);
        translationTv = findViewById(R.id.tv_translation);

        positionTv.setText("第"+(position+1)+"题");
        translationTv.setText(SentenceQuestionData.RIGHTANSWER_TRANSLATIONS[index][position]);
        rightAnswerTv.setText(SentenceQuestionData.RIGHTANSWERS[index][position]);
        answerView.setVisibility(View.GONE);
        initData();

        playImg.setOnClickListener(this);
        rightAnswerTvTv.setOnClickListener(this);
        translateBtn.setOnClickListener(this);
        seeAnswerBtn.setOnClickListener(this);
        redoBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_play:
                AudioUtils.getInstance().init(this);
                AudioUtils.getInstance().speakText(rightAnswerTv.getText().toString());
                break;
            case R.id.tv_right_answer_tv:
                if (rightAnswerView.getVisibility() == View.GONE){
                    rightAnswerView.setVisibility(View.VISIBLE);
                }else{
                    rightAnswerView.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_translate:
                if (translationTv.getVisibility() == View.INVISIBLE){
                    translationTv.setVisibility(View.VISIBLE);
                }else{
                    translationTv.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.btn_see_answer:
                answerEdt.setVisibility(View.GONE);
                answerView.setVisibility(View.VISIBLE);
                seeAnswerBtn.setVisibility(View.GONE);
                redoBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_redo:
                answerEdt.setVisibility(View.VISIBLE);
                positionTv.setText("第" + (position + 1) + "题");
                translationTv.setText(SentenceQuestionData.RIGHTANSWER_TRANSLATIONS[index][position]);
                rightAnswerTv.setText(SentenceQuestionData.RIGHTANSWERS[index][position]);
                answerView.setVisibility(View.GONE);
                seeAnswerBtn.setVisibility(View.VISIBLE);
                redoBtn.setVisibility(View.GONE);
                nextBtn.setVisibility(View.GONE);
                break;
            case R.id.btn_next:
                if (position<SentenceQuestionData.RIGHTANSWER_TRANSLATIONS[index].length-1) {
                    position++;
                    answerEdt.setVisibility(View.VISIBLE);
                    positionTv.setText("第" + (position + 1) + "题");
                    translationTv.setText(SentenceQuestionData.RIGHTANSWER_TRANSLATIONS[index][position]);
                    rightAnswerTv.setText(SentenceQuestionData.RIGHTANSWERS[index][position]);
                    answerView.setVisibility(View.GONE);
                    seeAnswerBtn.setVisibility(View.VISIBLE);
                    redoBtn.setVisibility(View.GONE);
                    nextBtn.setVisibility(View.GONE);
                    initData();
                }else{//做完了
                    AndroidUtils.Toast(this, "最后一题");
                }
                break;

        }
    }

    private void initData(){
        questionView.removeAllViews();
        for (int i = 0; i < SentenceQuestionData.HANZIS[index][position].length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_question_view, null);
//            ((TextView) view.findViewById(R.id.tv_pinyin)).setText(SentenceQuestionData.PINYINS[index][position][i]);
            ((TextView) view.findViewById(R.id.tv_pinyin)).setText("");
            ((TextView) view.findViewById(R.id.tv_hanzi)).setText(SentenceQuestionData.HANZIS[index][position][i]);
            questionView.addView(view);
        }
    }

}
