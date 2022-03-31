package com.example.csdict.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csdict.R;

public class ResetPassword extends AppCompatActivity {


    EditText answer ;
    Button ok ;
    String id;
    Button resett ;
    TextView pass_q_textedit;
    String answerr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ok = findViewById(R.id.reset_now_with_new);
        answer = findViewById(R.id.editTextAnswerQ);
        pass_q_textedit = findViewById(R.id.password_q_display);
        resett = findViewById(R.id.final_reset);

        resetOne();

        resett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnewpass();
            }
        });


    }

    private void resetOne() {
        Intent intent = getIntent();
        answerr = intent.getStringExtra("pass_a");
        id = intent.getStringExtra("id");
        pass_q_textedit.setText(intent.getStringExtra("pass_q").toString());

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "please answer the question to reset pass", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (answer.getText().toString().equals(answerr)){
                        Toast.makeText(getApplicationContext(), "please enter now the new password", Toast.LENGTH_SHORT).show();



                    }

                    else {
                        Toast.makeText(getApplicationContext(), "please contact Admin your answer not correct", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

    }

   void  setnewpass( ){

        DBHelper dbHelper = new DBHelper(getApplicationContext());
                        boolean flag = dbHelper.set_new_pass(answer.getText().toString(),Integer.parseInt(id));

                        if (flag){
                            Toast.makeText(getApplicationContext(), "reset pass could not be done", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "reset pass done", Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(getApplicationContext(),SignIn.class);
                            startActivity(intent1);
                        }
   }

}