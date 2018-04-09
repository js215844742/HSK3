package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MySpKey;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.SpUtils;

/**
 * 个人中心*个人信息
 * Created by Jane on 2018/3/21.
 */

public class MineInfoActivity extends BaseActivity {
    private TextView nameTv, genderTv;
    private int CODE_PHOTO_CAMERA = 11;
    private int CODE_PHOTO_PICK = 22;
    private int CODE_PHOTO_CUT = 33;
    int gender = 0;
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
    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.view_head:
//                AndroidUtils.Toast(this, "换头像");
//                showPhotoDialog();
                break;
            case R.id.view_name:
                AndroidUtils.startActivity(this, MineInfoNameActivity.class, true);
                break;
            case R.id.view_gender:
//                AndroidUtils.Toast(this, "选择性别");
                showGenderDialog();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
}
