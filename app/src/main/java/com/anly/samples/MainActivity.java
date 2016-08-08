package com.anly.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anly.samples.activity.AActivity;
import com.anly.samples.textview.TextInputLayoutActivity;
import com.anly.samples.textview.TextViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mSampleListView;

    private static ArrayList<SampleClass> mDataSet = new ArrayList<>();

    static {
        mDataSet.add(new SampleClass("ActivityLifecycleSample", AActivity.class));
        mDataSet.add(new SampleClass("TextViewSample", TextViewActivity.class));
        mDataSet.add(new SampleClass("TextInputLayoutSample", TextInputLayoutActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSampleListView = (ListView) findViewById(R.id.sample_list_view);
        mSampleListView.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, mDataSet));
        mSampleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SampleClass sampleClass = (SampleClass) parent.getAdapter().getItem(position);
                startActivity(new Intent(MainActivity.this, sampleClass.className));
            }
        });
    }

    public static class SampleClass {
        public String name;
        public Class className;

        public SampleClass(String name, Class className) {
            this.name = name;
            this.className = className;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
