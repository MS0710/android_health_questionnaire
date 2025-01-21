package com.example.questionapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager;

import com.example.questionapp.R;
import com.example.questionapp.data.UserRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinishPage2Activity extends AppCompatActivity {
    private String TAG = "FinishPage2Activity";
    private TextView txt_finishPage2_score,txt_finishPage2_status,txt_finishPage2_date,txt_finishPage2_scoreJudgment,
            txt_finishPage2_suggestion;
    private ImageView img_finishPage2_picture;
    private Button btn_finishPage2_back;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private SharedPreferences getPrefs;

    private String item,name,current_date;
    private int current_score;
    private String last_date;
    private int last_score;
    private boolean dateFlag;
    private boolean timeFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_page2);
        initView();
    }

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

        txt_finishPage2_score = (TextView) findViewById(R.id.txt_finishPage2_score);
        txt_finishPage2_status = (TextView) findViewById(R.id.txt_finishPage2_status);
        txt_finishPage2_date = (TextView) findViewById(R.id.txt_finishPage2_date);
        txt_finishPage2_scoreJudgment = (TextView) findViewById(R.id.txt_finishPage2_scoreJudgment);
        txt_finishPage2_suggestion = (TextView) findViewById(R.id.txt_finishPage2_suggestion);
        img_finishPage2_picture = (ImageView) findViewById(R.id.img_finishPage2_picture);
        btn_finishPage2_back = (Button) findViewById(R.id.btn_finishPage2_back);
        txt_finishPage2_score.setText("量測分數為: "+current_score);
        btn_finishPage2_back.setOnClickListener(onClick);

        checkCurrentResult();
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_finishPage2_back){
                finish();
                Intent intent = new Intent(FinishPage2Activity.this, HomeActivity.class);
                startActivity(intent);
            }

        }
    };

    private void sendResult(String _name, UserRecord userRecord){
        myRef.child(item).setValue(userRecord);
    }

    private void checkCurrentResult(){
        if(item.equals("autonomicNervousSystem")){
            if(current_score<10){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_autonomicNervousSystem_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<20){
                txt_finishPage2_status.setText("輕度自律神經失調");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_autonomicNervousSystem_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage2_status.setText("中/重度自律神經失調");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_autonomicNervousSystem_3));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("brainFatigueIndex")){
            if(current_score<10){
                txt_finishPage2_status.setText("健康的大腦");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_brainFatigueIndex_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<20){
                txt_finishPage2_status.setText("輕度/中度腦神經衰弱");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_brainFatigueIndex_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage2_status.setText("重度腦神經衰弱");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_brainFatigueIndex_3));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("angel")){
            if(current_score<3){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_angel_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else {
                txt_finishPage2_status.setText("可能有天使綜合症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_angel_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("dementia")){
            if(current_score<2){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_dementia_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else{
                txt_finishPage2_status.setText("可能有失智症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_dementia_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("parkinsonsDisease")){
            if(current_score<3){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_parkinsonsDisease_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else{
                txt_finishPage2_status.setText("可能有巴金森氏症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_parkinsonsDisease_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("schizophrenia")){
            if(current_score<8){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_schizophrenia_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else{
                txt_finishPage2_status.setText("可能有思覺失調症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_schizophrenia_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("insomnia")){
            if(current_score<9){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_insomnia_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<11){
                txt_finishPage2_status.setText("輕度失眠症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_insomnia_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage2_status.setText("重度失眠症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_insomnia_3));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("hypersomnia")){
            if(current_score<4){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_hypersomnia_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<9){
                txt_finishPage2_status.setText("輕度嗜睡症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_hypersomnia_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage2_status.setText("重度嗜睡症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_hypersomnia_3));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("depression")){
            if(current_score<9){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_depression_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<28){
                txt_finishPage2_status.setText("輕度憂鬱症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_depression_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage2_status.setText("重度憂鬱症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_depression_3));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("anxietyDisorder")){
            if(current_score<5){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_anxietyDisorder_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<10){
                txt_finishPage2_status.setText("輕度焦慮症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_anxietyDisorder_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage2_status.setText("中/重度焦慮症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_anxietyDisorder_3));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("ADHD")){
            if(current_score<17){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_ADHD_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<24){
                txt_finishPage2_status.setText("很可能有過動症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_ADHD_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            }else {
                txt_finishPage2_status.setText("非常可能有過動症");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_ADHD_3));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("herpesZoster")){
            if(current_score<2){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_herpesZoster_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else {
                txt_finishPage2_status.setText("疑似帶狀泡疹後神經痛");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_herpesZoster_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
            }
        }else if(item.equals("stroke")){
            if(current_score<7){
                txt_finishPage2_status.setText("正常情況");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_stroke_1));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.thumbs_up));
            }else if(current_score<16){
                txt_finishPage2_status.setText("輕度中風症狀");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_stroke_2));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.magnifier));
            } else {
                txt_finishPage2_status.setText("明顯中風症狀");
                txt_finishPage2_suggestion.setText(getString(R.string.finish_stroke_3));
                img_finishPage2_picture.setImageDrawable(getDrawable(R.drawable.doctor));
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
                        txt_finishPage2_date.setText("第一次測驗");
                        txt_finishPage2_scoreJudgment.setText("");
                    }else {
                        txt_finishPage2_date.setText("與上次"+last_date+"測驗相比");
                        //Log.d(TAG, "onDataChange: last_score = "+last_score);
                        if(current_score == last_score){
                            txt_finishPage2_scoreJudgment.setText("同分");
                        }else if(current_score > last_score){
                            txt_finishPage2_scoreJudgment.setText("上升");
                        }else {
                            txt_finishPage2_scoreJudgment.setText("進步許多");
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