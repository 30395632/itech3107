package com.exampleytpeak.kaoqin;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.exampleytpeak.apps.App;
import com.exampleytpeak.beans.LoginBean;

import org.xutils.ex.DbException;

import java.util.List;


public class LoginActivity extends BaseActivity {
    private EditText et_login_activity_zhanghao;//账号
    private EditText et_login_activity_password;//密码
    private Button btn_login_activity_register;//注册
    private Button btn_login_activity_login;//登录
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private static int REQUEST_PERMISSION_CODE = 1;
    private String loginType = "";
    String type = "";

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        et_login_activity_zhanghao = (EditText) findViewById(R.id.et_login_activity_zhanghao);
        et_login_activity_password = (EditText) findViewById(R.id.et_login_activity_password);
        btn_login_activity_register = (Button) findViewById(R.id.btn_login_activity_register);
        btn_login_activity_login = (Button) findViewById(R.id.btn_login_activity_login);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        //登录按钮
        btn_login_activity_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zhanghao = et_login_activity_zhanghao.getText().toString().trim();
                String password = et_login_activity_password.getText().toString().trim();
                if (TextUtils.isEmpty(zhanghao)) {
                    Toast.makeText(LoginActivity.this, "Please enter an account", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //普通用户登录，查询所有的用户信息，进行账号密码对比，
                try {
                    //findall方法为查询方法
                    List<LoginBean> loginBeanList = App.dbManager.findAll(LoginBean.class);
                    if (loginBeanList != null && loginBeanList.size() > 0) {
                        int size = loginBeanList.size();
                        for (int i = 0; i < size; i++) {
                            if (loginBeanList.get(i).getZhanghao().equals(zhanghao)
                                    && loginBeanList.get(i).getPassword().equals(password)
                            ) {
                                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(it);
                                finish();
                                return;
                            }
                        }
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });


        btn_login_activity_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, RegistActivity.class); //你要转向的Activity
                startActivity(it);
            }
        });

    }
}
