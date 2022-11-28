package com.example.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyService";
    private final String ACTION_BOOT = "com.example.components.MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        if(ACTION_BOOT.equals(intent.getAction()))
            Toast.makeText(context, "收到广播啦~",Toast.LENGTH_SHORT).show();
    }
}