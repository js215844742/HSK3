package hsk3.jane.cn.hsk3.activity.activity.start;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.activity.MainActivity;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.SpUtils;
import hsk3.jane.cn.hsk3.view.GlideCircleTransform;

/**
 * Created by Jane on 2018/3/7.
 */

public class FixNameActivity extends BaseActivity {
    private LinearLayout fixNameView;
    private TextView nameTv;
    private ImageView headImg;
    private EditText nameEdt;
    private Button beginBtn;
    private RadioButton boyRadio, girlRadio;
    private String name;
    private int gender;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_name);
        name = SpUtils.getStringPreference(MySpKey.SP_USER_NAME_KEY);
        gender = SpUtils.getIntPreference(MySpKey.SP_USER_GENDER_KEY);
        initView();
    }

    private void initView() {
        fixNameView = findViewById(R.id.view_fix_name);
        nameTv = findViewById(R.id.tv_name);
        headImg = findViewById(R.id.img_head);
        nameEdt = findViewById(R.id.edt_name);
        beginBtn = findViewById(R.id.btn_begin);
        boyRadio = findViewById(R.id.radio_boy);
        girlRadio = findViewById(R.id.radio_girl);
        Glide.with(this).load(R.mipmap.img_fix_name).transform(new GlideCircleTransform(this)).into(headImg);
//        nameEdt.setText(MathUtils.getChineseNumber(000));

        if (name.length()<=0){
            fixNameView.setVisibility(View.VISIBLE);
            nameTv.setVisibility(View.GONE);
        }else{
            nameTv.setText(name+(gender==1?"先生":"女士")+"，欢迎回来。\n开始今天的练习吧！");
            fixNameView.setVisibility(View.GONE);
            nameTv.setVisibility(View.VISIBLE);
        }
        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                begin();
            }
        });
    }

    private void begin() {
        if (name.length()<=0){
            if (nameEdt.getText().toString().trim().length()<=0){
                AndroidUtils.Toast(this,"请留下你的姓名");
                return;
            }
            SpUtils.saveStringPreference(MySpKey.SP_USER_NAME_KEY, nameEdt.getText().toString().trim());
            SpUtils.saveIntPreference(MySpKey.SP_USER_GENDER_KEY, boyRadio.isChecked()?1:2);
        }
        AndroidUtils.startActivity(FixNameActivity.this,MainActivity.class,true);
        finish();
    }
}
