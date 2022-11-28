package com.example.tintlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

public class RoundImageView extends AppCompatImageView {

    private static final String TAG = "RoundImageView";
    private float mRadius;
    private float mBorderWidth;
    private boolean mShowBorder;

    public RoundImageView(Context context) {
        this(context,null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RoundImageView(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.RoundImage_Style);
        //一般默认值设置成常量,name_属性
        mRadius = typedArray.getDimension(R.styleable.RoundImage_Style_radius,0);
        mBorderWidth = typedArray.getDimension(R.styleable.RoundImage_Style_borderWidth,0);
        mShowBorder = typedArray.getBoolean(R.styleable.RoundImage_Style_showBorder,false);
        Log.d(TAG,"mRadius -- > " + mRadius);
        Log.d(TAG,"mBorderWidth -- > " + mBorderWidth);
        Log.d(TAG,"mShowBorder -- > " + mShowBorder);
        typedArray.recycle();
    }
}


