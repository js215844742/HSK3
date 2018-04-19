package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.SpUtils;
import hsk3.jane.cn.hsk3.utils.ViewHolder;
import hsk3.jane.cn.hsk3.view.GlideCircleTransform;

/**
 * 个人中心*个人信息
 * Created by Jane on 2018/3/21.
 */

public class MineInfoActivity extends BaseActivity implements View.OnClickListener {
    private TextView nameTv, genderTv;
    private ImageView headImg;
    private int CODE_PHOTO_CAMERA = 11;
    private int CODE_PHOTO_PICK = 22;
    private int CODE_PHOTO_CUT = 33;
    int gender = 0;
    private PopupWindow popupWindow;
    private int [] heads = {R.mipmap.head_1,R.mipmap.head_2,R.mipmap.head_3,R.mipmap.head_4};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        initView();
    }

    private void initView() {
        setTitle("个人信息");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
        nameTv = findViewById(R.id.tv_name);
        genderTv = findViewById(R.id.tv_gender);
        headImg = findViewById(R.id.img_head);
    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.view_head:
//                AndroidUtils.Toast(this, "换头像");
//                showPhotoDialog();
                showPopUpWindown();
                break;
            case R.id.view_name:
                AndroidUtils.startActivity(this, MineInfoNameActivity.class, true);
                break;
            case R.id.view_gender:
                showGenderDialog();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).load(heads[SpUtils.getIntPreference(MySpKey.SP_USER_HEAD_NUM_KEY, 1)-1]).transform(new GlideCircleTransform(this)).into(headImg);
        nameTv.setText(SpUtils.getStringPreference(MySpKey.SP_USER_NAME_KEY));
        genderTv.setText(SpUtils.getIntPreference(MySpKey.SP_USER_GENDER_KEY)==1?"先生":"女士");
    }
    private void showPhotoDialog(){
        final String [] list = {"拍照", "相册"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择方式");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
//                        AndroidUtils.Toast(MineInfoActivity.this, list[i]);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CODE_PHOTO_CAMERA);
                        break;
                    case 1:
                        AndroidUtils.Toast(MineInfoActivity.this, list[i]);
                        break;
                }
            }
        });
        builder.show();
    }
    private void showGenderDialog(){
        gender = SpUtils.getIntPreference(MySpKey.SP_USER_GENDER_KEY);
        final String [] genders = {"先生", "女士"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(genders, gender-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                gender = i+1;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SpUtils.saveIntPreference(MySpKey.SP_USER_GENDER_KEY,gender);
                genderTv.setText(gender==1?"先生":"女士");
            }
        });
        builder.setNegativeButton("取消",null);
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == CODE_PHOTO_CAMERA){

            }else if (requestCode == CODE_PHOTO_PICK){

            }else if (requestCode == CODE_PHOTO_CUT){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
