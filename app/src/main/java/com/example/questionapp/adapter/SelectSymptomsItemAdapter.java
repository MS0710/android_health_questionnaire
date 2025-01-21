package com.example.questionapp.adapter;

import static com.example.questionapp.activity.SelectSymptomsActivity.MyFilter01;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.questionapp.R;
import com.example.questionapp.data.HomeItem;
import com.example.questionapp.data.SelectSymptomsItem;

import java.util.List;

public class SelectSymptomsItemAdapter extends BaseAdapter {
    private Context context;
    private List<SelectSymptomsItem> list;
    public SelectSymptomsItemAdapter(Context _context,List<SelectSymptomsItem> _list){
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
                    R.layout.select_symptoms_item, null);
            holder = new ViewHolder();
            holder.cb_selectSymptomsItem_check = (CheckBox) convertView.findViewById(R.id.cb_selectSymptomsItem_check);
            holder.ly_selectSymptomsItem_layout = (LinearLayout) convertView.findViewById(R.id.ly_selectSymptomsItem_layout);
            holder.txt_selectSymptomsItem_title = (TextView) convertView.findViewById(R.id.txt_selectSymptomsItem_title);
            holder.txt_selectSymptomsItem_subTitle = (TextView) convertView.findViewById(R.id.txt_selectSymptomsItem_subTitle);
            // 打标签
            convertView.setTag(holder);

        } else {
            // 从标签中获取数据
            holder = (ViewHolder) convertView.getTag();
        }
        holder.cb_selectSymptomsItem_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setChecked(isChecked);
                Intent intent = new Intent();
                intent.setAction(MyFilter01);
                context.sendBroadcast(intent);
            }
        });

        holder.cb_selectSymptomsItem_check.setChecked(list.get(position).getChecked());


        if(position%2 == 1){
            holder.ly_selectSymptomsItem_layout.setBackground(context.getDrawable(R.drawable.txt_solid_border_purple_blue));
        }else {
            holder.ly_selectSymptomsItem_layout.setBackground(context.getDrawable(R.drawable.txt_solid_border_pink));
        }

        holder.txt_selectSymptomsItem_title.setText(list.get(position).getTitle());
        holder.txt_selectSymptomsItem_subTitle.setText(list.get(position).getSubTitle());


        return convertView;
    }

    class ViewHolder {
        CheckBox cb_selectSymptomsItem_check;
        LinearLayout ly_selectSymptomsItem_layout;
        TextView txt_selectSymptomsItem_title,txt_selectSymptomsItem_subTitle;
    }
}
