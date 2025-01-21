package com.example.questionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.questionapp.activity.AnalysisResultActivity;
import com.example.questionapp.activity.FinishPage2Activity;
import com.example.questionapp.activity.HomeActivity;
import com.example.questionapp.activity.SelectSymptomsActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    //private FirebaseDatabase database;
    //private DatabaseReference myRef;
    private Button btn_main_signIn;
    private TextInputLayout textInput_main_name_layout;
    private EditText edit_main_name;
    private SharedPreferences getPrefs;
    private SharedPreferences.Editor editor;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("UserInfo");
        //myRef.child("user"+0).setValue("123");
        getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //getPrefs.getString("name", "");
        name = getPrefs.getString("name", "");

        btn_main_signIn = (Button)findViewById(R.id.btn_main_signIn);
        textInput_main_name_layout = (TextInputLayout) findViewById(R.id.textInput_main_name_layout);
        edit_main_name = (EditText)findViewById(R.id.edit_main_name);
        btn_main_signIn.setOnClickListener(onClick);

        if(!name.equals("")){
            edit_main_name.setText(name);
        }
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btn_main_signIn){
                Log.d(TAG, "onClick: btn_main_signIn");
                name = edit_main_name.getText().toString();
                if(checkEditViewEmpty()){
                    Log.d(TAG, "onClick: OK");
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    editor = getPrefs.edit();
                    editor.putString("name", name);
                    editor.apply();
                    startActivity(intent);

                    //Intent intent = new Intent(getApplicationContext(), SelectSymptomsActivity.class);
                    //Intent intent = new Intent(getApplicationContext(), AnalysisResultActivity.class);
                    //startActivity(intent);
                }else {
                    Log.d(TAG, "onClick: Fail");
                }

            }
        }
    };

    private boolean checkEditViewEmpty(){
        if(TextUtils.isEmpty(name)){
            textInput_main_name_layout.setError("請輸入名稱");
            return false;
        }else {
            textInput_main_name_layout.setError("");
            return true;
        }
    }
}