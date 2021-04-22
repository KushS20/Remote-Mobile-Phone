package com.example.emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Form extends AppCompatActivity {

    EditText Email, Password;
    Button Login, Register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");


        Email = (EditText) findViewById(R.id.Email1);
        Password = (EditText) findViewById(R.id.Password1);
        Login = (Button) findViewById((R.id.btnlogin1));
        DB = new DBHelper(this);



        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String em = Email.getText().toString();
                String pass = Password.getText().toString();

                if(em.equals("")||pass.equals(""))
                    Toast.makeText(Login_Form.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkempass = DB.checkusernamepassword(em, pass);
                    if(checkempass==true){
                        Toast.makeText(Login_Form.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login_Form.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(), Signup_Form.class));
    }
}
