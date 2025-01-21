package com.example.questionapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.questionapp.R;
import com.example.questionapp.adapter.HomeItemAdapter;
import com.example.questionapp.adapter.SelectSymptomsItemAdapter;
import com.example.questionapp.data.HomeItem;
import com.example.questionapp.data.SelectSymptomsItem;

import java.util.ArrayList;
import java.util.List;

public class SelectSymptomsActivity extends AppCompatActivity {
    private String TAG = "SelectSymptomsActivity";
    //private String[] title = {"自律神經失調","腦神經衰弱","天使綜合症","失智症","巴金森氏症",
    //        "思覺失調症", "失眠症","嗜睡症","憂鬱症","焦慮症","過動症","帶狀泡疹後神經痛",};
    private GridView gv_selectSymptoms_list;
    private SelectSymptomsItemAdapter selectSymptomsItemAdapter;
    private List<SelectSymptomsItem> list;
    private TextView txt_selectSymptoms_selectNum;
    private Button btn_selectSymptoms_ok;
    private int selectNum;
    private BroadcastResult broadcast = new BroadcastResult();
    public static final String MyFilter01 = "action01";

    private List<String> issueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_symptoms);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcast);
    }

    private void initView(){
        /**建立廣播過濾器*/
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyFilter01);
        /**註冊廣播*/
        registerReceiver(broadcast, intentFilter);

        selectNum = 0;
        txt_selectSymptoms_selectNum = (TextView)findViewById(R.id.txt_selectSymptoms_selectNum);
        btn_selectSymptoms_ok = (Button) findViewById(R.id.btn_selectSymptoms_ok);
        btn_selectSymptoms_ok.setOnClickListener(onClick);
        gv_selectSymptoms_list = (GridView) findViewById(R.id.gv_selectSymptoms_list);
        list = new ArrayList<>();
        setData();
        selectSymptomsItemAdapter = new SelectSymptomsItemAdapter(getApplicationContext(),list);
        gv_selectSymptoms_list.setAdapter(selectSymptomsItemAdapter);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btn_selectSymptoms_ok){
                if (selectNum>3){
                    Toast.makeText(getApplicationContext(),"最多選擇3種,請重新選擇",Toast.LENGTH_SHORT).show();
                }else if(selectNum<1){
                    Toast.makeText(getApplicationContext(),"請至少選擇一種症狀",Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onClick: ");
                    Log.d(TAG, "initView: issueList = "+issueList);
                    Intent intent = new Intent(SelectSymptomsActivity.this, AnalysisResultActivity.class);
                    if(issueList.size() < 2){
                        intent.putExtra("selectItem1",issueList.get(0));
                        intent.putExtra("selectItem2","");
                        intent.putExtra("selectItem3","");
                    }else if(issueList.size() < 3){
                        intent.putExtra("selectItem1",issueList.get(0));
                        intent.putExtra("selectItem2",issueList.get(1));
                        intent.putExtra("selectItem3","");
                    }else if(issueList.size() < 4){
                        intent.putExtra("selectItem1",issueList.get(0));
                        intent.putExtra("selectItem2",issueList.get(1));
                        intent.putExtra("selectItem3",issueList.get(2));
                    }
                    startActivity(intent);
                    finish();
                }

            }

        }
    };

    private int totalSelectNum(){
        selectNum = 0;
        issueList.clear();
        for (int i=0; i<list.size() ;i++){
            if(list.get(i).getChecked()){
                selectNum++;
                issueList.add(list.get(i).getTitle());
            }
        }
        return selectNum;
    }

    private void setData(){
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_1),getString(R.string.SelectSymptomsActivity_1_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_2),getString(R.string.SelectSymptomsActivity_2_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_3),getString(R.string.SelectSymptomsActivity_3_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_4),getString(R.string.SelectSymptomsActivity_4_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_5),getString(R.string.SelectSymptomsActivity_5_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_6),getString(R.string.SelectSymptomsActivity_6_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_7),getString(R.string.SelectSymptomsActivity_7_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_8),getString(R.string.SelectSymptomsActivity_8_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_9),getString(R.string.SelectSymptomsActivity_9_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_10),getString(R.string.SelectSymptomsActivity_10_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_11),getString(R.string.SelectSymptomsActivity_11_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_12),getString(R.string.SelectSymptomsActivity_12_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_13),getString(R.string.SelectSymptomsActivity_13_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_14),getString(R.string.SelectSymptomsActivity_14_1),-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_15),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_16),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_17),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_18),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_19),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_20),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_21),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_22),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_23),"",-1,false));
        list.add(new SelectSymptomsItem(getString(R.string.SelectSymptomsActivity_24),"",-1,false));

    }

    private class BroadcastResult extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            /**這邊接收來自Broadcast回傳*/
            String mAction = intent.getAction();
            switch (mAction) {
                case MyFilter01:
                    Log.d(TAG, "onReceive: MyFilter01");
                    totalSelectNum();
                    txt_selectSymptoms_selectNum.setText("最多選擇3種，已選擇 "+selectNum+" 種");
                    break;

            }
        }
    }
}