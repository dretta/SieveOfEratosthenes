package com.daniel.sieveoferatosthenes;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Daniel on 5/13/2015.
 */
public class DataVisualizationTask  extends AsyncTask<Void, Void, Void> {

    private int index;
    private ImageAdapter mAdapter;
    private TextView primes;

    public DataVisualizationTask(int primesLE, ImageAdapter imageAdapter, TextView outputText) {
        super();
        index = primesLE;
        mAdapter = imageAdapter;
        primes = outputText;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        StringBuilder sb = new StringBuilder();
        sb.append("The primes are: ");
        boolean[] soe = new boolean[index];
        Random random = new Random();
        int multiple, randomColor;
        Arrays.fill(soe, true);
        soe[0] = false;
        mAdapter.setPositionColor(0, Color.BLACK);
        onProgressUpdate();
        for (int i = 1; i < index; i++){
            if (soe[i]) {
                sb.append((i+1)+", ");
                multiple = 2 * i + 1;
                randomColor = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
                while (multiple < index) {
                    soe[multiple] = false;
                    mAdapter.setPositionColor(multiple, randomColor);
                    onProgressUpdate();
                    multiple += i + 1;
                }
            }
            onProgressUpdate();
        }
        if(index > 1)
            sb.delete(sb.length()-2,sb.length());
        primes.setText(sb.toString());
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        mAdapter.notifyDataSetChanged();
        long waitUntil = System.currentTimeMillis() + 1000;
        while(waitUntil > System.currentTimeMillis()){}
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
}

