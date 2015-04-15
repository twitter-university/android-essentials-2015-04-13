/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.university.android.yamba.service.YambaServiceHelper;


public class TweetActivity extends Activity implements TweetFragment.TweetCallbacks {
    private static final String TAG = "TWEET";
    private YambaServiceHelper svcHelper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tweet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        svcHelper = new YambaServiceHelper(this);

        setContentView(R.layout.activity_tweet);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                .add(R.id.container, new TweetFragment())
                .commit();
        }
    }

    @Override
    public void postTweet(String tweet) {
        svcHelper.post(tweet);
    }
}
