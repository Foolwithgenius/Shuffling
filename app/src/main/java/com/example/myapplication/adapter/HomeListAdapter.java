package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.myapplication.R;

import java.util.LinkedList;

/**
 * Created by lipeng on 2016/8/3.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.MyViewHolder
        > {
    private OnItemClickLitener mOnItemClickLitener;
    private Context mContext;
    private LinkedList<String> mDatas = new LinkedList<String>();

    public static final int TYPE_RANKING_ONE = 1;
    public static final int TYPE_RANKING_TWO = 2;

    public interface OnItemClickLitener {
        void onItemClick(int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public HomeListAdapter(Context context) {
        this.mContext = context;
    }

    public void update(LinkedList<String> datas) {
        if (datas != null) {
            mDatas.clear();
            this.mDatas.addAll(datas);
        }
    }

    public void appendData(LinkedList<String> datas) {
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
            return TYPE_RANKING_ONE;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch (viewType) {
            case TYPE_RANKING_ONE:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_recycleview_one, parent, false);
                break;
        }
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.showData(mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mChannelName, mCount;
        private LinearLayout mMore;
        private RecyclerView recyclerView;
        private RecyclerView mRecyclerView;
        private LinearLayout mLayoutll;

        public MyViewHolder(View itemView) {
            super(itemView);
            findView(itemView);
        }

        private void findView(View itemView) {
            mTitle = (TextView) itemView.findViewById(R.id.text_one);
        }

        public void showData(String data, final int position) {
            mTitle.setText(data);
            Log.e("---",""+data);
        }

    }


}