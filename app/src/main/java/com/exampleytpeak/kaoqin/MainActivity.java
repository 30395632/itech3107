package com.exampleytpeak.kaoqin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exampleytpeak.apps.App;
import com.exampleytpeak.beans.LoginBean;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.rlt_top)
    RelativeLayout rltTop;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    PlanAdapter planAdapter;

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        planAdapter = new PlanAdapter(this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(planAdapter);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            datas.add("Week " + (i + 1));
        }
        planAdapter.setData(datas);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MainViewHolder> {
        private Context context;
        private List<String> dataBeans = new ArrayList<>();

        public PlanAdapter(Context context) {
            this.context = context;
        }

        public void setData(List list) {
            dataBeans = list;
            notifyDataSetChanged();
        }

        @Override
        public PlanAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_zhouci, parent, false);
            return new PlanAdapter.MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PlanAdapter.MainViewHolder holder, final int position) {
            holder.tv_name.setText("Weekly：" + dataBeans.get(position));
            holder.rlt_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CourserAct.class);
                    intent.putExtra("name", dataBeans.get(position));
                    startActivity(intent);
                }
            });
        }

        public void removeList(int position) {
            dataBeans.remove(position);//删除数据源,移除集合中当前下标的数据
            notifyItemRemoved(position);//刷新被删除的地方
            notifyItemRangeChanged(position, getItemCount()); //刷新被删除数据，以及其后面的数据
        }

        @Override
        public int getItemCount() {
            if (dataBeans != null && dataBeans.size() > 0) {
                return dataBeans.size();
            } else {
                return 0;
            }
        }

        class MainViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_name;
            LinearLayout rlt_root;

            public MainViewHolder(View itemView) {
                super(itemView);
                tv_name = itemView.findViewById(R.id.tv_name);
                rlt_root = itemView.findViewById(R.id.rlt_root);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
