package com.example.myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SMS_Recevier";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages != null && messages.length > 0){ // 하나라도 들어있는 경우
            String sender = messages[0].getOriginatingAddress(); // 보낸사람의 전화번호
            String contents = messages[0].getMessageBody(); // 메세지 내용

            Log.d(TAG,"sender : "+sender+"\ncontents : "+contents);

            sendToActivity(context,sender,contents);
        }
    }

    private void sendToActivity(Context context, String sender, String contents) {
        Intent intent = new Intent(context,SmsActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                |Intent.FLAG_ACTIVITY_SINGLE_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);

        context.startActivity(intent);
    }


    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];
        
        int smsCount = objs.length;
        for(int i=0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ // M이후에는 format이 필요
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i],format);
            }
            messages[i] = SmsMessage.createFromPdu((byte[])objs[i]); // M 이전
        }
        
        return messages;
    }
}