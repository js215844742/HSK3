package hsk3.jane.cn.hsk3.activity.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jane on 2018/3/6.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    Context context;
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    public FragmentAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }
    public void setFragments(Context context, List<Fragment> fragments){
        this.fragments = fragments;
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
