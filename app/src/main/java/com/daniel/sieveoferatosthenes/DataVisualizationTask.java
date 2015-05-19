package com.daniel.sieveoferatosthenes;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Random;


public class DataVisualizationTask  extends AsyncTask<Void, Void, Void> {

    private int index;
    private TextViewAdapter mAdapter;
    private TextView primes;

    public DataVisualizationTask(int primesLE, TextViewAdapter imageAdapter, TextView outputText) {
        super();
        index = primesLE;
        mAdapter = imageAdapter;
        primes = outputText;
    }

    /*TODO: Figure out how to get each TextView in the GridView to change it's color one at a time
    * rather than all at once.
    *
    */

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        StringBuilder sb = new StringBuilder();

        boolean[] soe = new boolean[index];
        Random random = new Random();
        int multiple, randomColor;

        sb.append("The primes are: ");
        Arrays.fill(soe, true);

        soe[0] = false;
        mAdapter.setPositionColor(0, Color.BLACK);

        for (int i = 1; i < index; i++){
            if (soe[i]) {
                sb.append(i+1);
                sb.append(", ");
                multiple = 2 * i + 1;
                randomColor = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));

                while (multiple < index) {
                    soe[multiple] = false;
                    mAdapter.setPositionColor(multiple, randomColor);
                    multiple += i + 1;
                }

            }
        }

        if(index > 1)
            sb.delete(sb.length()-2,sb.length());

        primes.setText(sb.toString());
    }


    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
}

