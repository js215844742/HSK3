package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.view.FluidLayout;

/**
 * 声调练习
 * Created by Jane on 2018/3/28.
 */

public class SentenceToneActivity extends BaseActivity {
    private GestureLibrary gLib;
    private FluidLayout fluidLayout;
    private int index;//句型序号
    private int position = 0;//当前序号
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
        fluidLayout.removeAllViews();

        View view = LayoutInflater.from(this).inflate(R.layout.item_question_tone, null);
//            ((TextView) view.findViewById(R.id.tv_pinyin)).setText(SentenceQuestionData.PINYINS[index][position][i]);
        ((TextView) view.findViewById(R.id.tv_pinyin)).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tv_hanzi)).setText("脑");
        GestureOverlayView gestureView = view.findViewById(R.id.view_gesture);
        final ImageView toneImg = view.findViewById(R.id.img_tone);
        gestureView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                ArrayList<Prediction> predictions = gLib.recognize(gesture);
                if (predictions.size()>0 && predictions.get(0).score > 1.0){
                    String result = predictions.get(0).name;
                    if (result.equals("0")){

                    }else  if (result.equals("1")){
                        Glide.with(SentenceToneActivity.this).load(R.mipmap.img_tone_1).into(toneImg);
                    }else  if (result.equals("2")){
                        Glide.with(SentenceToneActivity.this).load(R.mipmap.img_tone_2).into(toneImg);
                    }else  if (result.equals("3")){
                        Glide.with(SentenceToneActivity.this).load(R.mipmap.img_tone_3).into(toneImg);
                    }else  if (result.equals("4")){
                        Glide.with(SentenceToneActivity.this).load(R.mipmap.img_tone_4).into(toneImg);
                    }else {
//                        AndroidUtils.Toast(SentenceToneActivity.this, "未识别");
                    }
                }
            }
        });
        fluidLayout.addView(view);

    }
}
