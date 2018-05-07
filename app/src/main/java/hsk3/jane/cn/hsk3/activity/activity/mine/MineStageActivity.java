package hsk3.jane.cn.hsk3.activity.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.base.BaseActivity;

/**
 * 个人中心*阶段
 * Created by Administrator on 2018/3/21 0021.
 */

public class MineStageActivity extends BaseActivity implements View.OnClickListener {
    private ImageView stage1Img, stage2Img, stage3Img, stage4Img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_stage);
        initView();
    }

    private void initView() {
        setTitle("第一阶段");
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        stage1Img = findViewById(R.id.img_stage_1);
        stage2Img = findViewById(R.id.img_stage_2);
        stage3Img = findViewById(R.id.img_stage_3);
        stage4Img = findViewById(R.id.img_stage_4);

        stage1Img.setOnClickListener(this);
        stage2Img.setOnClickListener(this);
        stage3Img.setOnClickListener(this);
        stage4Img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_stage_1:
                break;
            case R.id.img_stage_2:
                break;
            case R.id.img_stage_3:
                break;
            case R.id.img_stage_4:
                break;
        }
    }
}
