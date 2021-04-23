package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup_Form extends AppCompatActivity {

    EditText Email, Password, FullName, UserName, Repassword;
    Button Login, Register;



    DBHelper DB;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");

        Email = (EditText) findViewById(R.id.Email);
        UserName = (EditText) findViewById(R.id.UserName);
        FullName = (EditText) findViewById(R.id.FullName);
        Password = (EditText) findViewById(R.id.Password);
        Repassword = (EditText) findViewById(R.id.Repassword);


        Login = (Button) findViewById(R.id.Login);
        Register = (Button) findViewById((R.id.Register));
        DB = new DBHelper(this);

        Register.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = Email.getText().toString();
                String pass = Password.getText().toString();
                String repass = Repassword.getText().toString();
                String user = UserName.getText().toString();
                String full = FullName.getText().toString();


                if(user.equals("")||pass.equals("")||repass.equals("")||em.equals("")||full.equals(""))
                    Toast.makeText(Signup_Form.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(Signup_Form.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(Signup_Form.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Signup_Form.this, "User Already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Signup_Form.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }


            }

        });

        Login.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_Form.class);
                startActivity(intent);
                String user = UserName.getText().toString();
                String pass = Password.getText().toString();
                String repass = Repassword.getText().toString();
            }

        });


    }


}