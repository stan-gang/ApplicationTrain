package com.example.components;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private Button bt7;
    private Button bt8;
    private EditText et;

    MyService.MyBinder myBinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission(this);
        setContentView(R.layout.activity_main);
        initView();

        bt1.setOnClickListener(view -> {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setClass(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        });

        bt2.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SecAction");
            startActivity(intent);
        });

        bt3.setOnClickListener(view -> {
            Intent startIntent=new Intent(this,MyService.class);
            startService(startIntent);
        });

        bt4.setOnClickListener(view -> {
            Intent stopIntent=new Intent(this,MyService.class);
            stopService(stopIntent);
        });

        bt5.setOnClickListener(view -> {
            Intent intent=new Intent("com.example.components.MY_BROADCAST");
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
        });

        bt7.setOnClickListener(view -> insertMsg());
        //bt8.setOnClickListener(view -> getMsgs());

    }


    void initView(){
        bt1 = findViewById(R.id.startactivity1);
        bt2 = findViewById(R.id.startactivity2);
        bt3 = findViewById(R.id.startservice);
        bt4 = findViewById(R.id.stopservice);
        bt5 = findViewById(R.id.sendbroadcast);
        bt6 = findViewById(R.id.receivecbroadcast);
        bt7 = findViewById(R.id.resolver);
        bt8 = findViewById(R.id.provider);
        et = findViewById(R.id.show);

    }

    private void insertMsg() {
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        ContentValues conValues = new ContentValues();
//        Cursor cursor = resolver.query(uri, null, null, null, null);
//        @SuppressLint("Range") String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//        @SuppressLint("Range") String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        conValues.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, "张三");
        conValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER,"1234456");

        resolver.insert(uri, conValues);
        Log.e("HeHe", "短信插入完毕~");
    }

//    private void getMsgs(){
//        Uri uri = Uri.parse("content://sms");
//        ContentResolver resolver = getContentResolver();
//        //获取的是哪些列的信息
//        Cursor cursor= resolver.query(uri, null, null, null, null);
//        if(cursor.moveToNext()){
//            String msg = "";
//            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
//            @SuppressLint("Range") String body = cursor.getString(cursor.getColumnIndex("body"));
//            msg = address + " " + body;
//
//            Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
//        }
//        cursor.close();
//    }

    private void checkPermission(Activity activity) {
        // Storage Permissions

        List<String> permissions = new ArrayList<>();
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_CONTACTS)) {
            permissions.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA)) {
            permissions.add(android.Manifest.permission.CAMERA);
        }
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.RECORD_AUDIO)) {
            permissions.add(android.Manifest.permission.RECORD_AUDIO);
        }
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_PHONE_STATE)) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }




        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                "android.permission.READ_CONTACTS","android.permission.WRITE_CONTACTS"
        };

        try {
            //检测是否有读短信箱的权限
            int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                    "android.permission.READ_CONTACTS");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有读短信箱的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}