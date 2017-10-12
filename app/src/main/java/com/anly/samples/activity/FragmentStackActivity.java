package com.anly.samples.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;

public class FragmentStackActivity extends TraceActivity {
    int mStackLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_stack);
        
        // Watch for button clicks.
        Button button = (Button)findViewById(R.id.new_fragment);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                addFragmentToStack();
            }
        });
        button = (Button)findViewById(R.id.delete_fragment);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        if (savedInstanceState == null) {
            // Do first time initialization -- add initial fragment.
            Fragment newFragment = CountingFragment.newInstance(mStackLevel);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.simple_fragment, newFragment).commit();
        } else {
            mStackLevel = savedInstanceState.getInt("level");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }

//BEGIN_INCLUDE(add_stack)
    void addFragmentToStack() {
        mStackLevel++;

        // Instantiate a new fragment.
        Fragment newFragment = CountingFragment.newInstance(mStackLevel);

        // Add the fragment to the activity, pushing this transaction
        // on to the back stack.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.simple_fragment, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }
//END_INCLUDE(add_stack)

//BEGIN_INCLUDE(fragment)
    public static class CountingFragment extends Fragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static CountingFragment newInstance(int num) {
            CountingFragment f = new CountingFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.hello_world, container, false);
            View tv = v.findViewById(R.id.text);
            ((TextView)tv).setText("Fragment #" + mNum);
            tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
            return v;
        }
    }
//END_INCLUDE(fragment)
}
