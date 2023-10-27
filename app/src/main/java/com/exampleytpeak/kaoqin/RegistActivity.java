package com.exampleytpeak.kaoqin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exampleytpeak.apps.App;
import com.exampleytpeak.beans.LoginBean;

import org.xutils.ex.DbException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistActivity extends BaseActivity {


    @BindView(R.id.et_login_activity_zhanghao)
    EditText etLoginActivityZhanghao;
    @BindView(R.id.et_login_activity_password)
    EditText etLoginActivityPassword;
    @BindView(R.id.btn_login_activity_register)
    Button btnLoginActivityRegister;
    @BindView(R.id.btn_login_activity_login)
    Button btnLoginActivityLogin;
    @BindView(R.id.next_confrim)
    EditText nextConfrim;

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_regist);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        //注册按钮
        btnLoginActivityRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zhanghao = etLoginActivityZhanghao.getText().toString().trim();
                String password = etLoginActivityPassword.getText().toString().trim();
                String confrim = nextConfrim.getText().toString().trim();
                if (TextUtils.isEmpty(zhanghao)) {
                    Toast.makeText(RegistActivity.this, "Please enter an account", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegistActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!confrim.equals(password)) {
                    Toast.makeText(RegistActivity.this, "The two password inputs are inconsistent", Toast.LENGTH_SHORT).show();
                    return;
                }
                //存储账号密码，
                LoginBean loginBean = new LoginBean();
                loginBean.setZhanghao(zhanghao);
                loginBean.setPassword(password);
                try {
                    //将账号密码存储到数据局，save为写入数据库的方法
                    App.dbManager.save(loginBean);
                    Toast.makeText(RegistActivity.this, "Confirm successful registration", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
