package hsk3.jane.cn.hsk3.activity.activity.start;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.utils.MathUtils;
import hsk3.jane.cn.hsk3.view.GlideCircleTransform;

/**
 * Created by Jane on 2018/3/7.
 */

public class FixNameActivity extends BaseActivity {
    private ImageView headImg;
    private EditText nameEdt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_name);
        initView();
    }

    private void initView() {
        headImg = findViewById(R.id.img_head);
        nameEdt = findViewById(R.id.edt_name);
        Glide.with(this).load(R.mipmap.img_fix_name).transform(new GlideCircleTransform(this)).into(headImg);
        nameEdt.setText(MathUtils.getChineseNumber(000));
    }
}
