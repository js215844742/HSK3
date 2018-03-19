package hsk3.jane.cn.hsk3.activity.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.adapter.FragmentAdapter;
import hsk3.jane.cn.hsk3.activity.fragment.HomeFragment;
import hsk3.jane.cn.hsk3.activity.fragment.MineFragment;
import hsk3.jane.cn.hsk3.activity.fragment.QuestionFragment;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.data.Data;

public class MainActivity extends BaseActivity {
    Toolbar toolbar ;
    ViewPager viewPager;
    FragmentAdapter adapter;
    List<Fragment> fragments;

    private int index = 0;

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
//        for (int i = 0; i < Data.TYPES.length; i++) {
//            fragments.add(QuestionFragment.newInstance(i));
//        }
        fragments.add(HomeFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(MainActivity.this, fragments);
        viewPager.setAdapter(adapter);

    }

    public void onTabClicked(View view){
        switch (view.getId()){
            case R.id.home:
                index = 0;
                break;
            case R.id.mine:
                index = 1;
                break;
        }
        viewPager.setCurrentItem(index);
    }
}
