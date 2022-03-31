package com.example.csdict.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csdict.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WordProfileAdminView extends AppCompatActivity {


    TextView name;
    TextView app;
    TextView desc;
    TextView id;
    FloatingActionButton bt_fav ;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_profile_admin_view);
        intent = getIntent();
        name = findViewById(R.id.title_message_view);
        desc = findViewById(R.id.appserv12);
        app = findViewById(R.id.appserv1);
        id = findViewById(R.id.id_is);
        bt_fav = findViewById(R.id.add_to_faviorate_user);
        bt_fav.setVisibility(View.INVISIBLE);
        workOnData();


        if (isUser()){
            bt_fav.setVisibility(View.VISIBLE);
            bt_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     addWordToFav();
                }
            });
        }
        else if(!isUser()){
            bt_fav.setVisibility(View.INVISIBLE);

        }

    }


    void workOnData(){
        name.setText(new StringBuilder().append("word name is : ").append(intent.getStringExtra("name").toString()));
        desc.setText(new StringBuilder().append("description is : ").append(intent.getStringExtra("desc").toString()));
        id.setText(new StringBuilder().append("id is : ").append(intent.getStringExtra("id").toString()));
        app.setText(new StringBuilder().append("word observation is : ").append(intent.getStringExtra("app").toString()));


    }


    boolean isUser(){
        if (intent.getStringExtra("user_or_admin").toString().equals("user")){
           return true;
        }
        else {return  false;}
    }


    void addWordToFav(){
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.addWordFav(Integer.parseInt(intent.getStringExtra("id")),Integer.parseInt(intent.getStringExtra("id_user")));

    }
}