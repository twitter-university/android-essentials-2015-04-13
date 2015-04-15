/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba;

import android.app.Fragment;
import android.os.Bundle;

import com.twitter.university.android.yamba.service.YambaServiceHelper;


public class TweetActivity extends YambaActivity implements TweetFragment.TweetCallbacks {
    private static final String TAG = "TWEET";
    private YambaServiceHelper svcHelper;

    @Override
    public Fragment getFragment() { return new TweetFragment(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        svcHelper = new YambaServiceHelper(this);
    }

    @Override
    public void postTweet(String tweet) {
        svcHelper.post(tweet);
    }
}
