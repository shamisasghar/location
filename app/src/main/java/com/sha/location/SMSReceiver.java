package com.sha.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by SHA on 22/8/2017.
 */

public class SMSReceiver extends BroadcastReceiver {
    String str = "";
    private static final int CAMERA_REQUEST = 1888;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;

        //Toast.makeText(context,"hello", Toast.LENGTH_SHORT).show();
       if (bundle != null)
        {
            // Retrieve the SMS.
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++)
            {
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                // In case of a particular App / Service.
                //if(msgs[i].getOriginatingAddress().equals("+91XXX"))
                //{
               // str += "SMS from " + msgs[i].getOriginatingAddress();
                //str += " :";
                str += msgs[i].getMessageBody().toString();
                //}
            }

            Intent i=new Intent();

            i.setClass(context.getApplicationContext(),Camera.class);
            context.startActivity(i);
            if(str=="camera")
            // Display the SMS as Toast.
            {
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        }



    }


}
