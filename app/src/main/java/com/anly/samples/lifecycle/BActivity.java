package com.anly.samples.lifecycle;

import android.os.Bundle;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;

public class BActivity extends TraceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
    }
}
