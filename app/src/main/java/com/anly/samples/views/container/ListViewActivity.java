package com.anly.samples.views.container;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A list view example.
 */
public class ListViewActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new MyListAdapter(this));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getAdapter().getItem(position);
                Toast.makeText(ListViewActivity.this, item + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class MyListAdapter extends BaseAdapter {
        public MyListAdapter(Context context) {
            mContext = context;
        }

        public int getCount() {
            return mStrings.length;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return !mStrings[position].startsWith("-");
        }

        public Object getItem(int position) {
            return mStrings[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = (TextView) LayoutInflater.from(mContext).inflate(
                        android.R.layout.simple_expandable_list_item_1, parent, false);
            } else {
                tv = (TextView) convertView;
            }
            tv.setText(mStrings[position]);
            return tv;
        }

        private Context mContext;
    }
    
    private String[] mStrings = {
            "Abbaye de Belloc",
            "Abbaye du Mont des Cats",
            "Abertam",
            "Abondance",
            "Ackawi",
            "Acorn",
            "Adelost",
            "Affidelice au Chablis",
            "Afuega'l Pitu",
            "Airag",
            "Airedale",
            "Aisy Cendre",
            "Allgauer Emmentaler",
            "Alverca",
            "Ambert",
            "American Cheese",
            "Ami du Chambertin",
            "Anejo Enchilado",
            "Anneau du Vic-Bilh",
            "Anthoriro"
    };

}
