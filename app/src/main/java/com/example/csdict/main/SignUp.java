package com.example.csdict.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csdict.DataModels.DataModelUser;
import com.example.csdict.R;

public class SignUp extends AppCompatActivity {

    Button sginup_bt;
    EditText email_ET;
    EditText password_ET;
    EditText name_ET;
    EditText resetq ;
    EditText resetA;
    EditText phone_number_ET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sginup_bt = findViewById(R.id.buttonAcount);
        email_ET = findViewById(R.id.editEmail);
        password_ET = (EditText) findViewById(R.id.editPass);
        name_ET = findViewById(R.id.editName);
        resetq = findViewById(R.id.resert_question);
        resetA = findViewById(R.id.reset_pass_awnser);
        phone_number_ET = findViewById(R.id.editTextPhone);




         sginup_bt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 sginUpBt();
             }
         });
    }

    private void sginUpBt() {

        if (
                email_ET.getText().toString().equals("") ||
                        password_ET.getText().toString().equals("") ||
                        name_ET.getText().toString().equals("") ||
                        resetq.getText().toString().equals("") ||
                        resetA.getText().toString().equals("") ||
                        phone_number_ET.getText().toString().equals("")
        ) {
            Toast.makeText(getApplicationContext(), "please fill all fileds", Toast.LENGTH_SHORT).show();
        } else {

             DataModelUser dataModelUser  = new DataModelUser(name_ET.getText().toString(),"4",
                     email_ET.getText().toString(),
                     password_ET.getText().toString(),
                     phone_number_ET.getText().toString(),
                     resetq.getText().toString(),
                     resetA.getText().toString());


             DBHelper dbHelper = new DBHelper(getApplicationContext());
              boolean state = dbHelper.addUser(dataModelUser);
              if (state){
                  Toast.makeText(getApplicationContext(), "done ", Toast.LENGTH_SHORT).show();
                  Toast.makeText(getApplicationContext(), "you can now sign in", Toast.LENGTH_SHORT).show();
                   email_ET.setText("");
                   password_ET.setText("");
                   name_ET.setText("");
                   resetq .setText("");
                   resetA.setText("");
                   phone_number_ET.setText("");


              }
              else {
                  Toast.makeText(getApplicationContext(), "error in sign up please contact the Admin", Toast.LENGTH_SHORT).show();
              }




            finish();

        }

    }


}
