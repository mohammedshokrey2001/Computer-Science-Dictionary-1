package com.example.csdict.messageApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csdict.R;

public class MessageProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_profile);

        TextView title  = findViewById(R.id.title_message_view);
        TextView sender  = findViewById(R.id.sender_is_view);
        TextView message  = findViewById(R.id.comtentMessage);
        Intent intent = getIntent();

        title.setText("Title: "+intent.getStringExtra("title"));
        sender.setText("Sender: "+intent.getStringExtra("sender"));
        message.setText("Message:\n "+intent.getStringExtra("message"));


    }
}