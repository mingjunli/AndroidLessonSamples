package com.anly.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.anly.samples.activity.AActivity;
import com.anly.samples.activity.ForResultActivity;
import com.anly.samples.activity.FragmentDialogActivity;
import com.anly.samples.activity.FragmentHideShowActivity;
import com.anly.samples.activity.FragmentStackActivity;
import com.anly.samples.activity.ListFragmentActivity;
import com.anly.samples.aidl.AidlSampleActivity;
import com.anly.samples.layout.ConstraintLayout;
import com.anly.samples.layout.FrameLayout;
import com.anly.samples.layout.LinearLayout;
import com.anly.samples.pref.MemoryLeakActivity;
import com.anly.samples.pref.leak.CursorLeakActivity;
import com.anly.samples.pref.leak.HandlerLeakActivity;
import com.anly.samples.problem.BackgroundPaddingActivity;
import com.anly.samples.rx.DynamicCheckActivity;
import com.anly.samples.textview.TextInputLayoutActivity;
import com.anly.samples.textview.TextViewActivity;
import com.anly.samples.views.BasicWidgetsActivity;
import com.anly.samples.views.container.GridActivity;
import com.anly.samples.views.container.ListViewActivity;
import com.anly.samples.views.container.RecyclerViewDemo;
import com.anly.samples.views.container.ScrollViewActivity;
import com.anly.samples.views.container.SlidingTabActivity;
import com.anly.samples.views.container.WebViewActivity;
import com.anly.samples.views.custom.TaobaoDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<SampleClass> mDataSet = new ArrayList<>();

    static {
        mDataSet.add(new SampleClass("Activity/Lifecycle", AActivity.class));
        mDataSet.add(new SampleClass("Activity/ForResult", ForResultActivity.class));
        mDataSet.add(new SampleClass("Activity/Fragment/Stack", FragmentStackActivity.class));
        mDataSet.add(new SampleClass("Activity/Fragment/Hide&Show", FragmentHideShowActivity.class));
        mDataSet.add(new SampleClass("Activity/Fragment/ListFragment", ListFragmentActivity.class));
        mDataSet.add(new SampleClass("Activity/Fragment/DialogFragment", FragmentDialogActivity.class));

        mDataSet.add(new SampleClass("Layout/LinearLayout", LinearLayout.class));
        mDataSet.add(new SampleClass("Layout/RelativeLayout", RelativeLayout.class));
        mDataSet.add(new SampleClass("Layout/FrameLayout", FrameLayout.class));
        mDataSet.add(new SampleClass("Layout/ConstraintLayout", ConstraintLayout.class));

        mDataSet.add(new SampleClass("Container/ScrollView", ScrollViewActivity.class));
        mDataSet.add(new SampleClass("Container/ListView", ListViewActivity.class));
        mDataSet.add(new SampleClass("Container/GridView", GridActivity.class));
        mDataSet.add(new SampleClass("Container/RecyclerView", RecyclerViewDemo.class));
        mDataSet.add(new SampleClass("Container/SlidingTab", SlidingTabActivity.class));
        mDataSet.add(new SampleClass("Container/WebView", WebViewActivity.class));

        mDataSet.add(new SampleClass("View/BasicWidgets", BasicWidgetsActivity.class));

        mDataSet.add(new SampleClass("View/TextView/Spannable", TextViewActivity.class));
        mDataSet.add(new SampleClass("View/TextView/TextInputLayout", TextInputLayoutActivity.class));

        mDataSet.add(new SampleClass("RX/DynamicCheckSample", DynamicCheckActivity.class));
        mDataSet.add(new SampleClass("Issues/BackgroundPadding", BackgroundPaddingActivity.class));
        mDataSet.add(new SampleClass("Leak/MemoryLeakSample", MemoryLeakActivity.class));
        mDataSet.add(new SampleClass("Leak/ListenerLeakSample", MemoryLeakActivity.class));
        mDataSet.add(new SampleClass("Leak/HandlerLeakSample", HandlerLeakActivity.class));
        mDataSet.add(new SampleClass("Leak/CursorLeakSample", CursorLeakActivity.class));
        mDataSet.add(new SampleClass("AIDL/AIDL Sample", AidlSampleActivity.class));

        mDataSet.add(new SampleClass("Demos/Taobao Detail", TaobaoDetailActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String path = intent.getStringExtra("com.anly.samples.Path");

        if (path == null) {
            path = "";
        }

        if (!TextUtils.isEmpty(path)) {
            getSupportActionBar().setTitle(path);
        }


        ListView sampleListView = (ListView) findViewById(R.id.sample_list_view);
        sampleListView.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, getData(path)));

        sampleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SampleClass sampleClass = (SampleClass) parent.getAdapter().getItem(position);

                Intent intent = new Intent(MainActivity.this, sampleClass.className);
                if (!TextUtils.isEmpty(sampleClass.path)) {
                    intent.putExtra("com.anly.samples.Path", sampleClass.path);
                }
                startActivity(intent);
            }
        });
    }

    public static class SampleClass {
        public String name;
        public Class className;
        public String path;

        public SampleClass(String name, Class className) {
            this.name = name;
            this.className = className;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    protected List<SampleClass> getData(String prefix) {

        List<SampleClass> myData = new ArrayList<>();

        String[] prefixPath;
        String prefixWithSlash = prefix;

        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }

        int len = mDataSet.size();

        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            SampleClass info = mDataSet.get(i);
            String label = info.name;

            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData, nextLabel, info.className, "");
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel, MainActivity.class,
                                prefix.equals("") ? nextLabel : prefix + "/" + nextLabel);
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        return myData;
    }

    protected void addItem(List<SampleClass> data, String name, Class className, String path) {
        SampleClass sampleClass = new SampleClass(name, className);

        if (MainActivity.class.getName().equals(className.getName())) {
            sampleClass.path = path;
        }
        data.add(sampleClass);
    }
}
