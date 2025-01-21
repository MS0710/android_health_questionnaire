package com.example.questionapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.questionapp.R;
import com.example.questionapp.adapter.HomeItemAdapter;
import com.example.questionapp.data.HomeItem;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private String TAG = "HomeActivity";
    private String[] title = {"自律神經失調","中風","天使綜合症","失智症","帕金森氏症",
            "思覺失調症", "失眠症","嗜睡症","憂鬱症","焦慮症","過動症","帶狀泡疹後神經痛",};
    private GridView gv_home_list;
    private ImageView img_home_shrimp;
    private HomeItemAdapter homeItemAdapter;
    private List<HomeItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView(){
        gv_home_list = (GridView) findViewById(R.id.gv_home_list);
        list = new ArrayList<>();
        setData();
        homeItemAdapter = new HomeItemAdapter(getApplicationContext(),list);
        gv_home_list.setAdapter(homeItemAdapter);
        gv_home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (list.get(i).getTitle()){
                    case "自律神經失調":
                        //intent = new Intent(getApplicationContext(), QuestionAns2Activity.class);
                        intent = new Intent(getApplicationContext(), QuestionAns3Activity.class);
                        intent.putExtra("title","自律神經失調量表");
                        intent.putExtra("title2","請根據您真實的狀況填寫。");
                        //intent.putExtra("item","autonomicNervousSystem");
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

        img_home_shrimp = (ImageView) findViewById(R.id.img_home_shrimp);
        img_home_shrimp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SelectSymptomsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setData(){
        for (int i=0; i< title.length; i++){
            list.add(new HomeItem(title[i]));
        }
    }
}