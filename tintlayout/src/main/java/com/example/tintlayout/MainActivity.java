package com.example.tintlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout ll=findViewById(R.id.kuang);
        ColorStateList colorStateList = ContextCompat.getColorStateList(this, R.color.color_selector);
        Drawable originBitmapDrawable = ContextCompat.getDrawable(this,
                R.drawable.ic_icon_kefu);


        ImageView img=ll.findViewById(R.id.icon);
        img.setBackground(originBitmapDrawable);
        img.setBackgroundTintList(colorStateList);


        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.setActivated(!ll.isActivated());
            }
        });


    }
}