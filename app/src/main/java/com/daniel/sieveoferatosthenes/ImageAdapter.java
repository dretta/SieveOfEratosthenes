package com.daniel.sieveoferatosthenes;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ListAdapter;

import java.util.Arrays;

/**
 * Created by Daniel on 5/8/2015.
 */
public class ImageAdapter extends ArrayAdapter {
    private Context mContext;
    private int mCount;
    private boolean setBlueBackground = false;
    private int[] gridColors;

    public ImageAdapter(Context c, int resource, int count) {
        super(c, resource);
        mContext = c;
        mCount = count;
        gridColors = new int[mCount];
        Arrays.fill(gridColors,Color.LTGRAY);
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

        } else {
            textView = (TextView) convertView;
        }


        textView.setBackgroundColor(gridColors[position]);

        textView.setText("" + (1 + position));
        return textView;
    }

    public void setPositionColor(int position, int color) {
        if(gridColors[position] == Color.LTGRAY)
            gridColors[position] = color;
    }

}
