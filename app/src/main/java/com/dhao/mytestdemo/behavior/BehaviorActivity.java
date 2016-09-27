package com.dhao.mytestdemo.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dhao.mytestdemo.base.BaseActivity;
import com.dhao.mytestdemo.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

public class BehaviorActivity extends BaseActivity {
    private List<String> dataList;
    DataAdapter adapter;

    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.bt_click)
    Button btClick;
    @BindView(R.id.activity_behavior)
    CoordinatorLayout activityBehavior;
    @BindView(R.id.recycler_list)
    RecyclerView recyclerList;

    @Override
    protected void initViews() {
        dataList=new ArrayList<>();
         for(int i=0;i<20;i++){
            dataList.add("item "+i);
         }

        adapter=new DataAdapter();
        adapter.addAll(dataList);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        recyclerList.setAdapter(adapter);
    }

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolderView>{
        private List<String> lists;

        public DataAdapter() {
            lists=new ArrayList<>();
        }

        @Override
        public DataHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
            return new DataHolderView(view);
        }

        public void addAll(Collection<String> collection){
            lists.addAll(collection);
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(DataHolderView holder, int position) {
            holder.tvText.setText(lists.get(position));
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        public class DataHolderView extends RecyclerView.ViewHolder{
             TextView tvText;

            public DataHolderView(View itemView) {
                super(itemView);
                tvText= (TextView) itemView.findViewById(R.id.tv_item_text);
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_behavior;
    }

}
