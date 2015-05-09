package com.daniel.sieveoferatosthenes;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private boolean isPlaying;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isPlaying = false;

        ViewGroup.LayoutParams layoutParams;

        int primesLE = 225;
        int numOfColumns = (int)Math.round(Math.sqrt((double) primesLE));
        int numOfRows = (int)Math.ceil((double)primesLE/(double)numOfColumns);
        //RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
        //layoutParams = relativeLayout.getLayoutParams();
        //layoutParams.width = 150*numOfColumns;
        //relativeLayout.setLayoutParams(layoutParams);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        layoutParams = gridview.getLayoutParams();
        layoutParams.width = 150*numOfColumns; //this is in pixels
        //layoutParams.height = 150*numOfRows; //this is in pixels
        gridview.setLayoutParams(layoutParams);
        gridview.setNumColumns(numOfColumns);
        gridview.setAdapter(new ImageAdapter(this, primesLE));
        ListAdapter list = gridview.getAdapter();

        /*
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        */
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void playSoE(){

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
            if(isPlaying) {
                isPlaying = false;
                item.setIcon(R.drawable.ic_action_play);
                item.setTitle("Play");
                playSoE();
            }
            else {
                isPlaying = true;
                item.setIcon(R.drawable.ic_action_pause);
                item.setTitle("Pause");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
