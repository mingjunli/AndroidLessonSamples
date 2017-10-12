package com.anly.samples.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;

/**
 * Created on 2017/9/20.
 * Description:
 *
 * @author bianyue
 */
public class ForResultActivity extends TraceActivity {
    static final private int GET_CODE = 0;
    private TextView mResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_for_result);
        mResultsTextView = (TextView)findViewById(R.id.results);

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity whose result we want to retrieve.  The
                // result will come back with request code GET_CODE.
                Intent intent = new Intent(ForResultActivity.this, SendResultActivity.class);
                startActivityForResult(intent, GET_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        // You can use the requestCode to select between multiple child
        // activities you may have started.  Here there is only one thing
        // we launch.
        if (requestCode == GET_CODE) {

            if (resultCode == RESULT_CANCELED) {

                mResultsTextView.setText("Cancelled");

            } else if (resultCode == RESULT_OK){

                int color = data.getIntExtra("color", 0);
                if (Color.GREEN == color) {
                    mResultsTextView.setText("Green");
                } else if (Color.RED == color) {
                    mResultsTextView.setText("Red");
                }
                mResultsTextView.setBackgroundColor(color);
            }
        }
    }

}
