package com.anly.samples.views.container;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.anly.samples.R;

/**
 * Demonstrates wrapping a layout in a ScrollView.
 *
 */
public class ScrollViewActivity extends Activity {

    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        for (int i = 2; i < 64; i++) {
            TextView textView = new TextView(this);
            textView.setText("Text View " + i);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            textView.setBackgroundColor(Color.RED);
            layout.addView(textView, p);

            Button buttonView = new Button(this);
            buttonView.setText("Button " + i);
            layout.addView(buttonView, p);
        }

        mScrollView = (ScrollView) findViewById(R.id.scroll_view);

        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_1:
                        mScrollView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
                        break;
                    case R.id.radio_2:
                        mScrollView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);
                        break;
                    case R.id.radio_3:
                        mScrollView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                        break;
                    case R.id.radio_4:
                        mScrollView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
                        break;
                }
            }
        });


    }
}
