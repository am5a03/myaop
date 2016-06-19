package com.example.raymond.myaop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.metricslogger.Cacheable;
import com.example.metricslogger.MetricsLog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button getPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.listView);

        getPostButton = (Button) findViewById(R.id.getPost);
        getPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.postStatus);
                String post = getPost("abc");
                textView.setText(post);
            }
        });

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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @MetricsLog(category = "Post", action = "Like")
    private void likePost(long id) {
        Log.d(TAG, "likePost: id=" + id);
    }


    @Cacheable
    String getPost(String id) {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            Log.e(TAG, "getPost: ", e);
        }

        return new String("Post " + id);
    }

}
