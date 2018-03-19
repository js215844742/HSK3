package hsk3.jane.cn.hsk3.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hsk3.jane.cn.hsk3.R;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class HomeFragment extends Fragment {
    public static HomeFragment newInstance(){
//        Bundle bundle = new Bundle();
//        bundle.putInt("", 0);
        HomeFragment homeFragment = new HomeFragment();
//        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
