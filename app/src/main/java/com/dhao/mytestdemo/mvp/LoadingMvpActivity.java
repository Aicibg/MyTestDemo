package com.dhao.mytestdemo.mvp;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhao.mytestdemo.base.BaseActivity;
import com.dhao.mytestdemo.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingMvpActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LoadingMvpActivity.DataAdapter adapter;
    private List<String> dataList;
    private Handler handler = new Handler();

    @Override
    protected void initViews() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("item " + i);
        }

        viewHelpController.showLoading("正在加载中...");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewHelpController.restore();
                adapter = new DataAdapter();
                adapter.addAll(dataList);
                recyclerView.setLayoutManager(new LinearLayoutManager(LoadingMvpActivity.this));
                recyclerView.setAdapter(adapter);
                viewHelpController.showEmpty("没有数据，点击重新获取...", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewHelpController.restore();
                            }
                        }
                );
            }
        }, 1000);
    }


    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolderView> {
        private List<String> lists;

        public DataAdapter() {
            lists = new ArrayList<>();
        }

        @Override
        public DataHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
            return new DataHolderView(view);
        }

        public void addAll(Collection<String> collection) {
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

        public class DataHolderView extends RecyclerView.ViewHolder {
            TextView tvText;

            public DataHolderView(View itemView) {
                super(itemView);
                tvText = (TextView) itemView.findViewById(R.id.tv_item_text);
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_loading_mvp;
    }

    @Override
    protected View getLoadingTargetView() {
        return ButterKnife.findById(this, R.id.recycler_view);
    }
}
