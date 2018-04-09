package hsk3.jane.cn.hsk3.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;
import hsk3.jane.cn.hsk3.utils.PermissionUtils;

/**
 * Created by Jane on 2018/3/5.`
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * 权限回调Handler
     */
    private PermissionHandler mHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidUtils.hideNavigationBar(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        MyActivityManager.addActivity(this);
    }

    public void setTitle(String title){
        TextView textView = findViewById(R.id.tv_title);
        textView.setText(title);
    }
    public void initToolbar(Toolbar toolbar){
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        toolbar.inflateMenu(R.menu.rank_menu);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                MediaPlayerUtils.pointMusic(toolbar, AdvanceActivity.this, new OwinResposeListening() {
//                    @Override
//                    public void onResponse(Object response) {
//                        Intent intent0 = new Intent(AdvanceActivity.this, RankActivity.class);
//                        intent0.putExtra("id", 0);
//                        AndroidUtils.gotoActivity(AdvanceActivity.this,true,intent0);
//                    }
//                });
//                return false;
//            }
//        });
    }

    /**
     * 请求权限
     *
     * @param permissions 权限列表
     * @param handler     回调
     */
    protected void requestPermission(String[] permissions, PermissionHandler handler) {
        if (PermissionUtils.hasSelfPermissions(this, permissions)) {
            handler.onGranted();
        } else {
            mHandler = handler;
            ActivityCompat.requestPermissions(this, permissions, 001);
        }
    }
    /**
     * 权限回调接口
     */
    public abstract class PermissionHandler {
        /**
         * 权限通过
         */
        public abstract void onGranted();

        /**
         * 权限拒绝
         */
        public void onDenied() {
        }

        /**
         * 不再询问
         *
         * @return 如果要覆盖原有提示则返回true
         */
        public boolean onNeverAsk() {
            return false;
        }
    }
}
