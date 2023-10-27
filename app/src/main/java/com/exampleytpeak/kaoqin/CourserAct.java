package com.exampleytpeak.kaoqin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exampleytpeak.apps.App;
import com.exampleytpeak.beans.CourseBean;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourserAct extends BaseActivity {

    @BindView(R.id.tv_add_course)
    TextView tvAddCourse;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_starttime)
    TextView tvStarttime;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.tv_add)
    Button tvAdd;
    @BindView(R.id.tv_delete)
    Button tvDelete;
    List<CourseBean> datas = new ArrayList<>();
    @BindView(R.id.tv_kaoqin)
    Button tvKaoqin;

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_courser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            List<CourseBean> loginBeanList = App.dbManager.findAll(CourseBean.class);
            if (loginBeanList != null) {
                for (CourseBean gonggaoBean : loginBeanList) {
                    if (gonggaoBean.getZhouci().equals(getIntent().getStringExtra("name"))) {
                        datas.add(gonggaoBean);
                    }
                }
            }
            if (datas.size() > 0) {
                tvName.setText("Course Name：" + datas.get(0).getName());
                tvStarttime.setText("time-on：" + datas.get(0).getStarttime());
                tvEndtime.setText("End Time：" + datas.get(0).getEnttime());
                tvDelete.setVisibility(View.VISIBLE);
                tvAdd.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvStarttime.setVisibility(View.VISIBLE);
                tvEndtime.setVisibility(View.VISIBLE);
                tvKaoqin.setVisibility(View.VISIBLE);
            } else {
                tvDelete.setVisibility(View.GONE);
                tvAdd.setVisibility(View.GONE);

                tvName.setVisibility(View.GONE);
                tvStarttime.setVisibility(View.GONE);
                tvEndtime.setVisibility(View.GONE);
                tvKaoqin.setVisibility(View.GONE);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        tvAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size() > 0) {
                    Toast.makeText(CourserAct.this, "The course of this week already exists", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(CourserAct.this, AddCourseAct.class);
                    intent.putExtra("name", getIntent().getStringExtra("name"));
                    startActivity(intent);
                }
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(CourserAct.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                    App.dbManager.delete(datas.get(0));
                    datas.clear();
                    onResume();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourserAct.this, StuAct.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("course", datas.get(0).getName());
                startActivity(intent);
            }
        });


        tvKaoqin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourserAct.this, Kaoqinct.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("course", datas.get(0).getName());
                startActivity(intent);
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
