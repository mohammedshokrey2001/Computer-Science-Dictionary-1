package com.example.csdict.AdminWoork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csdict.R;

public class UserProfileAdminView extends AppCompatActivity {


    TextView first_name_et;
    TextView last_name_et;
    TextView mail_et;
    TextView phone_et;
    TextView pass_et;
    TextView pass_q_et;
    TextView pass_a_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_admin_view);


        first_name_et = findViewById(R.id.et_first_name_admin_view);
        last_name_et = findViewById(R.id.et_last_name_admin_view);
        mail_et = findViewById(R.id.et_mail_admin);
        phone_et = findViewById(R.id.et_phone_admin_view);
        pass_et = findViewById(R.id.et_passs_admin);
        pass_q_et = findViewById(R.id.et_passs_q_admin);
        pass_a_et = findViewById(R.id.et_pass_a_admin);



     setUserData();

    }




    void setUserData(){

        Intent intent = getIntent();

        first_name_et.setText(intent.getStringExtra("name").toString().split(" ")[0]);
        last_name_et.setText(intent.getStringExtra("name").toString().split(" ")[1]);
        mail_et.setText(intent.getStringExtra("mail").toString());
        phone_et.setText(intent.getStringExtra("phone").toString());
        pass_et.setText(intent.getStringExtra("pass").toString());
        pass_q_et.setText(intent.getStringExtra("pass_q").toString());
        pass_a_et.setText(intent.getStringExtra("pass_a").toString());


    }

}