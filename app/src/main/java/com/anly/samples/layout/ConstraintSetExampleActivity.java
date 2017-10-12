package com.anly.samples.layout;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;

import com.anly.samples.R;

/**
 * An example activity that show cases the use of {@link ConstraintSet}.
 * It starts out with a single ConstraintLayout which is then transitioned to
 * a different ConstraintLayout using {@link ConstraintSet#applyTo(ConstraintLayout)}.
 */
public class ConstraintSetExampleActivity extends AppCompatActivity {

    private static final String SHOW_BIG_IMAGE = "showBigImage";

    /**
     * Whether to show an enlarged image
     */
    private boolean mShowBigImage = false;
    /**
     * The ConstraintLayout that any changes are applied to.
     */
    private ConstraintLayout mRootLayout;
    /**
     * The ConstraintSet to use for the normal initial state
     */
    private ConstraintSet mConstraintSetNormal = new ConstraintSet();
    /**
     * ConstraintSet to be applied on the normal ConstraintLayout to make the Image bigger.
     */
    private ConstraintSet mConstraintSetBig = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraintset_example_main);

        mRootLayout = (ConstraintLayout) findViewById(R.id.activity_constraintset_example);
        // Note that this can also be achieved by calling
        // `mConstraintSetNormal.load(this, R.layout.constraintset_example_main);`
        // Since we already have an inflated ConstraintLayout in `mRootLayout`, clone() is
        // faster and considered the best practice.
        mConstraintSetNormal.clone(mRootLayout);
        // Load the constraints from the layout where ImageView is enlarged.
        mConstraintSetBig.load(this, R.layout.constraintset_example_big);

        if (savedInstanceState != null) {
            boolean previous = savedInstanceState.getBoolean(SHOW_BIG_IMAGE);
            if (previous != mShowBigImage) {
                mShowBigImage = previous;
                applyConfig();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOW_BIG_IMAGE, mShowBigImage);
    }

    // Method called when the ImageView within R.layout.constraintset_example_main
    // is clicked.
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void toggleMode(View v) {
        TransitionManager.beginDelayedTransition(mRootLayout);
        mShowBigImage = !mShowBigImage;
        applyConfig();
    }

    private void applyConfig() {
        if (mShowBigImage) {
            mConstraintSetBig.applyTo(mRootLayout);
        } else {
            mConstraintSetNormal.applyTo(mRootLayout);
        }
    }
}
