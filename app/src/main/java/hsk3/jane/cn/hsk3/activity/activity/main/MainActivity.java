package hsk3.jane.cn.hsk3.activity.activity.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.fragment.HomeFragment;
import hsk3.jane.cn.hsk3.activity.fragment.MineFragment;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MyActivityManager;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.SpUtils;
import hsk3.jane.cn.hsk3.view.BottomNavigationViewEx;

public class MainActivity extends BaseActivity {
    BottomNavigationViewEx bottomNavigationView;
    HomeFragment homeFragment;
    MineFragment mineFragment;
    private int [] menus = {R.menu.menu_bottom_navigation, R.menu.menu_bottom_navigation_1};
    private int theme;
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
    private long exitTime0 = 0;
    private long exitTime1 = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime0 > 1000) {
                AndroidUtils.Toast(this, "再点一次退出");
                exitTime0 = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - exitTime1 > 1000) {
                MyActivityManager.clearActivities();
            }
            return false;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        theme = SpUtils.getIntPreference(MySpKey.SP_SETTING_THEME_KEY, 0);
//        bottomNavigationView.getMenu().clear();
//        bottomNavigationView.inflateMenu(menus[theme]);
    }
}
