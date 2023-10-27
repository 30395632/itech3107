package com.exampleytpeak.kaoqin;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.exampleytpeak.apps.App;
import com.exampleytpeak.beans.CourseBean;

import org.xutils.ex.DbException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCourseAct extends BaseActivity {


    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.starttime)
    TextView starttime;
    @BindView(R.id.endtime)
    TextView endtime;
    @BindView(R.id.btn_login_activity_register)
    Button btnLoginActivityRegister;
    @BindView(R.id.btn_login_activity_login)
    Button btnLoginActivityLogin;

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_add_course);
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
                    Toast.makeText(AddCourseAct.this, "Please complete the information", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(starttime.getText().toString())) {
                    Toast.makeText(AddCourseAct.this, "Please complete the information", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(endtime.getText().toString())) {
                    Toast.makeText(AddCourseAct.this, "Please complete the information", Toast.LENGTH_SHORT).show();
                    return;
                }
                CourseBean courseBean = new CourseBean();
                courseBean.setId(System.currentTimeMillis() + "");
                courseBean.setName(title.getText().toString());
                courseBean.setStarttime(starttime.getText().toString());
                courseBean.setEnttime(endtime.getText().toString());
                courseBean.setZhouci(getIntent().getStringExtra("name"));
                try {
                    App.dbManager.saveOrUpdate(courseBean);
                    Toast.makeText(AddCourseAct.this, "Successfully saved", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });


        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar selectedDate = Calendar.getInstance();
                Calendar startDate = Calendar.getInstance();
                Calendar c = Calendar.getInstance();
                c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                int mYear = c.get(Calendar.YEAR); // 获取当前年份
                int mMonth = c.get(Calendar.MONTH);// 获取当前月份
                int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
                int mHour = c.get(Calendar.HOUR_OF_DAY);//时
                int mMinute = c.get(Calendar.MINUTE);//分
                int mSecond = c.get(Calendar.SECOND);//秒
                startDate.set(mYear, mMonth, mDay, 0, 0, 0);//起始时间
                selectedDate.set(1999, 12, 31, 0, 0, 0);//起始时间
                Calendar endDate = Calendar.getInstance();
                endDate.set(2099, 12, 31);//结束时间
                TimePickerView pvTime = new TimePickerBuilder(AddCourseAct.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        starttime.setText(getTimes(date));

                    }
                })
                        .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                        .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                        .addOnCancelClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                        .setLineSpacingMultiplier(2.0f)
                        .isAlphaGradient(true)
                        .setDate(startDate)
                        .setContentTextSize(16)
                        .setRangDate(selectedDate, endDate)
                        .setLabel("", "", "", "时", "分", "秒")
                        .build();
                Dialog mDialog = pvTime.getDialog();
                if (mDialog != null) {

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.BOTTOM);

                    params.leftMargin = 0;
                    params.rightMargin = 0;
                    pvTime.getDialogContainerLayout().setLayoutParams(params);

                    Window dialogWindow = mDialog.getWindow();
                    if (dialogWindow != null) {
                        dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                        dialogWindow.setDimAmount(0.3f);
                    }
                }
                pvTime.show();
            }
        });


        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar selectedDate = Calendar.getInstance();
                Calendar startDate = Calendar.getInstance();
                Calendar c = Calendar.getInstance();
                c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                int mYear = c.get(Calendar.YEAR); // 获取当前年份
                int mMonth = c.get(Calendar.MONTH);// 获取当前月份
                int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
                int mHour = c.get(Calendar.HOUR_OF_DAY);//时
                int mMinute = c.get(Calendar.MINUTE);//分
                int mSecond = c.get(Calendar.SECOND);//秒
                startDate.set(mYear, mMonth, mDay, 0, 0, 0);//起始时间
                selectedDate.set(1999, 12, 31, 0, 0, 0);//起始时间
                Calendar endDate = Calendar.getInstance();
                endDate.set(2099, 12, 31);//结束时间
                TimePickerView pvTime = new TimePickerBuilder(AddCourseAct.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        endtime.setText(getTimes(date));

                    }
                })
                        .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                        .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                        .addOnCancelClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                        .setLineSpacingMultiplier(2.0f)
                        .isAlphaGradient(true)
                        .setDate(startDate)
                        .setContentTextSize(16)
                        .setRangDate(selectedDate, endDate)
                        .setLabel("", "", "", "时", "分", "秒")
                        .build();
                Dialog mDialog = pvTime.getDialog();
                if (mDialog != null) {

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.BOTTOM);

                    params.leftMargin = 0;
                    params.rightMargin = 0;
                    pvTime.getDialogContainerLayout().setLayoutParams(params);

                    Window dialogWindow = mDialog.getWindow();
                    if (dialogWindow != null) {
                        dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                        dialogWindow.setDimAmount(0.3f);
                    }
                }
                pvTime.show();
            }
        });
    }

    private String getTimes(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
