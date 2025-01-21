package com.example.questionapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

import java.util.HashMap;
import java.util.List;

import static com.example.questionapp.R.id.rb_listItem_1;

public class ListQA3Adapter extends BaseAdapter {
    private String TAG = "ListQA3Adapter";
    private Context context;
    private List<DataList> dataList;
    private String ans;

    public ListQA3Adapter(Context context, List<DataList> dataList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_qa3_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_listQA3Item_question = convertView.findViewById(R.id.txt_listQA3Item_question);
            viewHolder.rg_listQA3Item_listItem = convertView.findViewById(R.id.rg_listQA3Item_listItem);
            viewHolder.rb_listQA3Item_1 = convertView.findViewById(R.id.rb_listQA3Item_1);
            viewHolder.rb_listQA3Item_2 = convertView.findViewById(R.id.rb_listQA3Item_2);
            viewHolder.rb_listQA3Item_3 = convertView.findViewById(R.id.rb_listQA3Item_3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(dataList.get(position).getQuestionTitle().equals("brainFatigueIndex")){
            viewHolder.rb_listQA3Item_1.setText("很少");
            viewHolder.rb_listQA3Item_2.setText("偶爾");
            viewHolder.rb_listQA3Item_3.setText("總是");
        }else if(dataList.get(position).getQuestionTitle().equals("dementia")){
            viewHolder.rb_listQA3Item_1.setText("是");
            viewHolder.rb_listQA3Item_2.setText("不是");
            viewHolder.rb_listQA3Item_3.setText("不知道");
        }


        if (dataList.get(position).getTitleOrContent().equals("title")){
            viewHolder.txt_listQA3Item_question.setText(dataList.get(position).getQuestion());
            viewHolder.txt_listQA3Item_question.setGravity(Gravity.CENTER);
            viewHolder.txt_listQA3Item_question.setTextSize(30);
            //viewHolder.txt_listQA3Item_question.setTextColor(R.color.deep_purple);
            viewHolder.txt_listQA3Item_question.setTextColor(Color.parseColor("#930093"));
            viewHolder.rg_listQA3Item_listItem.setVisibility(View.GONE);
        }else {
            String itemText = dataList.get(position).getQuestion();
            viewHolder.txt_listQA3Item_question.setText(itemText);
            viewHolder.txt_listQA3Item_question.setGravity(Gravity.LEFT);
            viewHolder.txt_listQA3Item_question.setTextSize(20);
            viewHolder.txt_listQA3Item_question.setTextColor(Color.parseColor("#000000"));
            viewHolder.rg_listQA3Item_listItem.setVisibility(View.VISIBLE);

            viewHolder.rg_listQA3Item_listItem.setOnCheckedChangeListener(null);
            viewHolder.rg_listQA3Item_listItem.clearCheck();
            DataList ansInfo = dataList.get(position);
            if (ansInfo.getAns() == 1){
                viewHolder.rb_listQA3Item_1.setChecked(true);
            }else if (ansInfo.getAns() == 2){
                viewHolder.rb_listQA3Item_2.setChecked(true);
            }else if (ansInfo.getAns() == 3){
                viewHolder.rb_listQA3Item_3.setChecked(true);
            }else {
                viewHolder.rg_listQA3Item_listItem.clearCheck();
            }

            View finalConvertView = convertView;
            viewHolder.rg_listQA3Item_listItem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    if(checkedId == R.id.rb_listQA3Item_1){
                        radioGroup.check(R.id.rb_listQA3Item_1);
                        DataList ansInfo = dataList.get(position);
                        ansInfo.setAns(1);
                        notifyDataSetChanged();
                        Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                    }else if(checkedId == R.id.rb_listQA3Item_2){
                        radioGroup.check(R.id.rb_listQA3Item_2);
                        DataList ansInfo = dataList.get(position);
                        ansInfo.setAns(2);
                        notifyDataSetChanged();
                        Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                    }else if(checkedId == R.id.rb_listQA3Item_3){
                        radioGroup.check(R.id.rb_listQA3Item_3);
                        DataList ansInfo = dataList.get(position);
                        ansInfo.setAns(3);
                        notifyDataSetChanged();
                        Log.d(TAG, "onCheckedChanged : "+dataList.get(position).getAns() );
                    }

                }
            });
        }





        return convertView;
    }

    static class ViewHolder {
        TextView txt_listQA3Item_question;
        RadioGroup rg_listQA3Item_listItem;
        RadioButton rb_listQA3Item_1,rb_listQA3Item_2,rb_listQA3Item_3;
    }
}
