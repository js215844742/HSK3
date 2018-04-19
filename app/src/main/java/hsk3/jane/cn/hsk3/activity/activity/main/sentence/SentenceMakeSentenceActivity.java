package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import hsk3.jane.cn.hsk3.data.SentenceData;
import hsk3.jane.cn.hsk3.data.SentenceMakeSentenceData;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.view.FluidLayout;

/**
 * 造句练习
 * Created by Jane on 2018/3/28.
 */

public class SentenceMakeSentenceActivity extends BaseActivity implements View.OnClickListener {
    private TextView positionTv, myAnswerTv, rightAnswerTv;
    private LinearLayout syntaxView;
    private FluidLayout questionView;
    private EditText answerEdt;
    private Button seeAnswerBtn, redoBtn, nextBtn;
    private LinearLayout answerView;

    private int index = 0;//所选句型序号
    private int position = 0;//当前所选题目的序号
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_make_sentence);
        index = getIntent().getIntExtra("index", 0);
        initView();
    }
    private void initView() {
        setTitle("造句练习-句法"+ (index+1));
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        syntaxView = findViewById(R.id.view_syntax);
        positionTv = findViewById(R.id.tv_position);
        myAnswerTv = findViewById(R.id.tv_my_answer);
        rightAnswerTv = findViewById(R.id.tv_right_answer);
        questionView = findViewById(R.id.view_timu);
        answerEdt = findViewById(R.id.edt_answer);
        seeAnswerBtn = findViewById(R.id.btn_see_answer);
        redoBtn = findViewById(R.id.btn_redo);
        nextBtn = findViewById(R.id.btn_next);
        answerView = findViewById(R.id.view_answer);

//        syntaxTv.setText(SentenceData.SYNTAX[index]);
        for (int i = 0; i < SentenceData.SYNTAX[index].length; i++) {
            TextView syntaxTv = new TextView(this);
            syntaxTv.setText(SentenceData.SYNTAX[index][i]);
            syntaxView.addView(syntaxTv);
        }
        if (SentenceMakeSentenceData.HANZIS[index].length>0) {
            positionTv.setText("第" + (position + 1) + "题");
            answerView.setVisibility(View.GONE);
            initData();
        }else{
            ((TextView) findViewById(R.id.tv_)).setText("这个句法没有句子练习。");
            answerEdt.setVisibility(View.GONE);
            answerView.setVisibility(View.GONE);
            seeAnswerBtn.setVisibility(View.GONE);
        }

        seeAnswerBtn.setOnClickListener(this);
        redoBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_see_answer:
                if (checkEdit()) {
                    answerEdt.setVisibility(View.GONE);
                    answerView.setVisibility(View.VISIBLE);
                    seeAnswerBtn.setVisibility(View.GONE);
                    redoBtn.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.VISIBLE);
                    checkAnswer();
                }else{
                    AndroidUtils.Toast(this, "请使用给出的词语造句");
                }
                break;
            case R.id.btn_redo:
                answerEdt.setText("");
                answerEdt.setVisibility(View.VISIBLE);
                positionTv.setText("第" + (position + 1) + "题");
                answerView.setVisibility(View.GONE);
                seeAnswerBtn.setVisibility(View.VISIBLE);
                redoBtn.setVisibility(View.GONE);
                nextBtn.setVisibility(View.GONE);
                break;
            case R.id.btn_next:
                if (position< SentenceMakeSentenceData.HANZIS[index].length-1) {
                    answerEdt.setText("");
                    position++;
                    answerEdt.setVisibility(View.VISIBLE);
                    positionTv.setText("第" + (position + 1) + "题");
                    answerView.setVisibility(View.GONE);
                    seeAnswerBtn.setVisibility(View.VISIBLE);
                    redoBtn.setVisibility(View.GONE);
                    nextBtn.setVisibility(View.GONE);
                    initData();
                }else{//做完了
                    showDiaolog();
                    nextBtn.setText("已完成");
//                    AndroidUtils.Toast(this, "最后一题");
                }
                break;
            case R.id.btn_1:
                Intent intent = new Intent(this, SentenceToneActivity.class);
                intent.putExtra("index", index);
                AndroidUtils.startActivity(this, intent, true);
                finish();
                break;
            case R.id.btn_2:
                finish();
                break;
        }
    }

    private void initData(){
        questionView.removeAllViews();
        for (int i = 0; i < SentenceMakeSentenceData.HANZIS[index][position].length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_question_view, null);
//            ((TextView) view.findViewById(R.id.tv_pinyin)).setText(SentenceQuestionData.PINYINS[index][position][i]);
            ((TextView) view.findViewById(R.id.tv_pinyin)).setText("");
            ((TextView) view.findViewById(R.id.tv_hanzi)).setText(SentenceMakeSentenceData.HANZIS[index][position][i]);
            questionView.addView(view);
        }
    }

    private void showDiaolog() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_dialog_finish, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ImageView img = view.findViewById(R.id.img_face);
        TextView tipTv = view.findViewById(R.id.tv_tip);
        Button btn1 = view.findViewById(R.id.btn_1);
        Button btn2 = view.findViewById(R.id.btn_2);
        tipTv.setText("恭喜你!\n你已经完成了句法"+ (index+1)+"的句子练习");
        btn1.setText("声调练习");
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        builder.setView(view);
        builder.setCancelable(false);
        builder.show();
    }

    private boolean checkEdit(){
        String myAnswer = answerEdt.getText().toString();
        for (int i = 0; i < SentenceMakeSentenceData.HANZIS[index][position].length; i++) {
            if (!myAnswer.contains(SentenceMakeSentenceData.HANZIS[index][position][i])) {
                return false;
            }
        }
        return true;
    }
    private void checkAnswer(){
        String myAnswer = answerEdt.getText().toString();
        myAnswerTv.setText(myAnswer);
        //TODO 造句匹配
        rightAnswerTv.setText("暂无评价");
        rightAnswerTv.setTextColor(getResources().getColor(R.color.red));
//        if (myAnswer.equals(SentenceMakeSentenceData.RIGHTANSWERS[index][position])){
//            myAnswerTv.setTextColor(getResources().getColor(R.color.green));
//        }else{
//            myAnswerTv.setTextColor(getResources().getColor(R.color.red));
//        }
    }
}
