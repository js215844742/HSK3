package hsk3.jane.cn.hsk3.activity.activity.start;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.activity.main.MainActivity;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.SpUtils;
import hsk3.jane.cn.hsk3.utils.ViewHolder;
import hsk3.jane.cn.hsk3.view.GlideCircleTransform;

/**
 * Created by Jane on 2018/3/7.
 */

public class FixNameActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout fixNameView;
    private TextView nameTv;
    private ImageView headImg;
    private EditText nameEdt;
    private Button beginBtn;
    private RadioButton boyRadio, girlRadio;
    private String name;
    private int gender;
    private PopupWindow popupWindow;
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
        Glide.with(this).load(R.mipmap.head_1).transform(new GlideCircleTransform(this)).into(headImg);
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
        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpWindown();
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

    private void showPopUpWindown(){
        setBackgroundAlpha(0.5f);
        View view = View.inflate(this, R.layout.view_head_img, null);
        ImageView headImg1 = (ImageView) ViewHolder.get(view, R.id.img_head_1);
        ImageView headImg2 = (ImageView) ViewHolder.get(view, R.id.img_head_2);
        ImageView headImg3 = (ImageView) ViewHolder.get(view, R.id.img_head_3);
        ImageView headImg4 = (ImageView) ViewHolder.get(view, R.id.img_head_4);
        headImg1.setOnClickListener(this);
        headImg2.setOnClickListener(this);
        headImg3.setOnClickListener(this);
        headImg4.setOnClickListener(this);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

//        popupWindow.setAnimationStyle(R.style.animationHead);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(headImg, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     * 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_head_1:
                Glide.with(this).load(R.mipmap.head_1).transform(new GlideCircleTransform(this)).into(headImg);
                SpUtils.saveIntPreference(MySpKey.SP_USER_HEAD_NUM_KEY, 1);
                break;
            case R.id.img_head_2:
                Glide.with(this).load(R.mipmap.head_2).transform(new GlideCircleTransform(this)).into(headImg);
                SpUtils.saveIntPreference(MySpKey.SP_USER_HEAD_NUM_KEY, 2);
                break;
            case R.id.img_head_3:
                Glide.with(this).load(R.mipmap.head_3).transform(new GlideCircleTransform(this)).into(headImg);
                SpUtils.saveIntPreference(MySpKey.SP_USER_HEAD_NUM_KEY, 3);
                break;
            case R.id.img_head_4:
                Glide.with(this).load(R.mipmap.head_4).transform(new GlideCircleTransform(this)).into(headImg);
                SpUtils.saveIntPreference(MySpKey.SP_USER_HEAD_NUM_KEY, 4);
                break;
        }
        popupWindow.dismiss();
    }
}
