package com.example.questionapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.questionapp.R;
import com.example.questionapp.data.UserRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinishPageActivity extends AppCompatActivity {
    private String TAG = "FinishPageActivity";
    private Button btn_finishPage_back;
    private TextView txt_finishPage_status,txt_finishPage_suggestion,txt_finishPage_date,
            txt_finishPage_scoreJudgment,txt_finishPage_score;
    private ImageView img_finishPage_picture;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private SharedPreferences getPrefs;
    private int current_score;
    private String item,name,current_date;
    private String last_date;
    private int last_score;
    private boolean dateFlag;
    private boolean timeFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_page);
        initView();
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "WrongConstant"})
    private void initView(){
        dateFlag = true;
        timeFlag = true;
        last_date = "";
        last_score = 0;

        item = getIntent().getStringExtra("item");
        current_score =  getIntent().getIntExtra("current_score",0);
        current_date = getIntent().getStringExtra("current_date");

        Log.d(TAG, "initView: item = "+item);
        Log.d(TAG, "initView: current_score = "+current_score);
        Log.d(TAG, "initView: current_date = "+current_date);

        getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        name = getPrefs.getString("name", "");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(name);
        readExistingData();

        btn_finishPage_back = (Button) findViewById(R.id.btn_finishPage_back);
        txt_finishPage_status = (TextView)findViewById(R.id.txt_finishPage_status);
        txt_finishPage_suggestion = (TextView)findViewById(R.id.txt_finishPage_suggestion);
        txt_finishPage_score = (TextView)findViewById(R.id.txt_finishPage_score);
        txt_finishPage_date = (TextView)findViewById(R.id.txt_finishPage_date);
        txt_finishPage_scoreJudgment = (TextView)findViewById(R.id.txt_finishPage_scoreJudgment);
        img_finishPage_picture = (ImageView)findViewById(R.id.img_finishPage_picture);
        txt_finishPage_score.setText("量測結果為分數: "+current_score);
        btn_finishPage_back.setOnClickListener(onClick);

        checkCurrentResult();
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_finishPage_back){
                finish();
            }

        }
    };

    private void sendResult(String _name, UserRecord userRecord){
        myRef.child(item).setValue(userRecord);
    }

    private void checkCurrentResult(){
        if(item.equals("autonomicNervousSystem")){
            if(current_score<10){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_autonomicNervousSystem_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<20){
                txt_finishPage_status.setText("輕度自律神經失調");
                txt_finishPage_suggestion.setText(getString(R.string.finish_autonomicNervousSystem_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage_status.setText("中/重度自律神經失調");
                txt_finishPage_suggestion.setText(getString(R.string.finish_autonomicNervousSystem_3));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("brainFatigueIndex")){
            if(current_score<10){
                txt_finishPage_status.setText("健康的大腦");
                txt_finishPage_suggestion.setText(getString(R.string.finish_brainFatigueIndex_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<20){
                txt_finishPage_status.setText("輕度/中度腦神經衰弱");
                txt_finishPage_suggestion.setText(getString(R.string.finish_brainFatigueIndex_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage_status.setText("重度腦神經衰弱");
                txt_finishPage_suggestion.setText(getString(R.string.finish_brainFatigueIndex_3));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("angel")){
            if(current_score<3){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_angel_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else {
                txt_finishPage_status.setText("可能有天使綜合症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_angel_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("dementia")){
            if(current_score<2){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_dementia_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else{
                txt_finishPage_status.setText("可能有失智症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_dementia_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("parkinsonsDisease")){
            if(current_score<3){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_parkinsonsDisease_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else{
                txt_finishPage_status.setText("可能有巴金森氏症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_parkinsonsDisease_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("schizophrenia")){
            if(current_score<8){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_schizophrenia_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else{
                txt_finishPage_status.setText("可能有思覺失調症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_schizophrenia_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("insomnia")){
            if(current_score<9){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_insomnia_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<11){
                txt_finishPage_status.setText("輕度失眠症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_insomnia_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage_status.setText("重度失眠症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_insomnia_3));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("hypersomnia")){
            if(current_score<4){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_hypersomnia_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<9){
                txt_finishPage_status.setText("輕度嗜睡症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_hypersomnia_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage_status.setText("重度嗜睡症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_hypersomnia_3));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("depression")){
            if(current_score<9){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_depression_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<28){
                txt_finishPage_status.setText("輕度憂鬱症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_depression_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage_status.setText("重度憂鬱症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_depression_3));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("anxietyDisorder")){
            if(current_score<5){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_anxietyDisorder_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<10){
                txt_finishPage_status.setText("輕度焦慮症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_anxietyDisorder_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage_status.setText("中/重度焦慮症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_anxietyDisorder_3));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("ADHD")){
            if(current_score<17){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_ADHD_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<24){
                txt_finishPage_status.setText("很可能有過動症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_ADHD_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage_status.setText("非常可能有過動症");
                txt_finishPage_suggestion.setText(getString(R.string.finish_ADHD_3));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("herpesZoster")){
            if(current_score<2){
                txt_finishPage_status.setText("正常情況");
                txt_finishPage_suggestion.setText(getString(R.string.finish_herpesZoster_1));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else {
                txt_finishPage_status.setText("疑似帶狀泡疹後神經痛");
                txt_finishPage_suggestion.setText(getString(R.string.finish_herpesZoster_2));
                img_finishPage_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void readExistingData(){
        Log.d(TAG, "readExistingData: ");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String readItem = "";
                String key = "";
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: "+snapshot.toString());
                    key = snapshot.getKey().toString();
                    Log.d(TAG, "onDataChange: key = "+key);
                    if (key.equals(item)){
                        readItem = dataSnapshot.child(item).child("item").getValue().toString();
                        Log.d(TAG, "onDataChange: "+dataSnapshot.child(item).child("date").getValue().toString());
                        Log.d(TAG, "onDataChange: readItem "+readItem);
                        Log.d(TAG, "onDataChange: "+dataSnapshot.child(item).child("score").getValue().toString());
                        if(dateFlag){
                            last_date = dataSnapshot.child(item).child("date").getValue().toString();
                            last_score = Integer.parseInt(dataSnapshot.child(item).child("score").getValue().toString());
                            dateFlag = false;
                        }
                    }
                }
                /*if(readItem.equals("")){
                    Log.d(TAG, "onDataChange: first");
                    txt_finishPage_date.setText("第一次測驗");
                    txt_finishPage_scoreJudgment.setText("");
                }else {
                    Log.d(TAG, "onDataChange: 2");
                    txt_finishPage_date.setText(last_date);
                    //Log.d(TAG, "onDataChange: last_score = "+last_score);
                    if(current_score == last_score){
                        txt_finishPage_scoreJudgment.setText("同分");
                    }else if(current_score > last_score){
                        txt_finishPage_scoreJudgment.setText("上升");
                    }else {
                        txt_finishPage_scoreJudgment.setText("下降");
                    }
                }
                sendResult(name,new UserRecord(item,current_score,current_date));*/

                if(timeFlag){
                    if(dateFlag){
                        txt_finishPage_date.setText("第一次測驗");
                        txt_finishPage_scoreJudgment.setText("");
                    }else {
                        txt_finishPage_date.setText(last_date);
                        //Log.d(TAG, "onDataChange: last_score = "+last_score);
                        if(current_score == last_score){
                            txt_finishPage_scoreJudgment.setText("同分");
                        }else if(current_score > last_score){
                            txt_finishPage_scoreJudgment.setText("上升");
                        }else {
                            txt_finishPage_scoreJudgment.setText("下降");
                        }
                    }
                    sendResult(name,new UserRecord(item,current_score,current_date));
                    timeFlag = false;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}