package com.eric.newsfeed.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.eric.base.ui.BaseFragment;
import com.eric.newsfeed.R;
import com.eric.newsfeed.bean.GankBean;
import com.eric.newsfeed.mvp.presenter.DaggerGankComponent;
import com.eric.newsfeed.mvp.presenter.GankModule;
import com.eric.newsfeed.mvp.presenter.GankPresenter;
import com.eric.newsfeed.mvp.presenter.IGankContract;
import com.eric.newsfeed.ui.NFRecycleAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class GankFragment extends BaseFragment implements IGankContract.IView {

    private RecyclerView recyclerView;

    private SwipeRefreshLayout refreshLayout;

    @Inject
    GankPresenter presenter;

    private Activity activity;

    private NFRecycleAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected int getContentView() {
        return R.layout.refresh_recycle_view;
    }

    @Override
    public void initView(View rootView) {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        refreshLayout = rootView.findViewById(R.id.refresh_layout);
        refreshLayout.setEnabled(true);
        adapter = new NFRecycleAdapter(activity);
        initRecycleView();
        // 利用Dagger进行代码解藕
        DaggerGankComponent.builder()
                .gankModule(new GankModule(this))
                .build()
                .inject(this);
    }

    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();

            }
        });

    }

    @Override
    public void initData() {
        presenter.refresh();

    }


    @Override
    public void endRefresh() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(List<GankBean.ResultData> list) {
        adapter.updateData(list);
        adapter.notifyDataSetChanged();

    }


}
