package hsk3.jane.cn.hsk3.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.bean.Word;

/**
 * Created by Jane on 2018/4/3.
 */

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Word> arrayList;
    public WordsAdapter(Context context, ArrayList<Word> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_words, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word item = arrayList.get(position);
        holder.firstLetterTv.setText(item.getFirstLetter());
        holder.wordTv.setText(item.getWord());
        holder.pinyinTv.setText(item.getPinyin());
        holder.typeTv.setText(item.getType());
        holder.explaneTv.setText(item.getExplane());
        holder.classNumberTv.setText(item.getClassNumber()+"");
        if (position==0){
            holder.firstLetterTv.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);
        } else if (item.getFirstLetter().equals(arrayList.get(position-1).getFirstLetter())) {
            holder.firstLetterTv.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }else{
            holder.firstLetterTv.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private View line;
        private TextView firstLetterTv, wordTv, pinyinTv, typeTv, explaneTv, classNumberTv;
        public ViewHolder(View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.view);
            firstLetterTv = itemView.findViewById(R.id.tv_first_letter);
            wordTv = itemView.findViewById(R.id.tv_word);
            pinyinTv = itemView.findViewById(R.id.tv_pinyin);
            typeTv = itemView.findViewById(R.id.tv_type);
            explaneTv = itemView.findViewById(R.id.tv_explane);
            classNumberTv = itemView.findViewById(R.id.tv_class_number);
        }
    }
}
