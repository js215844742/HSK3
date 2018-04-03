package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.adapter.WordsAdapter;
import hsk3.jane.cn.hsk3.activity.bean.Word;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MyApplication;
import hsk3.jane.cn.hsk3.data.WordData;
import hsk3.jane.cn.hsk3.db.WordDBAdapter;
import hsk3.jane.cn.hsk3.utils.DialogUtils;

/**
 * Created by Jane on 2018/4/2.
 */

public class MineSettingWordsActivity extends BaseActivity {
    WordDBAdapter dbAdapter;
    Cursor cursor;
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<Word> arrayList;
    private WordsAdapter adapter;
    private RelativeLayout noDataView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting_words);
        initView();
        dbAdapter = ((MyApplication)getApplication()).getDbAdapter();
        initData();
    }

    private void initView() {
        setTitle("词库");
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        noDataView = findViewById(R.id.view_no_data);
        arrayList = new ArrayList<>();
        adapter = new WordsAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initToolbar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.more:
                        showDiaolog();
                        break;
                }
                return false;
            }
        });
        noDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initWords();
            }
        });
    }

    private void initData() {
        arrayList.clear();
        cursor = dbAdapter.getAllByLetter();
        if (cursor.getCount()>0){
            recyclerView.setVisibility(View.VISIBLE);
            noDataView.setVisibility(View.GONE);
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                arrayList.add(new Word());
                arrayList.get(i).setId(cursor.getInt(cursor.getColumnIndex(WordDBAdapter.KEY_ID)));
                arrayList.get(i).setIndex(cursor.getInt(cursor.getColumnIndex(WordDBAdapter.KEY_INDEX)));
                arrayList.get(i).setWord(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_WORD)));
                arrayList.get(i).setPinyin(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_PINYIN)));
                arrayList.get(i).setFirstLetter(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_FIRST_LETTER)));
                arrayList.get(i).setTone(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_TONE)));
                arrayList.get(i).setType(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_TYPE)));
                arrayList.get(i).setExplane(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_EXPLANE)));
                arrayList.get(i).setClassNumber(cursor.getInt(cursor.getColumnIndex(WordDBAdapter.KEY_CLASS_NUMBER)));
                arrayList.get(i).setLianxiang(cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_LIANXIANG)));
            }
            adapter.notifyDataSetChanged();
        }else{
            recyclerView.setVisibility(View.GONE);
            noDataView.setVisibility(View.VISIBLE);
        }
    }

    private void showDiaolog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("加载词库？");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                initWords();
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                AndroidUtils.Toast(MineSettingWordsActivity.this, "取消");
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void initWords() {
        DialogUtils.showProgressDialog(MineSettingWordsActivity.this);
        for (int i = 0; i < WordData.WORDS.length; i++) {
            if (!dbAdapter.isExist(i)) {
                dbAdapter.insert(i);
            }
        }
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting_words, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
