package hsk3.jane.cn.hsk3.activity.activity.main.sentence;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.activity.mine.MineSettingWordsActivity;
import hsk3.jane.cn.hsk3.base.MyApplication;
import hsk3.jane.cn.hsk3.db.WordDBAdapter;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * Created by Jane on 2018/4/3.
 */

public class SentenceWordDetailDialogFragment extends DialogFragment {
    String word;
    private TextView pinyinTv, wordTv, typeTv, explaneTv, classNumberTv, lianxiangTv, wordsBtn;
    WordDBAdapter dbAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_sentence_practice_word_detail, null);
        initView(view);
        word = getArguments().getString("word");
        dbAdapter = ((MyApplication)getActivity().getApplication()).getDbAdapter();
        initData();
        return view;
    }

    public static SentenceWordDetailDialogFragment newInstance(String word){
        SentenceWordDetailDialogFragment fragment = new SentenceWordDetailDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("word", word);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initView(View view) {
        pinyinTv = view.findViewById(R.id.tv_pinyin);
        wordTv = view.findViewById(R.id.tv_hanzi);
        typeTv = view.findViewById(R.id.tv_type);
        explaneTv = view.findViewById(R.id.tv_explane);
        classNumberTv = view.findViewById(R.id.tv_class_number);
        lianxiangTv = view.findViewById(R.id.tv_lianxiang);
        wordsBtn = view.findViewById(R.id.btn_words);
        wordsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidUtils.startActivity(getActivity(), MineSettingWordsActivity.class, true);
            }
        });
    }

    private void initData() {
        wordTv.setText(word);
        Cursor cursor = dbAdapter.searchExact(word);
        if (cursor.getCount()<=0){
            pinyinTv.setText("");
            typeTv.setText("未查到此词语");
            explaneTv.setText("");
            classNumberTv.setText("");
            lianxiangTv.setText("");
        }else{
            cursor.moveToFirst();
            pinyinTv.setText(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_PINYIN)));
            typeTv.setText("词性："+cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_TYPE)));
            explaneTv.setText("词义："+cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_EXPLANE)));
            classNumberTv.setText("课号："+cursor.getInt(cursor.getColumnIndex(WordDBAdapter.KEY_CLASS_NUMBER))+"");
            lianxiangTv.setText("联想："+cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_LIANXIANG)));
        }
    }
}
