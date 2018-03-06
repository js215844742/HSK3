package hsk3.jane.cn.hsk3.activity.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.adapter.FragmentAdapter;
import hsk3.jane.cn.hsk3.activity.fragment.QuestionFragment;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.data.Data;

public class MainActivity extends BaseActivity {
    Toolbar toolbar ;
    ViewPager viewPager;
    FragmentAdapter adapter;
    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        setTitle("主页");
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewpager);
//        initToolbar(toolbar);
        fragments = new ArrayList<>();
        for (int i = 0; i < Data.TYPES.length; i++) {
            fragments.add(QuestionFragment.newInstance(i));
        }
        adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(MainActivity.this, fragments);
        viewPager.setAdapter(adapter);

    }
}
