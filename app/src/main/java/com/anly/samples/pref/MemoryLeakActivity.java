package com.anly.samples.pref;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anly.samples.R;

public class MemoryLeakActivity extends AppCompatActivity implements SampleListener {

    private BigObject mBigObject = new BigObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        ListenerManager.getInstance().addListener(this);
    }

    @Override
    public void doSomething() {

    }
}
