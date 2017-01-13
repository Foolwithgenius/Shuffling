package com.example.myapplication.tool;

import android.os.Handler;
import android.os.Message;

import com.example.myapplication.MainActivity;


public class HomeHandler extends Handler {

    public static final int SCROLL_VIEWPAGER_HOME = 0;

    public MainActivity mActivity;

    public void setHomeContext(MainActivity fragment) {
        this.mActivity = fragment;
    }


    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case SCROLL_VIEWPAGER_HOME:
                if (mActivity != null) mActivity.onPagerChange();
                sendEmptyMessageDelayed(SCROLL_VIEWPAGER_HOME, 3000);
                break;
        }
    }

}