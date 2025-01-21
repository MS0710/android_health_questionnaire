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
import com.example.questionapp.data.AnsInfo;
import com.example.questionapp.data.DataList;

import java.util.HashMap;
import java.util.List;

import static com.example.questionapp.R.id.rb_listItem_1;

public class ListAdapter extends BaseAdapter {
    private String TAG = "ListAdapter";
    private Context context;
    private List<DataList> dataList;
    private String ans;
    public HashMap<Integer, String> states = new HashMap<Integer, String>();
    public ListAdapter(Context context, List<DataList> dataList) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_listItem_question = convertView.findViewById(R.id.txt_listItem_question);
            viewHolder.rg_listItem = convertView.findViewById(R.id.rg_listItem);
            viewHolder.rb_listItem_1 = convertView.findViewById(rb_listItem_1);
            viewHolder.rb_listItem_2 = convertView.findViewById(R.id.rb_listItem_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(dataList.get(position).getQuestionTitle().equals("autonomicNervousSystem") ||
                dataList.get(position).getQuestionTitle().equals("herpesZoster") ||
                dataList.get(position).getQuestionTitle().equals("schizophrenia")  ){
            viewHolder.rb_listItem_1.setText("是");
            viewHolder.rb_listItem_2.setText("否");

        }else if(dataList.get(position).getQuestionTitle().equals("parkinsonsDisease") ||
                dataList.get(position).getQuestionTitle().equals("angel")){
            viewHolder.rb_listItem_1.setText("是");
            viewHolder.rb_listItem_2.setText("不是");
        }

        String itemText = dataList.get(position).getQuestion();
        viewHolder.txt_listItem_question.setText(itemText);

        viewHolder.rg_listItem.setOnCheckedChangeListener(null);
        viewHolder.rg_listItem.clearCheck();
        DataList ansInfo = dataList.get(position);
        if (ansInfo.getAns() == 1){
            viewHolder.rb_listItem_1.setChecked(true);
        }else if (ansInfo.getAns() == 2){
            viewHolder.rb_listItem_2.setChecked(true);
        }else {
            viewHolder.rg_listItem.clearCheck();
        }

        viewHolder.rg_listItem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.rb_listItem_1){
                    radioGroup.check(R.id.rb_listItem_1);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(1);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }else if(checkedId == R.id.rb_listItem_2){
                    radioGroup.check(R.id.rb_listItem_2);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(2);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }

            }
        });



        return convertView;
    }

    static class ViewHolder {
        TextView txt_listItem_question;
        RadioGroup rg_listItem;
        RadioButton rb_listItem_1,rb_listItem_2;
    }
}
