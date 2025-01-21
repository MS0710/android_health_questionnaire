package com.example.questionapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.questionapp.R;
import com.example.questionapp.data.HomeItem;

import java.util.List;

public class AnalysisResultAdapter extends BaseAdapter {
    private Context context;
    private List<HomeItem> list;
    public AnalysisResultAdapter(Context _context,List<HomeItem> _list){
        this.context = _context;
        this.list = _list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 第一次加载创建View，其余复用 View
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.analysis_result_item, null);
            holder = new ViewHolder();
            holder.txt_analysisResultItem_title = (TextView) convertView.findViewById(R.id.txt_analysisResultItem_title);
            // 打标签
            convertView.setTag(holder);

        } else {
            // 从标签中获取数据
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_analysisResultItem_title.setText(list.get(position).getTitle());


        return convertView;
    }

    class ViewHolder {
        TextView txt_analysisResultItem_title;
    }
}
