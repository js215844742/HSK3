package hsk3.jane.cn.hsk3.activity.activity.main.sentence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.adapter.SyntaxAdapter;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.Utils;

/**
 * 选择句法,公式
 * Created by Administrator on 2018/3/20 0020.
 */

public class SentenceChooseSyntaxActivity extends BaseActivity {
    private ListView listview;
    private SyntaxAdapter adapter;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_choose_syntax);
        type = getIntent().getIntExtra("type", 1);
        initView();
    }

    private void initView() {
        setTitle(Utils.getTitle(type)+"-选择句法");
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        listview = findViewById(R.id.listView);
        adapter = new SyntaxAdapter(this);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != adapter.getCount()-1){
                    switch (type) {
                        case 1://连词成句
                            Intent intent1 = new Intent(SentenceChooseSyntaxActivity.this, SentencePracticeWordExampleActivity.class);
                            intent1.putExtra("index", i);
                            startActivity(intent1);
                            break;
                        case 2://句子练习
                            Intent intent2 = new Intent(SentenceChooseSyntaxActivity.this, SentenceMoreSentenceActivity.class);
                            intent2.putExtra("index", i);
                            startActivity(intent2);
                            break;
                        case 3://造句练习
                            Intent intent3 = new Intent(SentenceChooseSyntaxActivity.this, SentenceMakeSentenceActivity.class);
                            intent3.putExtra("index", i);
                            startActivity(intent3);
                            break;
                        case 4://声调练习
                            Intent intent4 = new Intent(SentenceChooseSyntaxActivity.this, SentenceToneActivity.class);
                            intent4.putExtra("index", i);
                            startActivity(intent4);
                            break;
                    }
                }
            }
        });
    }
}
