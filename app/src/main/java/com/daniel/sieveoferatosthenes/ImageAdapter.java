package com.daniel.sieveoferatosthenes;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ListAdapter;

/**
 * Created by Daniel on 5/8/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int mCount;

    public ImageAdapter(Context c, int count) {
        mContext = c;
        mCount = count;
    }

    public int getCount() {
        return mCount;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new GridView.LayoutParams(100, 100));

            //textView;
            //textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }

        textView.setBackgroundColor(0xfff00000);
        textView.setText("" + position);
        return textView;
    }

}
