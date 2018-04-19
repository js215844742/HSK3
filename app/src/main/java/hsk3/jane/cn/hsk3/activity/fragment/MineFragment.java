package hsk3.jane.cn.hsk3.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.activity.mine.MineAboutActivity;
import hsk3.jane.cn.hsk3.activity.activity.mine.MineHelpActivity;
import hsk3.jane.cn.hsk3.activity.activity.mine.MineInfoActivity;
import hsk3.jane.cn.hsk3.activity.activity.mine.MineSettingActivity;
import hsk3.jane.cn.hsk3.activity.activity.mine.MineStageActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.SpUtils;
import hsk3.jane.cn.hsk3.view.GlideCircleTransform;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private ImageView headImg;
    private TextView nameTv, genderTv;
    private RelativeLayout infoView;
    private LinearLayout stageView, aboutView, helpView, settingView;
    private int [] heads = {R.mipmap.head_1,R.mipmap.head_2,R.mipmap.head_3,R.mipmap.head_4};
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
        View view = inflater.inflate(R.layout.fragment_mine, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText("个人中心");
        nameTv = view.findViewById(R.id.tv_name);
        genderTv = view.findViewById(R.id.tv_genderTv);
        headImg = view.findViewById(R.id.img_head);
        infoView = view.findViewById(R.id.view_info);
        stageView = view.findViewById(R.id.view_stage);
        aboutView = view.findViewById(R.id.view_about);
        helpView = view.findViewById(R.id.view_help);
        settingView = view.findViewById(R.id.view_setting);

        infoView.setOnClickListener(this);
        stageView.setOnClickListener(this);
        aboutView.setOnClickListener(this);
        helpView.setOnClickListener(this);
        settingView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view_info:
                AndroidUtils.startActivity(getActivity(), MineInfoActivity.class, true);
                break;
            case R.id.view_stage:
                AndroidUtils.startActivity(getActivity(), MineStageActivity.class, true);
                break;
            case R.id.view_about:
                AndroidUtils.startActivity(getActivity(), MineAboutActivity.class, true);
                break;
            case R.id.view_help:
                AndroidUtils.startActivity(getActivity(), MineHelpActivity.class, true);
                break;
            case R.id.view_setting:
                AndroidUtils.startActivity(getActivity(), MineSettingActivity.class, true);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Glide.with(this).load(heads[SpUtils.getIntPreference(MySpKey.SP_USER_HEAD_NUM_KEY, 1)-1]).transform(new GlideCircleTransform(getActivity())).into(headImg);
        nameTv.setText(SpUtils.getStringPreference(MySpKey.SP_USER_NAME_KEY));
        genderTv.setText(SpUtils.getIntPreference(MySpKey.SP_USER_GENDER_KEY)==1?"先生":"女士");
    }
}
