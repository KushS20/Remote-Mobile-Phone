package com.example.emergencyapp;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msg = null;

            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msg = new SmsMessage[pdus.length];
                    for (int i = 0; i < msg.length; i++) {
                        msg[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        String [] splittedMsg = msg[i].getMessageBody().split("#");
                        String sourceNumber = msg[i].getOriginatingAddress();

                        if (splittedMsg.length > 0 && splittedMsg[0].equals("FIND")) {
                            String number = getContact(context, splittedMsg[1]);
                            SmsManager smsMgrVar = SmsManager.getDefault();
                            String responseMsg = null;
                            if (number != null) {
                                responseMsg = "Contact number of " + splittedMsg[1] + " is " + number;
                                smsMgrVar.sendTextMessage(sourceNumber, null, responseMsg, null, null);
                            } else {
                                responseMsg = "Contact details of " + splittedMsg[1] + " does not exist. ";
                                smsMgrVar.sendTextMessage(sourceNumber, null, responseMsg, null, null);
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getContact(Context context, String name) {
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        String number = null;
        while (cursor.moveToNext()) {
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            if (name.equals(contactName))
                number =  cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        return number;
    }
}
