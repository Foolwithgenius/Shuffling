package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;


import com.example.myapplication.adapter.HomeListAdapter;
import com.example.myapplication.bese.BaseRecycleAdapter;
import com.example.myapplication.tool.HomeHandler;
import com.example.myapplication.view.HeaderBannerView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HomeHandler handler;
    private HeaderBannerView headerView;
    private LinkedList<String> list = new LinkedList<String>();
    private BaseRecycleAdapter mBaseAdapter;
    private HomeListAdapter mAdapter;

    public static final int SCROLL_VIEWPAGER_HOME = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new HomeHandler();
        handler.setHomeContext(this);
        headerView = new HeaderBannerView(this);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
         mAdapter = new HomeListAdapter(this);
         mBaseAdapter = new BaseRecycleAdapter(mAdapter);
        mBaseAdapter.adjustSpanSize(mRecyclerView);
        mRecyclerView.setAdapter(mBaseAdapter);
        mBaseAdapter.addHeaderView(headerView);
        initData();
    }

    private void initData() {
        for (int x = 0; x < 20; x++) {
            list.add("我是虚拟数据"+x);
        }
        mAdapter.update(list);
        headerView.setData(list);
        mBaseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeMessages(SCROLL_VIEWPAGER_HOME);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(SCROLL_VIEWPAGER_HOME, 3000);
    }

    public void onPagerChange() {
        headerView.showNextPage();
    }


}
