package hsk3.jane.cn.hsk3.utils;

import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

/**
 * Created by Jane on 2018/3/5.
 */

public class AndroidUtils {
    /**
     * 透明的导航栏
     */
    public static void hideNavigationBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
        }
    }
}
