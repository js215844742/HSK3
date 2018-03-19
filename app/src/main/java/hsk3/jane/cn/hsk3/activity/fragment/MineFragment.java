package hsk3.jane.cn.hsk3.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hsk3.jane.cn.hsk3.R;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class MineFragment extends Fragment {
    public static MineFragment newInstance(){
//        Bundle bundle = new Bundle();
//        bundle.putInt("", 0);
        MineFragment mineFragment = new MineFragment();
//        mineFragment.setArguments(bundle);
        return mineFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_question, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
