package hsk3.jane.cn.hsk3.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * Created by Jane on 2018/3/5.`
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidUtils.hideNavigationBar(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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
}
