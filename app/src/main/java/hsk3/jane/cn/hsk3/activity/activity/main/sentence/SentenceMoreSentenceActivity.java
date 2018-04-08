package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import hsk3.jane.cn.hsk3.data.SentenceMoreSentenceData;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.AudioUtils;

/**
 * 更多句子练习
 * Created by Administrator on 2018/3/24 0024.
 */

public class SentenceMoreSentenceActivity extends BaseActivity implements View.OnClickListener {
    private TextView contextTv, translationTv, translateBtn, positionTv;
    private LinearLayout syntaxView;
    private ImageView playSlowImg, playNormalImg;
    private Button nextBtn;

    private int index;//句型序号
    private int position = 0;//当前所选题目的序号
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_more_sentence);
        index = getIntent().getIntExtra("index", 0);
        initView();
    }
    private void initView() {
        setTitle("句子练习-句法"+ (index+1));
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        syntaxView = findViewById(R.id.view_syntax);
        contextTv = findViewById(R.id.tv_context_hanzi);
        translationTv = findViewById(R.id.tv_translation);
        translateBtn = findViewById(R.id.btn_translate);
        playSlowImg = findViewById(R.id.img_play_slow);
        playNormalImg = findViewById(R.id.img_play_normal);
        nextBtn = findViewById(R.id.btn_next);
        positionTv = findViewById(R.id.tv_position);

        playSlowImg.setOnClickListener(this);
        playNormalImg.setOnClickListener(this);
        translateBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

//        syntaxTv.setText(SentenceData.SYNTAX[index]);
        for (int i = 0; i < SentenceData.SYNTAX[index].length; i++) {
            TextView syntaxTv = new TextView(this);
            syntaxTv.setText(SentenceData.SYNTAX[index][i]);
            syntaxView.addView(syntaxTv);
        }
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_play_slow:
                AudioUtils.getInstance().init(this, 0);
                AudioUtils.getInstance().speakText(contextTv.getText().toString());
                break;
            case R.id.img_play_normal:
                AudioUtils.getInstance().init(this);
                AudioUtils.getInstance().speakText(contextTv.getText().toString());
                break;
            case R.id.btn_next:
                if (position<SentenceMoreSentenceData.SENTENCES_CHINESE[index].length-1){
                    position ++ ;
                    AudioUtils.getInstance().stopSpeak();
                    initData();
                }else{
                    showDiaolog();
                    nextBtn.setText("已完成");
                }
                break;
            case R.id.btn_translate:
                if (translationTv.getVisibility() == View.GONE){
                    translationTv.setVisibility(View.VISIBLE);
                }else{
                    translationTv.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_1:
                Intent intent = new Intent(this, SentenceMakeSentenceActivity.class);
                intent.putExtra("index", index);
                AndroidUtils.startActivity(this, intent, true);
                finish();
                break;
            case R.id.btn_2:
                finish();
                break;
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
        btn1.setText("造句练习");
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        builder.setView(view);
        builder.setCancelable(false);
        builder.show();
    }

    private void initData(){
        if (SentenceMoreSentenceData.SENTENCES_CHINESE[index].length<=0){
            contextTv.setText("这个句法没有句子练习。");
            positionTv.setText("");
            nextBtn.setVisibility(View.GONE);
            translateBtn.setVisibility(View.GONE);
            playNormalImg.setVisibility(View.GONE);
            playSlowImg.setVisibility(View.GONE);
        }else {
            positionTv.setText("第" + (position + 1) + "题");
            contextTv.setText(SentenceMoreSentenceData.SENTENCES_CHINESE[index][position]);
            translationTv.setText(SentenceMoreSentenceData.SENTENCES_ENGLISH[index][position]);
            translationTv.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioUtils.getInstance().stopSpeak();
    }
}
