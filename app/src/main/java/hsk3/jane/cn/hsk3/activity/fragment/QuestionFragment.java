package hsk3.jane.cn.hsk3.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hsk3.jane.cn.hsk3.R;
import hsk3.jane.cn.hsk3.activity.adapter.QuestionAdapter;
import hsk3.jane.cn.hsk3.activity.bean.QuestionBean;
import hsk3.jane.cn.hsk3.data.SentenceData;

import static hsk3.jane.cn.hsk3.data.SentenceQuestionData.HANZIS;
import static hsk3.jane.cn.hsk3.data.SentenceQuestionData.PINYINS;
import static hsk3.jane.cn.hsk3.data.SentenceQuestionData.RIGHTANSWERS;


/**
 * Created by Jane on 2018/3/6.
 */

public class QuestionFragment extends Fragment{
    TextView typeTv;
    RecyclerView recyclerView;
    QuestionAdapter adapter;
    private int index;//第n种公示
    ArrayList<QuestionBean> arrayList;
    public static QuestionFragment newInstance(int index){
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setArguments(bundle);
        return questionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        index = getArguments().getInt("index");
        View view = inflater.inflate(R.layout.fragment_question, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        typeTv = view.findViewById(R.id.tv_type);
        recyclerView = view.findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        adapter = new QuestionAdapter(getActivity(), arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        typeTv.setText(SentenceData.SYNTAX[index]);

        for (int i = 0; i < PINYINS[index].length; i++) {
            arrayList.add(new QuestionBean(PINYINS[index][i], HANZIS[index][i], RIGHTANSWERS[index][i]));
        }
        adapter.notifyDataSetChanged();
    }
}
