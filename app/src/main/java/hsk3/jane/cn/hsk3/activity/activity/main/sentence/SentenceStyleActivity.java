package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * 选择练习项目
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

    public void onStart(View view){
        Intent intent = new Intent(this, SentenceChooseSyntaxActivity.class);
        switch (view.getId()){
            case R.id.btn_style_1:
                intent.putExtra("type", 1);
                break;
            case R.id.btn_style_2:
                intent.putExtra("type", 2);
                break;
            case R.id.btn_style_3:
                intent.putExtra("type", 3);
                break;
            case R.id.btn_style_4:
                intent.putExtra("type", 4);
                break;

        }
        AndroidUtils.startActivity(this,intent,true);
    }
}
