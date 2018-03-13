package hsk3.jane.cn.hsk3.utils;

import android.util.SparseArray;
import android.view.View;

import hsk3.jane.cn.hsk3.R;

/**
 * ViewHolder的工具类
 * Created by Jane on 2018/3/13.
 */

public class ViewHolder {
    // 添加私有构造函数，防止外部实例化
    public ViewHolder() {
    }

    /**
     * 用来缓存空间，优化加载
     * @param view  itemview布局
     * @param id    itemview布局中需要缓存的控件的id
     * @return      缓存后的控件（TextView,ImageView...等控件）
     */
    public static View get(View view, int id){
        //获取itemView的ViewHolder对象，并将其转型为SparseArray<View>
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null){
            //如果viewholder为空，就新建一个
            viewHolder = new SparseArray<>();
            //给view设置Tag标签
            view.setTag(viewHolder);
        }
        //根据控件id获取itemview布局的控件
        View childView = viewHolder.get(id);
        if (childView == null){
            //如果childview为空，说明尚未缓存该控件，那么就根据itemview找到该控件
            childView = view.findViewById(id);
            //缓存该控件
            viewHolder.put(id, childView);
        }
        //返回缓存号的控件
        return  childView;
    }
}
