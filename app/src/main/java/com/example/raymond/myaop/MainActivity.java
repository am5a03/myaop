package com.example.raymond.myaop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.metricslogger.MetricsLog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.listView);

        final String[] posts = new String[] {
                "Post 1",
                "Post 2",
                "Post 3",
                "Post 4",
                "Post 5"
        };

        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(
                this,
                R.layout.post_item,
                R.id.textView,
                posts
        );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                likePost(position);
            }
        });
    }

    @MetricsLog(category = "Post", action = "Like")
    private void likePost(long id) {
        Log.d(TAG, "likePost: id=" + id);
    }

}
