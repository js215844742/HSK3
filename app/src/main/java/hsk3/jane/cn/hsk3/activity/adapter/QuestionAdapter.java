package hsk3.jane.cn.hsk3.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.bean.QuestionBean;
import hsk3.jane.cn.hsk3.view.FluidLayout;

/**
 * Created by Jane on 2018/3/5.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    Context context;
    ArrayList<QuestionBean> arrayList;
    LayoutInflater inflater;
    public QuestionAdapter(Context context, ArrayList<QuestionBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QuestionBean item = arrayList.get(position);
        holder.numberTv.setText(position+1+".");
        for (int i = 0; i < item.getPinyin().length; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_question_view, null);
            ((TextView) view.findViewById(R.id.tv_pinyin)).setText(item.getPinyin()[i]);
            ((TextView) view.findViewById(R.id.tv_hanzi)).setText(item.getHanzi()[i]);
            holder.fluidLayout.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView numberTv;
        FluidLayout fluidLayout;
        EditText answerTv;
        FluidLayout rightAnswerView;
        public ViewHolder(View itemView) {
            super(itemView);
            numberTv = itemView.findViewById(R.id.tv_number);
            fluidLayout = itemView.findViewById(R.id.view_timu);
            answerTv = itemView.findViewById(R.id.edt_answer);
            rightAnswerView = itemView.findViewById(R.id.view_right_answer);
        }
    }
}
