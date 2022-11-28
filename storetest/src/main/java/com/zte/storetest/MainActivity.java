package com.zte.storetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private EditText nameET,numberET;
    private RadioButton sexRB;
    private Button spButton,fileButton,dbButton,exButton;
    private String name,sex,num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = (EditText) findViewById(R.id.name);
        numberET = (EditText) findViewById(R.id.number);
        sexRB =(RadioButton) findViewById(R.id.male);
        spButton =(Button)findViewById(R.id.shared);
        fileButton =(Button)findViewById(R.id.file);
        dbButton =(Button)findViewById(R.id.database);
        exButton =(Button)findViewById(R.id.external);

        String name =nameET.getText().toString();
        String sex =sexRB.toString();
        String num=numberET.getText().toString();

        fileButton.setOnClickListener(view -> filestore());
        spButton.setOnClickListener(view -> spstore());
        dbButton.setOnClickListener(view -> dbstore());
        exButton.setOnClickListener(view -> exstore());

    }



    private void filestore(){
        /*
        * new、初始化、写、
        * data/data/包名/files
        */

        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput("in_file.txt",Context.MODE_PRIVATE);
            outputStream.write(data);
            Toast.makeText(this, "文件内部存储保存成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



//        FileInputStream  inputStream = null;
//        try {
//            inputStream = openFileInput("in_file.txt");
//            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

    }
    private void spstore(){
        /*
         * 存数据:
         * 1.获得SharedPreferences的实例 sp_name是文件名
         * 2.获得Editor实例,设置参数
         * 3.写入数据,apply()是异步,commit()是同步
         * 4./data/data/"app package name"/shared_prefs/sp_name.xml验证
         */

        SharedPreferences sp = getSharedPreferences("sp_test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.putString("sex", sex);
        editor.putString("num", num);
        editor.apply();
        Toast.makeText(this,"sp存储数据成功",Toast.LENGTH_LONG).show();


//        String info = sp.getString("name", "");
//        mShowText.setText(info);
//        Toast.makeText(this,"读取数据成功",Toast.LENGTH_LONG).show();
    }
    private void dbstore(){}
    private void exstore(){
        /*
        * 公共且随应用卸载而删除，路径一：storage/sdcard/Android/data/package/files/xxx
        * 路径二：storage/sdcard/xxx/xxx  与内部存储一样
        */

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) { //sd卡处于挂载状态
            String fileName = "";
            File externalFilesDir = this.getExternalFilesDir(null);//目录
            File file = new File(externalFilesDir, fileName);//文件
            //开始写文件
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(content.getBytes("UTF-8"));

                Toast.makeText(this, "文件外部存储保存成功", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Toast.makeText(this, "找不到指定的SD卡", Toast.LENGTH_SHORT).show();
        }

    }
    private void cpstore(){
        Log.d("sssssss","aaaaaaaaaaaa");
    }



}