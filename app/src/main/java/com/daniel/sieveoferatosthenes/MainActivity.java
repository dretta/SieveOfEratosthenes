package com.daniel.sieveoferatosthenes;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private boolean mIsPlaying;
    private int primesLE;
    private GridView mGridView;
    private ImageAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIsPlaying = false;

        ViewGroup.LayoutParams layoutParams;

        primesLE = 0;
        int numOfColumns = (int)Math.round(Math.sqrt((double) primesLE));
        int numOfRows = (int)Math.ceil((double)primesLE/(double)numOfColumns);

        View relativeLayout = findViewById(R.id.relativeLayout);
        View title_horizontalScrollView = relativeLayout.findViewById(R.id.title_horizontalScrollView);
        View dataLayout = title_horizontalScrollView.findViewById(R.id.dataLayout);
        mGridView = (GridView) dataLayout.findViewById(R.id.mGridView);
        layoutParams = mGridView.getLayoutParams();
        layoutParams.width = 150*numOfColumns; //this is in pixels
        mGridView.setLayoutParams(layoutParams);
        mGridView.setNumColumns(numOfColumns);
        mAdapter = new ImageAdapter(this, android.R.layout.simple_list_item_1, primesLE);
        mGridView.setAdapter(mAdapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void playSoE(){
        /*
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, primesLE) {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View result = super.getView(position, convertView, parent);
                    result.setBackgroundColor(Color.BLUE);
                return result;
            };
        };
        */



        for(int i = 0; i < primesLE; i++){
            mAdapter.setPositionColor(i, 0xffff0000 + 0x100*i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("Menu","Button Pressed");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        else if (id == R.id.action_status){
            if(mIsPlaying) {
                mIsPlaying = false;
                item.setIcon(R.drawable.ic_action_play);
                item.setTitle("Play");
                playSoE();
            }
            else {
                mIsPlaying = true;
                item.setIcon(R.drawable.ic_action_pause);
                item.setTitle("Pause");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
