package com.example.questionapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.questionapp.R;
import com.example.questionapp.adapter.ListQA4Adapter;
import com.example.questionapp.adapter.ListQA5Adapter;
import com.example.questionapp.data.Data;
import com.example.questionapp.data.DataList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuestionAns4Activity extends AppCompatActivity {
    private String TAG = "QuestionAns4Activity";
    private TextView txt_qA4_title,txt_qA4_title2;
    private Button btn_qA4_send;
    private ListView lv_QA4_list;
    private ListQA4Adapter listQA4Adapter;
    private List<DataList> dataList;
    private Data data;
    private String title,title2,item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_ans4);
        initView();
    }

    private void initView(){
        title = getIntent().getStringExtra("title");
        title2 = getIntent().getStringExtra("title2");
        item = getIntent().getStringExtra("item");
        txt_qA4_title = (TextView)findViewById(R.id.txt_qA4_title);
        txt_qA4_title2 = (TextView)findViewById(R.id.txt_qA4_title2);
        btn_qA4_send = (Button)findViewById(R.id.btn_qA4_send);

        txt_qA4_title.setText(title);
        txt_qA4_title2.setText(title2);
        btn_qA4_send.setOnClickListener(onClick);


        lv_QA4_list = findViewById(R.id.lv_qA4_list);
        data = new Data();
        setData();
        // 创建自定义适配器并设置给 ListView
        listQA4Adapter = new ListQA4Adapter(this, dataList);
        lv_QA4_list.setAdapter(listQA4Adapter);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_qA4_send){
                String timeStamp;
                Calendar calendar1 = Calendar.getInstance();
                int year = calendar1.get(Calendar.YEAR);
                int month = calendar1.get(Calendar.MONTH)+1;
                int day = calendar1.get(Calendar.DAY_OF_MONTH);
                Log.d(TAG, "Calendar获取当前日期"+year+"年"+month+"月"+day+"日");
                timeStamp = ""+year+"/"+month+"/"+day;

                Log.d(TAG, "onClick: checkAns() = "+checkAns());
                if(checkAns()<0){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionAns4Activity.this);
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
        if(item.equals("hypersomnia")){
            for (int i=0; i<data.hypersomnia.length; i++){
                dataList.add(new DataList(item,data.hypersomnia[i],0,""));
            }
        }else if(item.equals("depression")){
            for (int i=0; i<data.depression.length; i++){
                dataList.add(new DataList(item,data.depression[i],0,""));
            }
        }else if(item.equals("anxietyDisorder")){
            for (int i=0; i<data.anxietyDisorder.length; i++){
                dataList.add(new DataList(item,data.anxietyDisorder[i],0,""));
            }
        }else if(item.equals("stroke")){
            for (int i=0; i<data.stroke.length; i++){
                dataList.add(new DataList(item,data.stroke[i],0,""));
            }
        }
    }

    private int checkAns(){
        int ansScore = 0;
        int ans = 0;
        if(item.equals("hypersomnia")){
            for(int i=0; i<data.hypersomnia.length; i++){
                ans = dataList.get(i).getAns();
                if(ans == 0){
                    return -1;
                }
                if(ans == 2){
                    ansScore = ansScore+1;
                }else if(ans == 3){
                    ansScore = ansScore+2;
                }else if(ans == 4){
                    ansScore = ansScore+3;
                }
            }
        }else if(item.equals("depression")){
            for(int i=0; i<data.depression.length; i++){
                ans = dataList.get(i).getAns();
                if(ans == 0){
                    return -1;
                }
                if(ans == 2){
                    ansScore = ansScore+1;
                }else if(ans == 3){
                    ansScore = ansScore+2;
                }else if(ans == 4){
                    ansScore = ansScore+3;
                }
            }
        }else if(item.equals("anxietyDisorder")){
            for(int i=0; i<data.anxietyDisorder.length; i++){
                ans = dataList.get(i).getAns();
                if(ans == 0){
                    return -1;
                }
                if(ans == 2){
                    ansScore = ansScore+1;
                }else if(ans == 3){
                    ansScore = ansScore+2;
                }else if(ans == 4){
                    ansScore = ansScore+3;
                }
            }
        }else if(item.equals("stroke")){
            for(int i=0; i<data.stroke.length; i++){
                ans = dataList.get(i).getAns();
                if(ans == 0){
                    return -1;
                }
                if(ans == 2){
                    ansScore = ansScore+1;
                }else if(ans == 3){
                    ansScore = ansScore+2;
                }else if(ans == 4){
                    ansScore = ansScore+3;
                }
            }
        }
        return ansScore;
    }
}