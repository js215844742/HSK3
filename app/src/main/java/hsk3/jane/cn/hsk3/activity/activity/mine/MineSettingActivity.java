package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;
import hsk3.jane.cn.hsk3.base.MyApplication;
import hsk3.jane.cn.hsk3.data.WordData;
import hsk3.jane.cn.hsk3.db.WordDBAdapter;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.DialogUtils;

/**
 * 个人中心*设置
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineSettingActivity extends BaseActivity {
    WordDBAdapter dbAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);
        initView();
        dbAdapter = ((MyApplication)getApplication()).getDbAdapter();

    }

    private void initView() {
        setTitle("设置");
        initToolbar((Toolbar)findViewById(R.id.toolbar));
    }

    public void onStart(View view){
        switch (view.getId()){
            case R.id.view_data:
//                showDiaolog();
//                AndroidUtils.Toast(this, "加载数据");
                AndroidUtils.startActivity(this, MineSettingWordsActivity.class, true);
                break;
            case R.id.view_theme:
                AndroidUtils.startActivity(this, MineSettingThemeActivity.class, true);
                break;
            case R.id.btn_exit:
                break;

//            case R.id.btn_ok:
//                AndroidUtils.Toast(this, "确认");
//                break;
//            case R.id.btn_cancle:
//                AndroidUtils.Toast(this, "取消");
//                break;
        }
    }

    private void showDiaolog() {
//        View view = LayoutInflater.from(MineSettingActivity.this).inflate(R.layout.view_dialog_init_words, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

//        ImageView img = view.findViewById(R.id.img_face);
//        TextView tipTv = view.findViewById(R.id.tv_tip);
//        Button btn1 = view.findViewById(R.id.btn_1);
//        Button btn2 = view.findViewById(R.id.btn_2);
//        tipTv.setText("加载词库？");
//        btn1.setText("更多句子练习");
//        dialog.setView(view);
        builder.setTitle("提示");
        builder.setMessage("加载词库？");

        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                initWords();
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AndroidUtils.Toast(MineSettingActivity.this, "取消");
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void initWords() {
        DialogUtils.showProgressDialog(MineSettingActivity.this);
        for (int i = 0; i < WordData.WORDS.length; i++) {
            if (!dbAdapter.isExist(i+1)) {
                dbAdapter.insert(i);
            }
        }
    }
}
