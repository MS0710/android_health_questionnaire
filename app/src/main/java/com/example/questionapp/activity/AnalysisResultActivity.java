package com.example.questionapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.questionapp.R;
import com.example.questionapp.adapter.AnalysisResultAdapter;
import com.example.questionapp.adapter.HomeItemAdapter;
import com.example.questionapp.data.HomeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalysisResultActivity extends AppCompatActivity {
    private String TAG = "AnalysisResultActivity";

    String selectItem1,selectItem2,selectItem3;
    private List<String> itemTag = new ArrayList<>();
    private List<String> issueList = new ArrayList<>();

    private GridView gv_analysisResult_list;
    private AnalysisResultAdapter analysisResultAdapter;
    private List<HomeItem> list;
    private Button btn_analysisResult_retry,btn_analysisResult_goHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_result);
        initView();
    }

    private void initView(){
        selectItem1 = getIntent().getStringExtra("selectItem1");
        selectItem2 = getIntent().getStringExtra("selectItem2");
        selectItem3 = getIntent().getStringExtra("selectItem3");
        Log.d(TAG, "initView: selectItem1 = "+selectItem1);
        Log.d(TAG, "initView: selectItem2 = "+selectItem2);
        Log.d(TAG, "initView: selectItem3 = "+selectItem3);
        list = new ArrayList<>();
        setData();

        btn_analysisResult_retry = (Button)findViewById(R.id.btn_analysisResult_retry);
        btn_analysisResult_goHome = (Button)findViewById(R.id.btn_analysisResult_goHome);
        btn_analysisResult_retry.setOnClickListener(onClick);
        btn_analysisResult_goHome.setOnClickListener(onClick);

        gv_analysisResult_list = (GridView) findViewById(R.id.gv_analysisResult_list);
        analysisResultAdapter = new AnalysisResultAdapter(getApplicationContext(),list);
        gv_analysisResult_list.setAdapter(analysisResultAdapter);
        gv_analysisResult_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (list.get(i).getTitle()){
                    case "自律神經失調":
                        //intent = new Intent(getApplicationContext(), QuestionAns2Activity.class);
                        intent = new Intent(getApplicationContext(), QuestionAns3Activity.class);
                        intent.putExtra("title","自律神經失調量表");
                        intent.putExtra("title2","請根據您真實的狀況填寫。");
                        intent.putExtra("item","autonomicNervousSystem");
                        intent.putExtra("item","brainFatigueIndex");
                        startActivity(intent);
                        break;
                    case "中風":
                        /*intent = new Intent(getApplicationContext(), QuestionAns3Activity.class);
                        intent.putExtra("title","大腦疲勞指數量表");
                        intent.putExtra("title2","請根據您過去兩週的狀況填寫。");
                        intent.putExtra("item","brainFatigueIndex");*/
                        intent = new Intent(getApplicationContext(), QuestionAns4Activity.class);
                        intent.putExtra("title","中風指數量表");
                        intent.putExtra("title2","請根據您真實的狀況填寫。");
                        intent.putExtra("item","stroke");
                        startActivity(intent);
                        break;
                    case "天使綜合症":
                        intent = new Intent(getApplicationContext(), QuestionAns2Activity.class);
                        intent.putExtra("title","天使綜合症量表");
                        intent.putExtra("title2","請依照您真實的狀況填寫。");
                        intent.putExtra("item","angel");
                        startActivity(intent);
                        break;
                    case "失智症":
                        intent = new Intent(getApplicationContext(), QuestionAns3Activity.class);
                        intent.putExtra("title","失智症量表");
                        intent.putExtra("title2","請依照您真實的狀況填寫。");
                        intent.putExtra("item","dementia");
                        startActivity(intent);
                        break;
                    case "帕金森氏症":
                        intent = new Intent(getApplicationContext(), QuestionAns2Activity.class);
                        intent.putExtra("title","帕金森氏症量表");
                        intent.putExtra("title2","請依照您真實的狀況填寫。");
                        intent.putExtra("item","parkinsonsDisease");
                        startActivity(intent);
                        break;
                    case "思覺失調症":
                        intent = new Intent(getApplicationContext(), QuestionAns2Activity.class);
                        intent.putExtra("title","思覺失調症量表");
                        intent.putExtra("title2","請依照您真實的狀況填寫。");
                        intent.putExtra("item","schizophrenia");
                        startActivity(intent);
                        break;
                    case "失眠症":
                        intent = new Intent(getApplicationContext(), QuestionAns5Activity.class);
                        intent.putExtra("title","失眠症量表");
                        intent.putExtra("title2","請根據你過去四個星期的睡眠狀況填寫。");
                        intent.putExtra("item","insomnia");
                        startActivity(intent);
                        break;
                    case "嗜睡症":
                        intent = new Intent(getApplicationContext(), QuestionAns4Activity.class);
                        intent.putExtra("title","嗜睡症量表");
                        intent.putExtra("title2","請根據你最近生活中可能會打瞌睡或睡著的情況做填寫。");
                        intent.putExtra("item","hypersomnia");
                        startActivity(intent);
                        break;
                    case "憂鬱症":
                        intent = new Intent(getApplicationContext(), QuestionAns4Activity.class);
                        intent.putExtra("title","憂鬱症量表");
                        intent.putExtra("title2","請根據您真實的狀況填寫。");
                        intent.putExtra("item","depression");
                        startActivity(intent);
                        break;
                    case "焦慮症":
                        intent = new Intent(getApplicationContext(), QuestionAns4Activity.class);
                        intent.putExtra("title","焦慮症量表");
                        intent.putExtra("title2","請根據過去兩個星期，你有多常受以下問題困擾？ ");
                        intent.putExtra("item","anxietyDisorder");
                        startActivity(intent);
                        break;
                    case "過動症":
                        intent = new Intent(getApplicationContext(), QuestionAns5Activity.class);
                        intent.putExtra("title","過動症(ADHD)量表");
                        intent.putExtra("title2","請根據您真實的狀況填寫。");
                        intent.putExtra("item","ADHD");
                        startActivity(intent);
                        break;
                    case "帶狀泡疹後神經痛":
                        intent = new Intent(getApplicationContext(), QuestionAns2Activity.class);
                        intent.putExtra("title","帶狀皰疹後神經痛量表");
                        intent.putExtra("title2","請根據您最近的狀況填寫。");
                        intent.putExtra("item","herpesZoster");
                        startActivity(intent);

                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"position"+i, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btn_analysisResult_retry){
                Log.d(TAG, "onClick: btn_analysisResult_retry");
                finish();
                Intent intent = new Intent(AnalysisResultActivity.this, SelectSymptomsActivity.class);
                startActivity(intent);

            }else if(view.getId() == R.id.btn_analysisResult_goHome){
                Log.d(TAG, "onClick: btn_analysisResult_goHome");
                finish();
                Intent intent = new Intent(AnalysisResultActivity.this, HomeActivity.class);
                startActivity(intent);

            }

        }
    };

    private void setData(){
        itemTag.clear();
        issueList.clear();
        list.clear();
        if(!selectItem1.isEmpty()){
            itemTag.add(selectItem1);
        }
        if(!selectItem2.isEmpty()){
            itemTag.add(selectItem2);
        }
        if(!selectItem3.isEmpty()){
            itemTag.add(selectItem3);
        }
        Log.d(TAG, "initView: itemTag = "+itemTag);

        for (int i=0;i<itemTag.size();i++){
            if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_1))){
                if(!issueList.contains("失智症")){
                    issueList.add("失智症");
                }
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("中風")){
                    issueList.add("中風");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_2))){
                if(!issueList.contains("天使綜合症")){
                    issueList.add("天使綜合症");
                }
                if(!issueList.contains("中風")){
                    issueList.add("中風");
                }
                if(!issueList.contains("帕金森氏症")){
                    issueList.add("帕金森氏症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_3))){
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_4))){
                if(!issueList.contains("失眠症")){
                    issueList.add("失眠症");
                }
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_5))){
                if(!issueList.contains("中風")){
                    issueList.add("中風");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_6))){
                if(!issueList.contains("天使綜合症")){
                    issueList.add("天使綜合症");
                }
                if(!issueList.contains("中風")){
                    issueList.add("中風");
                }
                if(!issueList.contains("帕金森氏症")){
                    issueList.add("帕金森氏症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_7))){
                if(!issueList.contains("帶狀泡疹後神經痛")){
                    issueList.add("帶狀泡疹後神經痛");
                }
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("中風")){
                    issueList.add("中風");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_8))){
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("憂鬱症")){
                    issueList.add("憂鬱症");
                }
                if(!issueList.contains("嗜睡症")){
                    issueList.add("嗜睡症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_9))){
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("憂鬱症")){
                    issueList.add("憂鬱症");
                }
                if(!issueList.contains("失眠症")){
                    issueList.add("失眠症");
                }
                if(!issueList.contains("嗜睡症")){
                    issueList.add("嗜睡症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_10))){
                if(!issueList.contains("失智症")){
                    issueList.add("失智症");
                }
                if(!issueList.contains("過動症")){
                    issueList.add("過動症");
                }
                if(!issueList.contains("中風")){
                    issueList.add("中風");
                }
                if(!issueList.contains("帕金森氏症")){
                    issueList.add("帕金森氏症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_11))){
                if(!issueList.contains("過動症")){
                    issueList.add("過動症");
                }
                if(!issueList.contains("失眠症")){
                    issueList.add("失眠症");
                }
                if(!issueList.contains("嗜睡症")){
                    issueList.add("嗜睡症");
                }
                if(!issueList.contains("思覺失調症")){
                    issueList.add("思覺失調症");
                }
                if(!issueList.contains("憂鬱症")){
                    issueList.add("憂鬱症");
                }
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_12))){
                if(!issueList.contains("失智症")){
                    issueList.add("失智症");
                }
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_13))){
                if(!issueList.contains("思覺失調症")){
                    issueList.add("思覺失調症");
                }
                if(!issueList.contains("過動症")){
                    issueList.add("過動症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_14))){
                if(!issueList.contains("思覺失調症")){
                    issueList.add("思覺失調症");
                }
                if(!issueList.contains("憂鬱症")){
                    issueList.add("憂鬱症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_15))){
                if(!issueList.contains("中風")){
                    issueList.add("中風");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_16))){
                if(!issueList.contains("思覺失調症")){
                    issueList.add("思覺失調症");
                }
                if(!issueList.contains("憂鬱症")){
                    issueList.add("憂鬱症");
                }
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_17))){
                if(!issueList.contains("天使綜合症")){
                    issueList.add("天使綜合症");
                }
                if(!issueList.contains("過動症")){
                    issueList.add("過動症");
                }
                if(!issueList.contains("失眠症")){
                    issueList.add("失眠症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_18))){
                if(!issueList.contains("失智症")){
                    issueList.add("失智症");
                }
                if(!issueList.contains("失眠症")){
                    issueList.add("失眠症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_19))){
                if(!issueList.contains("帕金森氏症")){
                    issueList.add("帕金森氏症");
                }
                if(!issueList.contains("失眠症")){
                    issueList.add("失眠症");
                }
                if(!issueList.contains("嗜睡症")){
                    issueList.add("嗜睡症");
                }
                if(!issueList.contains("思覺失調症")){
                    issueList.add("思覺失調症");
                }
                if(!issueList.contains("憂鬱症")){
                    issueList.add("憂鬱症");
                }
                if(!issueList.contains("焦慮症")){
                    issueList.add("焦慮症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_20))){
                if(!issueList.contains("帕金森氏症")){
                    issueList.add("帕金森氏症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_21))){
                if(!issueList.contains("失智症")){
                    issueList.add("失智症");
                }
                if(!issueList.contains("思覺失調症")){
                    issueList.add("思覺失調症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_22))){
                if(!issueList.contains("失智症")){
                    issueList.add("失智症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_23))){
                if(!issueList.contains("失智症")){
                    issueList.add("失智症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }else if(itemTag.get(i).contains(getString(R.string.SelectSymptomsActivity_24))){
                if(!issueList.contains("失智症")){
                    issueList.add("失智症");
                }
                if(!issueList.contains("憂鬱症")){
                    issueList.add("憂鬱症");
                }
                if(!issueList.contains("自律神經失調")){
                    issueList.add("自律神經失調");
                }
            }
        }
        Log.d(TAG, "initView: issueList = "+issueList);
        for (int i=0; i<issueList.size();i++){
            list.add(new HomeItem(issueList.get(i)));
        }
    }
}