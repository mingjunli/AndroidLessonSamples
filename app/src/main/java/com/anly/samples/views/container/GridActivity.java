package com.anly.samples.views.container;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.anly.samples.R;

import java.util.List;

/**
 * This demo illustrates the use of CHOICE_MODE_MULTIPLE_MODAL, a.k.a. selection mode on GridView.
 */
public class GridActivity extends Activity {

    GridView mGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadApps();

        setContentView(R.layout.grid_1);
        mGrid = (GridView) findViewById(R.id.myGrid);
        mGrid.setAdapter(new AppsAdapter());
        mGrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        mGrid.setMultiChoiceModeListener(new MultiChoiceModeListener());
    }

    private List<ResolveInfo> mApps;

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    public class AppsAdapter extends BaseAdapter {
        public AppsAdapter() {
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            CheckableLayout l;
            ImageView i;

            if (convertView == null) {
                i = new ImageView(GridActivity.this);
                i.setScaleType(ImageView.ScaleType.FIT_CENTER);
                i.setLayoutParams(new ViewGroup.LayoutParams(120, 120));
                l = new CheckableLayout(GridActivity.this);
                l.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT,
                        GridView.LayoutParams.WRAP_CONTENT));
                l.addView(i);
            } else {
                l = (CheckableLayout) convertView;
                i = (ImageView) l.getChildAt(0);
            }

            ResolveInfo info = mApps.get(position);
            i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));

            return l;
        }


        public final int getCount() {
            return mApps.size();
        }

        public final Object getItem(int position) {
            return mApps.get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }

    public class CheckableLayout extends FrameLayout implements Checkable {
        private boolean mChecked;

        public CheckableLayout(Context context) {
            super(context);
        }

        public void setChecked(boolean checked) {
            mChecked = checked;
            setBackground(checked ?
                    ContextCompat.getDrawable(GridActivity.this, R.drawable.red)
                    : null);
        }

        public boolean isChecked() {
            return mChecked;
        }

        public void toggle() {
            setChecked(!mChecked);
        }

    }

    public class MultiChoiceModeListener implements GridView.MultiChoiceModeListener {
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Select Items");
            mode.setSubtitle("One item selected");
            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return true;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }

        public void onItemCheckedStateChanged(ActionMode mode, int position, long id,
                boolean checked) {
            int selectCount = mGrid.getCheckedItemCount();
            switch (selectCount) {
            case 1:
                mode.setSubtitle("One item selected");
                break;
            default:
                mode.setSubtitle("" + selectCount + " items selected");
                break;
            }
        }

    }
}
