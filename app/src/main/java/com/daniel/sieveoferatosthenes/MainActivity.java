package com.daniel.sieveoferatosthenes;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {

    private int primesLE; //Less than or Equal to
    private GridView mGridView;
    private TextViewAdapter mAdapter;
    private TextView mTextView;


    private void dataVisualization(){

        int numOfColumns = (int)Math.round(Math.sqrt((double) primesLE));

        ViewGroup.LayoutParams layoutParams;

        layoutParams = mGridView.getLayoutParams();
        layoutParams.width = 150*numOfColumns; //this is in pixels
        mGridView.setLayoutParams(layoutParams);
        mGridView.setNumColumns(numOfColumns);
        mAdapter = new TextViewAdapter(this, android.R.layout.simple_list_item_1, primesLE);
        mGridView.setAdapter(mAdapter);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primesLE = 0;

        View relativeLayout = findViewById(R.id.relativeLayout);
        View dataView = relativeLayout.findViewById(R.id.dataView);
        View dataLayout = dataView.findViewById(R.id.dataLayout);
        mGridView = (GridView) dataLayout.findViewById(R.id.mGridView);
        View outputLayout = relativeLayout.findViewById(R.id.outputLayout);
        View outputView = outputLayout.findViewById(R.id.outputView);
        mTextView = (TextView)outputView.findViewById(R.id.mTextView);

        dataVisualization();

        View inputLayout = relativeLayout.findViewById(R.id.inputLayout);
        final EditText inputEditText = (EditText)inputLayout.findViewById(R.id.inputEditText);
        inputEditText.setOnKeyListener(new EditText.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

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
                mTextView.setText("");
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
            DataVisualizationTask dataVisTask = new DataVisualizationTask(primesLE, mAdapter, mTextView);
             dataVisTask.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_status && primesLE > 0){
            playSoE();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
