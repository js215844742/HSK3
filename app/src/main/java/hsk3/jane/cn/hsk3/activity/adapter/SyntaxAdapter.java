package hsk3.jane.cn.hsk3.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.data.Data;
import hsk3.jane.cn.hsk3.utils.ViewHolder;

/**
 * Created by Jane on 2018/3/21.
 */

public class SyntaxAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Context context;

    public SyntaxAdapter(Context context) {
        super();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Data.SYNTAX.length;
    }

    @Override
    public Object getItem(int i) {
        return Data.SYNTAX[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String item = Data.SYNTAX[i];
        if (view == null){
            view = inflater.inflate(R.layout.item_sentence_syntax, null);
        }
        TextView title = (TextView) ViewHolder.get(view, R.id.tv_title);
        title.setText(item);
        return view;
    }
}