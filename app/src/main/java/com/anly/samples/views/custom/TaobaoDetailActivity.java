package com.anly.samples.views.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;
import com.anly.samples.common.dummydata.Cheeses;
import com.anly.samples.views.container.RecyclerCustomAdapter;

/**
 * Created on 2017/10/11.
 * Description:
 *
 * @author bianyue
 */
public class TaobaoDetailActivity extends TraceActivity {

    RecyclerView mRecyclerView;
    RecyclerCustomAdapter mAdapter;
    SnapTopLinearLayoutManager mLinearLayoutManager;

    LinearLayout mTopTabLayout;
    private LinearLayout headLayout;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private View dividerView;

    // TODO
    private int imageHeight = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_taobao_detail);
        mRecyclerView = (RecyclerView)findViewById(R.id.detail_view);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mLinearLayoutManager = new SnapTopLinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new RecyclerCustomAdapter(Cheeses.CHEESES_22);
        mRecyclerView.setAdapter(mAdapter);

        mTopTabLayout = (LinearLayout)findViewById(R.id.top_tab_layout);

        headLayout = (android.widget.LinearLayout) findViewById(R.id.head_layout);
        headLayout.setVisibility(View.GONE);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(mOnClickListener);

        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setOnClickListener(mOnClickListener);

        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setOnClickListener(mOnClickListener);

        tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setOnClickListener(mOnClickListener);

        dividerView = findViewById(R.id.divide_line);

        setStatusBar();
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int  mTotalScrolled = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Log.i(TAG, "newState:-------->" + newState);

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int x, int y) {
            Log.i(TAG, "y:-------->" + y);
            mTotalScrolled += y;
            if (mTotalScrolled <= 0) {
                //设置渐变的头部的背景颜色
                Log.i(TAG, "y <= 0:----------->");
                headLayout.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));

                headLayout.setVisibility(View.GONE);
                tv1.setTextColor(Color.TRANSPARENT);
                tv2.setTextColor(Color.TRANSPARENT);
                tv3.setTextColor(Color.TRANSPARENT);
                tv4.setTextColor(Color.TRANSPARENT);
                dividerView.setVisibility(View.GONE);

            } else if (mTotalScrolled > 0 && mTotalScrolled <= imageHeight) {
                //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                Log.i(TAG, "滑动距离小于banner图的高度---->" + imageHeight);
                float scale = (float) mTotalScrolled / imageHeight;
                int alpha = (int) (scale * 255);
                headLayout.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                headLayout.setVisibility(View.VISIBLE);
                tv1.setTextColor(Color.argb(alpha, 1, 24, 28));
                tv2.setTextColor(Color.argb(alpha, 1, 24, 28));
                tv3.setTextColor(Color.argb(alpha, 1, 24, 28));
                tv4.setTextColor(Color.argb(alpha, 1, 24, 28));
            } else {
                //滑动到banner下面设置普通颜色
                Log.i(TAG, "滑动到banner下面---->" + imageHeight);
                headLayout.setBackgroundColor(Color.WHITE);
                headLayout.setVisibility(View.VISIBLE);
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.BLACK);
                tv4.setTextColor(Color.BLACK);
                dividerView.setVisibility(View.VISIBLE);
            }
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int position;
            switch (v.getId()) {
                case R.id.tv1:
                    position = 0;
                    break;
                case R.id.tv2:
                    position = 6;
                    break;
                case R.id.tv3:
                    position = 14;
                    break;
                case R.id.tv4:
                    position = 18;
                    break;
                default:
                    position = 0;
                    break;
            }

            mLinearLayoutManager.scrollToPosition(position);
        }
    };

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_dark_transparent));
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public class SnapTopLinearLayoutManager extends LinearLayoutManager {
        private Context mContext;
        public SnapTopLinearLayoutManager(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        public void scrollToPosition(int position) {
            SnapTopLinearSmoothScroller linearSmoothScroller = new SnapTopLinearSmoothScroller(mContext) {
                @Override
                public PointF computeScrollVectorForPosition(int targetPosition) {
                    return SnapTopLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
                }
            };
            linearSmoothScroller.setTargetPosition(position);
            startSmoothScroll(linearSmoothScroller);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
            SnapTopLinearSmoothScroller linearSmoothScroller = new SnapTopLinearSmoothScroller(recyclerView.getContext()) {
                @Override
                public PointF computeScrollVectorForPosition(int targetPosition) {
                    return SnapTopLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
                }
            };
            linearSmoothScroller.setTargetPosition(position);
            startSmoothScroll(linearSmoothScroller);
        }
    }

    public abstract class SnapTopLinearSmoothScroller extends LinearSmoothScroller {

        @Override
        protected int getVerticalSnapPreference() {
            return SNAP_TO_START;
        }

        public SnapTopLinearSmoothScroller(Context context) {
            super(context);
        }
    }

}
