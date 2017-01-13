package com.example.myapplication.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bese.BasePagerAdapter;
import com.example.myapplication.tool.PixelUtil;

import java.util.List;


/**
 * Created by King on 2014/8/6.
 */
public class HeaderBannerView extends RelativeLayout {

    private static final long click_timeout = 110;
    private static long click_time = 0;
    private ViewPager mViewPager;
    private LinearLayout group;
    private BannerAdapter bannerAdapter;
    private TextView mTitle, mInfo;
    private ImageView[] indicators;
    private Context mContext;
    private float click_x = 0;
    private boolean onTouch = false, onShowNext = true;
    private TextView name;
    private TextView title;
    private List<String> view_data;
    private ViewPager.SimpleOnPageChangeListener pagerChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < indicators.length; i++) {
                indicators[i].setImageResource((position % view_data.size() != i) ? R.drawable.advertise_dot_unfocus
                        : R.drawable.advertise_dot_focus);
            }
            int mPosition = (position % view_data.size());
        }
    };

    public HeaderBannerView(Context context) {
        this(context, null);
    }

    public HeaderBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    public void showNextPage() {
        if (!onShowNext) {
            if (!onTouch) onShowNext = true;
        } else if (null != mViewPager && null != bannerAdapter && bannerAdapter.getList().size() > 1) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                click_time = System.currentTimeMillis();
                click_x = ev.getX();
                onTouch = true;
                onShowNext = false;
                break;
            case MotionEvent.ACTION_UP:
                onTouch = false;
                if (bannerAdapter != null && mViewPager != null && bannerAdapter.getCount() > mViewPager.getCurrentItem()
                        && click_time + click_timeout >= System.currentTimeMillis() && Math.abs(ev.getX() - click_x) < 50) {
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void init() {
        int width = Math.min(
                getResources().getDisplayMetrics().widthPixels,
                getResources().getDisplayMetrics().heightPixels);
        LayoutInflater.from(getContext()).inflate(R.layout.home_recyclerview_header, this);
        mViewPager = (ViewPager) findViewById(R.id.ad_viewpager);
        mViewPager.setPageMargin(20);
        mViewPager.setOffscreenPageLimit(3);
        group = (LinearLayout) findViewById(R.id.dot_layout);
    }

    public void setData(List<String> list) {
        if (list != null && list.size() > 0) {
            view_data = list;
            addIndicator(view_data.size());
            mViewPager.setAdapter(bannerAdapter = new BannerAdapter(view_data));
            mViewPager.setOnPageChangeListener(pagerChangeListener);
            mViewPager.setCurrentItem(500 - 500 % view_data.size());
        } else {
            mViewPager.getLayoutParams().height = 1;
        }
    }

    private void addIndicator(int count) {
        indicators = new ImageView[count];
        group.removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView textView = new ImageView(getContext());
            int item_width = PixelUtil.dp2px(getContext(), 5);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(item_width, item_width);
            lp.setMargins(item_width, 0, item_width, 0);
            textView.setLayoutParams(lp);
            indicators[i] = textView;
            indicators[i].setImageResource((i == 0) ? R.drawable.advertise_dot_focus :
                    R.drawable.advertise_dot_unfocus);
            group.addView(textView);
        }
    }

    private class BannerAdapter extends BasePagerAdapter<String> {

        public BannerAdapter(List<String> list) {
            super(list);
        }

        @Override
        public int getCount() {
            if (getList() != null && getList().size() > 0) {
                return Integer.MAX_VALUE;
            } else {
                return 0;
            }
        }

        @Override
        public View getItemView(ViewGroup container, int position) {
            position = position % getList().size();
            ImageView view = new ImageView(mContext);
            view.setImageResource(R.mipmap
                    .ic_launcher);
            return view;
        }
    }
}
