package hsk3.jane.cn.hsk3.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hsk3.jane.cn.hsk3.R;

/**
 * 弹框管理
 * Created by Administrator on 2018/3/25 0025.
 */

public class DialogUtils {

    private static ProgressDialog dialog;
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (dialog != null)
                dialog.cancel();
            dialog = null;
        }
    };

    public static void showProgressDialog(Context context, int title,
                                          int message) {
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        dialog.setTitle(title);
        dialog.setIndeterminate(false);// 进度条是否明确进度
        dialog.setMessage(context.getString(message));
        dialog.show();
    }
    public static void showProgressDialog(Context context, String title,
                                          String message) {
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        dialog.setTitle(title);
        dialog.setIndeterminate(false);// 进度条是否明确进度
        dialog.setMessage(message);
        dialog.show();
    }
    public static void showProgressDialog(Context context){
        cancelProgressDialog();
        dialog = new ProgressDialog(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("加载中...");
        dialog.show();
    }
    public static void cancelProgressDialog() {
        mHandler.sendEmptyMessage(0);
    }
    public static boolean isProgressCanceled() {
        if (dialog == null || !dialog.isShowing()) {
            return true;
        }
        return false;
    }
    public static void showCheckDiaolog(Context context, boolean isRight) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_check, null);
        final AlertDialog builder = new AlertDialog.Builder(context).create();

        ImageView img = view.findViewById(R.id.img_face);
        TextView tipTv = view.findViewById(R.id.tv_tip);
        Button btn1 = view.findViewById(R.id.btn_1);
        Button btn2 = view.findViewById(R.id.btn_2);
        tipTv.setText(isRight?"恭喜你，答对了！":"很遗憾，答错了！");
        Glide.with(context).load(isRight?R.mipmap.img_face_smail:R.mipmap.img_face_cry).into(img);
        builder.setView(view);
        builder.setCancelable(false);
        builder.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                builder.cancel();
            }
        }, 1000);
    }
}
