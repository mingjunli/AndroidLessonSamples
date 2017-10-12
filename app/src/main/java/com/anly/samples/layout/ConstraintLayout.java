package com.anly.samples.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.anly.samples.R;

public class ConstraintLayout extends AppCompatActivity {
    private boolean mShowingLayout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayout);
    }

    public void show(View v) {
        String tag = (String) v.getTag();
        int id = getResources().getIdentifier(tag, "layout", getPackageName());
        setContentView(id);
        mShowingLayout = true;
    }

    @Override
    public void onBackPressed() {
        if (mShowingLayout) {
            setContentView(R.layout.activity_constraintlayout);
            mShowingLayout = false;
        } else {
            super.onBackPressed();
        }
    }

    public void showConstraintSetExample(View view) {
        startActivity(new Intent(this, ConstraintSetExampleActivity.class));
    }
}
