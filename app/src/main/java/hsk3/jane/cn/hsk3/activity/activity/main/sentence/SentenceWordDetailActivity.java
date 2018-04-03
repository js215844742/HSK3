package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.activity.mine.MineSettingWordsActivity;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MyApplication;
import hsk3.jane.cn.hsk3.db.WordDBAdapter;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * Created by Jane on 2018/4/3.
 */

public class SentenceWordDetailActivity extends BaseActivity {
    private String word;
    private TextView pinyinTv, wordTv, typeTv, explaneTv, classNumberTv, lianxiangTv, wordsBtn;
    WordDBAdapter dbAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_practice_word_detail);
        dbAdapter = ((MyApplication)getApplication()).getDbAdapter();
        word = getIntent().getStringExtra("word");
        initView();
        initData();
    }

    private void initView() {
        pinyinTv = findViewById(R.id.tv_pinyin);
        wordTv = findViewById(R.id.tv_hanzi);
        typeTv = findViewById(R.id.tv_type);
        explaneTv = findViewById(R.id.tv_explane);
        classNumberTv = findViewById(R.id.tv_class_number);
        lianxiangTv = findViewById(R.id.tv_lianxiang);
        wordsBtn = findViewById(R.id.btn_words);
        wordsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtils.startActivity(SentenceWordDetailActivity.this, MineSettingWordsActivity.class, true);
            }
        });
    }

    private void initData(){
        wordTv.setText(word);
        Cursor cursor = dbAdapter.searchExact(word);
        if (cursor.getCount()<=0){
            pinyinTv.setText("");
            typeTv.setText("未查到此词语");
            explaneTv.setText("");
            classNumberTv.setText("");
            lianxiangTv.setText("");
        }else{
            pinyinTv.setText(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_PINYIN)));
            typeTv.setText(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_TYPE)));
            explaneTv.setText(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_EXPLANE)));
            classNumberTv.setText(cursor.getInt(cursor.getColumnIndex(WordDBAdapter.KEY_CLASS_NUMBER))+"");
            lianxiangTv.setText(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_LIANXIANG)));
        }
    }
}
