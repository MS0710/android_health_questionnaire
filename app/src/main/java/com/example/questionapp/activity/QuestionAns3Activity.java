package com.example.questionapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.questionapp.R;
import com.example.questionapp.adapter.ListAdapter;
import com.example.questionapp.adapter.ListQA3Adapter;
import com.example.questionapp.data.Data;
import com.example.questionapp.data.DataList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuestionAns3Activity extends AppCompatActivity {
    private String TAG = "QuestionAns3Activity";
    private TextView txt_qA3_title,txt_qA3_title2;
    private Button btn_qA3_send;
    private ListView lv_QA3_list;
    private ListQA3Adapter listAdapter;
    private List<DataList> dataList;
    private Data data;
    private String title,title2,item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_ans3);
        initView();
    }

    private void initView(){
        title = getIntent().getStringExtra("title");
        title2 = getIntent().getStringExtra("title2");
        item = getIntent().getStringExtra("item");
        txt_qA3_title = (TextView)findViewById(R.id.txt_qA3_title);
        txt_qA3_title2 = (TextView)findViewById(R.id.txt_qA3_title2);
        btn_qA3_send = (Button)findViewById(R.id.btn_qA3_send);

        txt_qA3_title.setText(title);
        txt_qA3_title2.setText(title2);
        btn_qA3_send.setOnClickListener(onClick);


        lv_QA3_list = findViewById(R.id.lv_QA3_list);
        data = new Data();
        setData();
        // 创建自定义适配器并设置给 ListView
        listAdapter = new ListQA3Adapter(this, dataList);
        lv_QA3_list.setAdapter(listAdapter);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_qA3_send){
                String timeStamp;
                Calendar calendar1 = Calendar.getInstance();
                int year = calendar1.get(Calendar.YEAR);
                int month = calendar1.get(Calendar.MONTH)+1;
                int day = calendar1.get(Calendar.DAY_OF_MONTH);
                Log.d(TAG, "Calendar获取当前日期"+year+"年"+month+"月"+day+"日");
                timeStamp = ""+year+"/"+month+"/"+day;

                Log.d(TAG, "onClick: checkAns() = "+checkAns());
                if(checkAns()<0){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionAns3Activity.this);
                    alertDialog.setTitle("請填寫全部題目");
                    alertDialog.setMessage("有問題未作答，請填寫全部題目");
                    alertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }else {
                    //Intent intent = new Intent(getApplicationContext(),FinishPageActivity.class);
                    Intent intent = new Intent(getApplicationContext(),FinishPage2Activity.class);
                    intent.putExtra("item",item);
                    intent.putExtra("current_date",timeStamp);
                    intent.putExtra("current_score",checkAns());
                    startActivity(intent);
                    finish();

                }
            }
        }
    };

    private void setData(){
        // 准备数据源，这里以一个字符串列表为例
        dataList = new ArrayList<>();
        if(item.equals("brainFatigueIndex")){
            for (int i=0; i<data.brainFatigueIndex.length; i++){
                dataList.add(new DataList(item,data.brainFatigueIndex[i][1],0,data.brainFatigueIndex[i][0]));
            }
        }else if(item.equals("dementia")){
            for (int i=0; i<data.dementia.length; i++){
                dataList.add(new DataList(item,data.dementia[i],0,""));
            }
        }
    }

    private int checkAns(){
        int ansScore = 0;
        int ans = 0;

        if(item.equals("brainFatigueIndex")){
            for(int i=0; i<data.brainFatigueIndex.length; i++){
                ans = dataList.get(i).getAns();
                Log.d(TAG, "checkAns: i = "+i+"; ans = "+ans);
                if(ans == 0 && data.brainFatigueIndex[i][0].equals("content")){
                    return -1;
                }
                if(ans == 2){
                    ansScore = ansScore+1;
                }else if(ans == 3){
                    ansScore = ansScore+2;
                }
            }
        }else if(item.equals("dementia")){
            for(int i=0; i<data.dementia.length; i++){
                ans = dataList.get(i).getAns();
                if(ans == 0){
                    return -1;
                }
                if(ans == 1){
                    ansScore = ansScore+1;
                }
            }
        }
        return ansScore;
    }
}