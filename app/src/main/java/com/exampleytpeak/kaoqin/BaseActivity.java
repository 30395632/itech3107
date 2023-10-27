package com.exampleytpeak.kaoqin;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initLayout();
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();

    }

    public abstract void initLayout();

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();
}
