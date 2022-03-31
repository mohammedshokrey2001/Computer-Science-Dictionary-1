package com.example.csdict.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csdict.AdminWoork.AdminWork;
import com.example.csdict.DataModels.DataModelUser;
import com.example.csdict.R;

import java.util.ArrayList;

public class SignIn  extends AppCompatActivity {

    Button sign_in;
    Button sign_up;
    EditText email_Edit;
    EditText pass_Edit;
    Button reset_pass ;

    DBHelper dbHelper ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sign_in = findViewById(R.id.btnlogin);
        sign_up = findViewById(R.id.new_acc);
        email_Edit = findViewById(R.id.etemail);
        pass_Edit  = findViewById(R.id.et_pass_in);
        reset_pass = findViewById(R.id.reset_pas);
           dbHelper= new DBHelper(getApplicationContext());

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();
            }
        });



        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_in();
            }
        });


       reset_pass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               resetPssword();
           }
       });
    }

    private void sign_in() {
      if (email_Edit.getText().toString().equals("") ||pass_Edit.getText().toString().equals("")){
          Toast.makeText(getApplicationContext(), "please enter email and password to login or create new one", Toast.LENGTH_SHORT).show();
      }else {
          if (isAdmin()){
              Toast.makeText(getApplicationContext(), "hello Admin", Toast.LENGTH_SHORT).show();
              DBHelper dbHelper = new DBHelper(getApplicationContext());
              Intent intent = new Intent(getApplicationContext(), AdminWork.class);
               startActivity(intent);
          }
          else {
                 signInUser();
          }
      }
        email_Edit.setText("");
        pass_Edit.setText("");
    }

    private void signInUser() {

         DataModelUser modelUser = dbHelper.searchUser(email_Edit.getText().toString(),pass_Edit.getText().toString());

         if (modelUser ==null){
             Toast.makeText(getApplicationContext(), "the mail or password is incorrect ,please make sure or you can reset pass", Toast.LENGTH_LONG).show();


          }
         else{
             Toast.makeText(getApplicationContext(), "you are signed in now", Toast.LENGTH_LONG).show();
             Intent intent = new Intent(getApplicationContext(), UserWork.class);

             ArrayList<String> a = new ArrayList<String>();
             a.add(modelUser.getName());
             a.add(modelUser.getMail());
             a.add(modelUser.getPhone());
             a.add(modelUser.getId());
             a.add(modelUser.getFav());


               intent.putExtra("data",a);

             startActivity(intent);

         }



    }

    private void resetPssword() {

         DataModelUser user = dbHelper.resetPass(email_Edit.getText().toString());
          Intent intent = new Intent(getApplicationContext(),ResetPassword.class);
          intent.putExtra("pass_q",user.getReset_qu());
          intent.putExtra("pass_a",user.getReset_answer());
          intent.putExtra("id" , user.getId());
          startActivity(intent);

    }

    private boolean isAdmin() {

        if (email_Edit.getText().toString().equals("Admin@sys")&&pass_Edit.getText().toString().equals("ad123")){
            return true;
        }
        else {
            return false;
        }
    }

    private void sign_up() {
        Intent intent = new Intent(SignIn.this,SignUp.class);
        startActivity(intent);
    }


}
