package com.exampleytpeak.kaoqin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exampleytpeak.apps.App;
import com.exampleytpeak.beans.StudentBean;

import org.xutils.ex.DbException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddStuAct extends BaseActivity {

    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.id)
    EditText id;
    @BindView(R.id.btn_login_activity_register)
    Button btnLoginActivityRegister;
    @BindView(R.id.btn_login_activity_login)
    Button btnLoginActivityLogin;

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_add_stu);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        btnLoginActivityRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText().toString())) {
                    Toast.makeText(AddStuAct.this, "Please complete the information", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(id.getText().toString())) {
                    Toast.makeText(AddStuAct.this, "Please complete the information", Toast.LENGTH_SHORT).show();
                    return;
                }
                StudentBean studentBean = new StudentBean();
                studentBean.setId(System.currentTimeMillis() + "");
                studentBean.setName(title.getText().toString());
                studentBean.setStudentid(id.getText().toString().trim());
                studentBean.setCourse(getIntent().getStringExtra("course"));
                studentBean.setZhouci(getIntent().getStringExtra("name"));
                try {
                    App.dbManager.save(studentBean);
                    Toast.makeText(AddStuAct.this, "Successfully saved", Toast.LENGTH_SHORT).show();
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
