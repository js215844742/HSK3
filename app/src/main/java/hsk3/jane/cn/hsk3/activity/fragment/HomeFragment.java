package hsk3.jane.cn.hsk3.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.adapter.HomeAdapter;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
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
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText("首页");
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new HomeAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }
}
