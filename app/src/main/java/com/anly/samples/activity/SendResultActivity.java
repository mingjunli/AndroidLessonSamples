package com.anly.samples.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;

public class SendResultActivity extends TraceActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_result);

        findViewById(R.id.btn_a).setOnClickListener(this);
        findViewById(R.id.btn_b).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_a:
                setResult(RESULT_OK, new Intent().putExtra("color", Color.GREEN));
                finish();
                break;

            case R.id.btn_b:
                setResult(RESULT_OK, new Intent().putExtra("color", Color.RED));
                finish();
                break;

            case R.id.btn_c:
                setResult(RESULT_CANCELED);
                finish();
                break;

            default:
                break;
        }
    }
}
