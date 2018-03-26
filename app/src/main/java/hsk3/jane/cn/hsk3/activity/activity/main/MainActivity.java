package hsk3.jane.cn.hsk3.activity.activity.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.fragment.HomeFragment;
import hsk3.jane.cn.hsk3.activity.fragment.MineFragment;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.view.BottomNavigationViewEx;

public class MainActivity extends BaseActivity {
    BottomNavigationViewEx bottomNavigationView;
    HomeFragment homeFragment;
    MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.work:
                        ft.hide(mineFragment).show(homeFragment).commitAllowingStateLoss();
                        break;
                    case R.id.mine:
                        ft.hide(homeFragment).show(mineFragment).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        homeFragment = HomeFragment.newInstance();
        mineFragment = MineFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_All_id, homeFragment).show(homeFragment);
        ft.add(R.id.fragment_All_id, mineFragment).hide(mineFragment);
        ft.commitAllowingStateLoss();
    }

}
