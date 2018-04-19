package hsk3.jane.cn.hsk3.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.utils.ViewHolder;

/**
 * Created by Jane on 2018/4/18.
 */

public class MineHeleQuestionAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String [] titles = {"登录", "词库", "其他"};
    private String [] [] questions = {
            {
                "首次登录"
            },
            {
                "为什么点击词语查字典的时候查不到对应的词语",
            },
            {
                "头像不能自定义",
                "语音不能播放"
            }
    };
    private String [] [] answers = {
            {
                "首次登陆时会弹出登陆界面，用户等级姓名和性别后即可打开APP的主界面，且姓名和性别均为必填哦。"
            },
            {
                "这个能是词库没有加载导致的，你可以打开：我->设置->词库，点击右上角弹出的“更新词库”，然后再查询试试，若仍未能查询到，可能是你点击的词语已经超出词库范围了哦。",
            },
            {
                "我们暂时不支持自定义头像的哦，你可以在给出的头像中选择你喜欢的头像哦。",
                "播放语音需要联网环境哦，请检查打开你的网络环境然后再试试吧。"
            }
    };

    public MineHeleQuestionAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return titles.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return questions[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return titles[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return questions[i][i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_mine_help_question_group, null);
        }
        TextView titleTv = (TextView) ViewHolder.get(view, R.id.tv_title);
        titleTv.setText(titles[i]);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_mine_help_question_child, null);
        }
        TextView questionTv = (TextView) ViewHolder.get(view, R.id.tv_question);
        questionTv.setText((i1+1)+"."+questions[i][i1]);
        TextView answerTv = (TextView) ViewHolder.get(view, R.id.tv_answer);
        answerTv.setText(""+answers[i][i1]);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
