package com.daniel.sieveoferatosthenes;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    private Thread mThread;
    private int i;

    private void dataVisualization(){

        int numOfColumns = (int)Math.round(Math.sqrt((double) primesLE));
        //int numOfRows = (int)Math.ceil((double)primesLE/(double)numOfColumns);
        ViewGroup.LayoutParams layoutParams;

        layoutParams = mGridView.getLayoutParams();
        layoutParams.width = 150*numOfColumns; //this is in pixels
        mGridView.setLayoutParams(layoutParams);
        mGridView.setNumColumns(numOfColumns);
        mAdapter = new ImageAdapter(this, android.R.layout.simple_list_item_1, primesLE);
        mGridView.setAdapter(mAdapter);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIsPlaying = false;
        primesLE = 0;
        i = 0;


        View relativeLayout = findViewById(R.id.relativeLayout);
        View title_horizontalScrollView = relativeLayout.findViewById(R.id.title_horizontalScrollView);
        View dataLayout = title_horizontalScrollView.findViewById(R.id.dataLayout);
        mGridView = (GridView) dataLayout.findViewById(R.id.mGridView);

        dataVisualization();

        View inputLayout = relativeLayout.findViewById(R.id.inputLayout);
        final EditText inputEditText = (EditText)inputLayout.findViewById(R.id.inputEditText);
        inputEditText.setOnKeyListener(new EditText.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    InputMethodManager inputManager = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(inputEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    try {
                        primesLE = Integer.parseInt(inputEditText.getText().toString());
                    } catch(NumberFormatException nfe) {
                        inputEditText.setText("Try again");
                        primesLE = 0;
                    }
                    if(primesLE < 0)
                        primesLE = 0;

                    dataVisualization();

                    Toast.makeText(MainActivity.this, inputEditText.getText(), Toast.LENGTH_SHORT).show();

                    return true;
                }
                return false;
            }
        });
        inputEditText.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.getText().clear();
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void playSoE(){


        for(int i = 0; i < primesLE; i++) {
            mAdapter.setPositionColor(i, 0xffff0000 + 0x100 * i);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                }
            }, 1000);

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
