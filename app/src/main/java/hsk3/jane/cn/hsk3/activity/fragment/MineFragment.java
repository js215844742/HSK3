package hsk3.jane.cn.hsk3.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.SpUtils;
import hsk3.jane.cn.hsk3.view.GlideCircleTransform;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class MineFragment extends Fragment {
    private ImageView headImg;
    private TextView nameTv, genderTv;
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
        Glide.with(this).load(R.mipmap.img_fix_name).transform(new GlideCircleTransform(getActivity())).into(headImg);
        nameTv.setText(SpUtils.getStringPreference(MySpKey.SP_USER_NAME_KEY));
        genderTv.setText(SpUtils.getIntPreference(MySpKey.SP_USER_GENDER_KEY)==1?"先生":"女士");
    }
}
