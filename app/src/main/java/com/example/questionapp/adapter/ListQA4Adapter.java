package com.example.questionapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.questionapp.R;
import com.example.questionapp.data.DataList;

import java.util.List;

public class ListQA4Adapter extends BaseAdapter {
    private String TAG = "ListQA5Adapter";
    private Context context;
    private List<DataList> dataList;
    private String ans;

    public ListQA4Adapter(Context context, List<DataList> dataList) {
        ans = "";
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_qa4_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_listQA4Item_question = convertView.findViewById(R.id.txt_listQA4Item_question);
            viewHolder.rg_listQA4Item_listItem = convertView.findViewById(R.id.rg_listQA4Item_listItem);
            viewHolder.rb_listQA4Item_1 = convertView.findViewById(R.id.rb_listQA4Item_1);
            viewHolder.rb_listQA4Item_2 = convertView.findViewById(R.id.rb_listQA4Item_2);
            viewHolder.rb_listQA4Item_3 = convertView.findViewById(R.id.rb_listQA4Item_3);
            viewHolder.rb_listQA4Item_4 = convertView.findViewById(R.id.rb_listQA4Item_4);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        String itemText = dataList.get(position).getQuestion();
        viewHolder.txt_listQA4Item_question.setText(itemText);

        viewHolder.rg_listQA4Item_listItem.setOnCheckedChangeListener(null);
        viewHolder.rg_listQA4Item_listItem.clearCheck();
        DataList ansInfo = dataList.get(position);
        if (ansInfo.getAns() == 1){
            viewHolder.rb_listQA4Item_1.setChecked(true);
        }else if (ansInfo.getAns() == 2){
            viewHolder.rb_listQA4Item_2.setChecked(true);
        }else if (ansInfo.getAns() == 3){
            viewHolder.rb_listQA4Item_3.setChecked(true);
        }else if (ansInfo.getAns() == 4){
            viewHolder.rb_listQA4Item_4.setChecked(true);
        }else {
            viewHolder.rg_listQA4Item_listItem.clearCheck();
        }

        viewHolder.rg_listQA4Item_listItem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.rb_listQA4Item_1){
                    radioGroup.check(R.id.rb_listQA4Item_1);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(1);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }else if(checkedId == R.id.rb_listQA4Item_2){
                    radioGroup.check(R.id.rb_listQA4Item_2);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(2);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }else if(checkedId == R.id.rb_listQA4Item_3){
                    radioGroup.check(R.id.rb_listQA4Item_3);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(3);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }else if(checkedId == R.id.rb_listQA4Item_4){
                    radioGroup.check(R.id.rb_listQA4Item_4);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(4);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }

            }
        });




        return convertView;
    }

    static class ViewHolder {
        TextView txt_listQA4Item_question;
        RadioGroup rg_listQA4Item_listItem;
        RadioButton rb_listQA4Item_1,rb_listQA4Item_2,rb_listQA4Item_3,rb_listQA4Item_4;
    }
}
