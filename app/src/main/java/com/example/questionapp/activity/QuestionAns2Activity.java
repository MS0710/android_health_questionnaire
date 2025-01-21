package com.example.questionapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.questionapp.R;
import com.example.questionapp.adapter.ListAdapter;
import com.example.questionapp.data.Data;
import com.example.questionapp.data.DataList;
import com.example.questionapp.data.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuestionAns2Activity extends AppCompatActivity {
    private String TAG = "QuestionAns2Activity";
    private TextView txt_qA2_title,txt_qA2_title2;
    private Button btn_qA2_send;
    private ListView lv_QA2_list;
    private ListAdapter listAdapter;
    private List<DataList> dataList;
    private String title,title2,item;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_ans2);
        initView();
    }

    private void initView(){
        title = getIntent().getStringExtra("title");
        title2 = getIntent().getStringExtra("title2");
        item = getIntent().getStringExtra("item");

        txt_qA2_title = (TextView)findViewById(R.id.txt_qA2_title);
        txt_qA2_title2 = (TextView)findViewById(R.id.txt_qA2_title2);
        btn_qA2_send = (Button)findViewById(R.id.btn_qA2_send);

        //txt_qA2_title.setText("自律神經失調量表");
        //txt_qA2_title2.setText("請根據您真實的狀況填寫。");
        txt_qA2_title.setText(title);
        txt_qA2_title2.setText(title2);
        btn_qA2_send.setOnClickListener(onClick);


        lv_QA2_list = findViewById(R.id.lv_QA2_list);
        data = new Data();
        setData();
        // 创建自定义适配器并设置给 ListView
        listAdapter = new ListAdapter(this, dataList);
        lv_QA2_list.setAdapter(listAdapter);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_qA2_send){
                String timeStamp;
                Calendar calendar1 = Calendar.getInstance();
                int year = calendar1.get(Calendar.YEAR);
                int month = calendar1.get(Calendar.MONTH)+1;
                int day = calendar1.get(Calendar.DAY_OF_MONTH);
                Log.d(TAG, "Calendar获取当前日期"+year+"年"+month+"月"+day+"日");
                timeStamp = ""+year+"/"+month+"/"+day;

                if(checkAns()<0){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionAns2Activity.this);
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
        if(item.equals("autonomicNervousSystem")){
            for (int i=0; i<data.autonomicNervousSystem.length; i++){
                dataList.add(new DataList(item,data.autonomicNervousSystem[i],0,""));
            }
        }else if(item.equals("angel")){
            for (int i=0; i<data.angel.length; i++){
                dataList.add(new DataList(item,data.angel[i],0,""));
            }
        }else if(item.equals("parkinsonsDisease")){
            for (int i=0; i<data.parkinsonsDisease.length; i++){
                dataList.add(new DataList(item,data.parkinsonsDisease[i],0,""));
            }
        }else if(item.equals("herpesZoster")){
            for (int i=0; i<data.herpesZoster.length; i++){
                dataList.add(new DataList(item,data.herpesZoster[i],0,""));
            }
        }else if(item.equals("schizophrenia")){
            for (int i=0; i<data.schizophrenia.length; i++){
                dataList.add(new DataList(item,data.schizophrenia[i],0,""));
            }
        }


    }
    private int checkAns(){
        int ansScore = 0;
        //String ans = "";
        int ans = 0;
        if(item.equals("autonomicNervousSystem")){
            for(int i=0; i<data.autonomicNervousSystem.length; i++){
                ans = dataList.get(i).getAns();
                Log.d(TAG, "checkAns: i = "+i+"; ans = "+ans);
                if(ans == 0){
                    return -1;
                }
                if(ans == 1){
                    ansScore = ansScore+1;
                }
            }
        }else if(item.equals("angel")){
            for(int i=0; i<data.angel.length; i++){
                ans = dataList.get(i).getAns();
                if(ans == 0){
                    return -1;
                }
                if(ans == 1){
                    ansScore = ansScore+1;
                }
            }
        }else if(item.equals("parkinsonsDisease")){
            for(int i=0; i<data.parkinsonsDisease.length; i++){
                ans = dataList.get(i).getAns();
                if(ans == 0){
                    return -1;
                }
                if(ans == 1){
                    ansScore = ansScore+1;
                }
            }
        }else if(item.equals("herpesZoster")){
            for(int i=0; i<data.herpesZoster.length; i++){
                ans = dataList.get(i).getAns();
                if(ans == 0){
                    return -1;
                }
                if(ans == 1){
                    ansScore = ansScore+1;
                }
            }
        }else if(item.equals("schizophrenia")){
            for(int i=0; i<data.schizophrenia.length; i++){
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