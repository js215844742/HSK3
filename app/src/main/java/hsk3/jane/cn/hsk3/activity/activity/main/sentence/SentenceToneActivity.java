package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.annotation.SuppressLint;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.data.SentenceToneData;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.view.FluidLayout;

/**
 * 声调练习
 * Created by Jane on 2018/3/28.
 */

public class SentenceToneActivity extends BaseActivity implements View.OnClickListener {
    private GestureLibrary gLib;
    private FluidLayout fluidLayout;
    private int index;//句型序号
    private int position = 0;//当前序号
    private Button seeAnswerBtn, redoBtn, nextBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_tone);
        index = getIntent().getIntExtra("index", 0);
        initView();
        gLib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        gLib.load();
    }

    private void initView() {
        setTitle("声调练习-句法"+ (index+1));
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        fluidLayout = findViewById(R.id.view_fluid);

        seeAnswerBtn = findViewById(R.id.btn_see_answer);
        redoBtn = findViewById(R.id.btn_redo);
        nextBtn = findViewById(R.id.btn_next);

        seeAnswerBtn.setOnClickListener(this);
        redoBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        initData(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_see_answer:
                seeAnswerBtn.setVisibility(View.GONE);
                redoBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                initData(true);
                break;
            case R.id.btn_redo:
                seeAnswerBtn.setVisibility(View.VISIBLE);
                redoBtn.setVisibility(View.GONE);
                nextBtn.setVisibility(View.GONE);
                initData(false);
                break;
            case R.id.btn_next:
                if (position< SentenceToneData.HANZIS[index].length-1) {
                    position++;
                    seeAnswerBtn.setVisibility(View.VISIBLE);
                    redoBtn.setVisibility(View.GONE);
                    nextBtn.setVisibility(View.GONE);
                    initData(false);
                }else{
                    showDiaolog();
                    nextBtn.setText("已完成");
//                    AndroidUtils.Toast(this, "最后一题");
                }
                break;
            case R.id.btn_2:
                finish();
                break;
        }
    }

    private void initData(boolean seeAnswer) {
        fluidLayout.removeAllViews();
        String strings [] = SentenceToneData.HANZIS[index][position].split("");
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            if (string.length()>0) {
                View view = LayoutInflater.from(this).inflate(R.layout.item_question_tone, null);
//            ((TextView) view.findViewById(R.id.tv_pinyin)).setText(SentenceQuestionData.PINYINS[index][position][i]);
                ((TextView) view.findViewById(R.id.tv_pinyin)).setVisibility(View.GONE);
                ((TextView) view.findViewById(R.id.tv_hanzi)).setText(string);
                GestureOverlayView gestureView = view.findViewById(R.id.view_gesture);
                final ImageView toneImg = view.findViewById(R.id.img_tone);
                if (!seeAnswer) {
                    gestureView.setVisibility(View.VISIBLE);
                    gestureView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
                        @Override
                        public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                            ArrayList<Prediction> predictions = gLib.recognize(gesture);
                            if (predictions.size() > 0 && predictions.get(0).score > 8.0) {
                                String result = predictions.get(0).name;
                                if (result.equals("0")) {
                                    Glide.with(SentenceToneActivity.this).load(0).into(toneImg);
                                } else if (result.equals("1")) {
                                    Glide.with(SentenceToneActivity.this).load(R.mipmap.img_tone_1).into(toneImg);
                                } else if (result.equals("2")) {
                                    Glide.with(SentenceToneActivity.this).load(R.mipmap.img_tone_2).into(toneImg);
                                } else if (result.equals("3")) {
                                    Glide.with(SentenceToneActivity.this).load(R.mipmap.img_tone_3).into(toneImg);
                                } else if (result.equals("4")) {
                                    Glide.with(SentenceToneActivity.this).load(R.mipmap.img_tone_4).into(toneImg);
                                } else {
                                    AndroidUtils.Toast(SentenceToneActivity.this, "未识别");
                                }
                            }
                        }
                    });
                }else{
                    gestureView.setVisibility(View.INVISIBLE);
                }
                fluidLayout.addView(view);
            }
        }
    }

    private void showDiaolog() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_dialog_finish, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ImageView img = view.findViewById(R.id.img_face);
        TextView tipTv = view.findViewById(R.id.tv_tip);
        Button btn1 = view.findViewById(R.id.btn_1);
        Button btn2 = view.findViewById(R.id.btn_2);
        tipTv.setText("恭喜你!\n你已经完成了句法"+ (index+1)+"的声调练习");
        btn1.setText("声调练习");
        btn1.setVisibility(View.GONE);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        builder.setView(view);
        builder.setCancelable(false);
        builder.show();
    }
}
