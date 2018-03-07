package hsk3.jane.cn.hsk3.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import hsk3.jane.cn.hsk3.R;

/**
 * Created by Jane on 2018/3/5.
 */

public class AndroidUtils {
    public static Toast localToast;
    /**
     * 透明的导航栏
     */
    public static void hideNavigationBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
        }
    }

    /**
     * 弹出Toast提示
     * @param context
     * @param string
     */
    public static void Toast(Context context, String string){
        if (context == null) return;
        if (localToast == null) {
            localToast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        }else {
            localToast.setText(string);
        }
        localToast.setGravity(Gravity.CENTER, 0, 0);
        localToast.show();
    }

    /**
     * 弹出Toast提示
     * @param context
     * @param strId
     */
    public static void Toast(Context context, int strId){
        if (context == null) return;
        if (localToast == null) {
            localToast = Toast.makeText(context, strId, Toast.LENGTH_SHORT);
        }else {
            localToast.setText(strId);
        }
        localToast.setGravity(Gravity.CENTER, 0, 0);
        localToast.show();
    }

    /**
     * 开启新的activity
     * @param context
     * @param activity
     * @param bundle
     * @param anime
     */
    public static void startActivity(Context context, Class<?> activity, Bundle bundle, boolean anime){
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
        if (anime) {
            ((Activity) context).overridePendingTransition(
                    R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    /**
     * 开启新的activity
     * @param context
     * @param activity
     * @param anime
     */
    public static void startActivity(Context context, Class<?> activity, boolean anime){
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        if (anime) {
            ((Activity) context).overridePendingTransition(
                    R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    /**
     * 开启新的activity
     * @param context
     * @param intent
     * @param anime
     */
    public static void startActivity(Context context, Intent intent, boolean anime){
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        if (anime) {
            ((Activity) context).overridePendingTransition(
                    R.anim.in_from_right, R.anim.out_to_left);
        }
    }


}
