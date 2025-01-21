package com.example.questionapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
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

public class ListQA5Adapter extends BaseAdapter {
    private String TAG = "ListQA5Adapter";
    private Context context;
    private List<DataList> dataList;
    private String ans;

    public ListQA5Adapter(Context context, List<DataList> dataList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_qa5_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_listQA5Item_question = convertView.findViewById(R.id.txt_listQA5Item_question);
            viewHolder.rg_listQA5Item_listItem = convertView.findViewById(R.id.rg_listQA5Item_listItem);
            viewHolder.rb_listQA5Item_1 = convertView.findViewById(R.id.rb_listQA5Item_1);
            viewHolder.rb_listQA5Item_2 = convertView.findViewById(R.id.rb_listQA5Item_2);
            viewHolder.rb_listQA5Item_3 = convertView.findViewById(R.id.rb_listQA5Item_3);
            viewHolder.rb_listQA5Item_4 = convertView.findViewById(R.id.rb_listQA5Item_4);
            viewHolder.rb_listQA5Item_5 = convertView.findViewById(R.id.rb_listQA5Item_5);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(dataList.get(position).getQuestionTitle().equals("insomnia")){
            viewHolder.rb_listQA5Item_1.setText("從未");
            viewHolder.rb_listQA5Item_2.setText("很少");
            viewHolder.rb_listQA5Item_3.setText("偶爾");
            viewHolder.rb_listQA5Item_4.setText("經常");
            viewHolder.rb_listQA5Item_5.setText("總是");
        }


        String itemText = dataList.get(position).getQuestion();
        viewHolder.txt_listQA5Item_question.setText(itemText);

        viewHolder.rg_listQA5Item_listItem.setOnCheckedChangeListener(null);
        viewHolder.rg_listQA5Item_listItem.clearCheck();
        DataList ansInfo = dataList.get(position);
        if (ansInfo.getAns() == 1){
            viewHolder.rb_listQA5Item_1.setChecked(true);
        }else if (ansInfo.getAns() == 2){
            viewHolder.rb_listQA5Item_2.setChecked(true);
        }else if (ansInfo.getAns() == 3){
            viewHolder.rb_listQA5Item_3.setChecked(true);
        }else if (ansInfo.getAns() == 4){
            viewHolder.rb_listQA5Item_4.setChecked(true);
        }else if (ansInfo.getAns() == 5){
            viewHolder.rb_listQA5Item_5.setChecked(true);
        }else {
            viewHolder.rg_listQA5Item_listItem.clearCheck();
        }

        viewHolder.rg_listQA5Item_listItem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.rb_listQA5Item_1){
                    radioGroup.check(R.id.rb_listQA5Item_1);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(1);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }else if(checkedId == R.id.rb_listQA5Item_2){
                    radioGroup.check(R.id.rb_listQA5Item_2);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(2);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }else if(checkedId == R.id.rb_listQA5Item_3){
                    radioGroup.check(R.id.rb_listQA5Item_3);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(3);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }else if(checkedId == R.id.rb_listQA5Item_4){
                    radioGroup.check(R.id.rb_listQA5Item_4);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(4);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }else if(checkedId == R.id.rb_listQA5Item_5){
                    radioGroup.check(R.id.rb_listQA5Item_5);
                    DataList ansInfo = dataList.get(position);
                    ansInfo.setAns(5);
                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                }

            }
        });




        return convertView;
    }

    static class ViewHolder {
        TextView txt_listQA5Item_question;
        RadioGroup rg_listQA5Item_listItem;
        RadioButton rb_listQA5Item_1,rb_listQA5Item_2,rb_listQA5Item_3,rb_listQA5Item_4,rb_listQA5Item_5;
    }
}
