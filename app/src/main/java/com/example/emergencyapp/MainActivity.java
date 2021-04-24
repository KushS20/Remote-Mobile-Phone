package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txt_num,txt_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_message = (EditText)findViewById(R.id.txt_message);
        txt_num = (EditText)findViewById(R.id.txt_phonenumber);

//        String [] permissions = {
//                Manifest.permission.RECEIVE_SMS,
//                Manifest.permission.SEND_SMS,
//                Manifest.permission.READ_CONTACTS,
//                Manifest.permission.WRITE_CONTACTS
//        };
//
//        ActivityCompat.requestPermissions(this, permissions, PackageManager.PERMISSION_GRANTED);
    }

    public void btn_send(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){

             MyMessage();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);

        }
    }

    private void MyMessage() {

        String phoneNumber = txt_num.getText().toString().trim();
        String Message = txt_message.getText().toString().trim();

        if (!txt_num.getText().toString().equals("") || !txt_message.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, Message, null, null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this,"Please Enter Number or message",Toast.LENGTH_SHORT).show();
        }

    }
}


