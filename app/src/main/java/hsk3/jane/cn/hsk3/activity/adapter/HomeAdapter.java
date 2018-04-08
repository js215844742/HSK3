package hsk3.jane.cn.hsk3.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.activity.main.sentence.SentenceStyleActivity;
import hsk3.jane.cn.hsk3.utils.AndroidUtils;

/**
 * Created by Jane on 2018/3/20.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private Context context;
    private String [] arrayList = {"句子练习", "声调练习", "成语练习", "神话故事", "三字经"};
    private String [] summaries = {"Sentence Pratice", "Other", "Other", "Other", "Other"};
    public HomeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (position==0) {
            holder.view.setBackgroundResource(R.drawable.shape_bg_item_home_violet);
        }else {
            holder.view.setBackgroundResource(R.drawable.shape_bg_item_home_gray);
        }
        holder.nameTv.setText(arrayList[position]);
        holder.summaryTv.setText(summaries[position]);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0){
                    AndroidUtils.startActivity(context, SentenceStyleActivity.class,true);
                }else{
                    AndroidUtils.Toast(context, "暂未开放");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout view;
        private TextView nameTv;
        private TextView summaryTv;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            nameTv = itemView.findViewById(R.id.tv_name);
            summaryTv = itemView.findViewById(R.id.tv_summary);
        }
    }
}
