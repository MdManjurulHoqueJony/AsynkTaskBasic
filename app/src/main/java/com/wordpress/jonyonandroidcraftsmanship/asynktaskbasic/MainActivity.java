package com.wordpress.jonyonandroidcraftsmanship.asynktaskbasic;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvTest = null;
    private String[] data = {"a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e",
            "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e",
            "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e",
            "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e",
            "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e", "a", "b", "c", "d", "e"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        lvTest = (ListView) findViewById(R.id.lvTest);
        lvTest.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        new MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> adapter = null;
        private int count = 0;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) lvTest.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (String item : data) {
                publishProgress(item);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Logger.log(e.toString());
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            setProgress((int) (((double)count/data.length)*10000));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setProgressBarVisibility(false);
            Logger.toast(MainActivity.this, "Items are added Successfully");
        }
    }

}
