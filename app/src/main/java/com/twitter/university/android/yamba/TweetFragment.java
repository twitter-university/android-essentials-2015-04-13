/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class TweetFragment extends Fragment {
    private static final String TAG = "TWEET";

    private int tweetMaxLen;
    private int tweetWarnLen;
    private int tweetMinLen;

    private int colorOk;
    private int colorWarn;
    private int colorError;

    private EditText tweetText;
    private TextView tweetCount;
    private View tweetSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources rez = getResources();

        colorOk = rez.getColor(R.color.count_ok);
        tweetMaxLen = rez.getInteger(R.integer.tweet_limit);

        colorWarn = rez.getColor(R.color.count_warn);
        tweetWarnLen = rez.getInteger(R.integer.warn_limit);

        colorError = rez.getColor(R.color.count_err);
        tweetMinLen = rez.getInteger(R.integer.err_limit);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle state) {
        View rootView = inflater.inflate(R.layout.fragment_tweet, root, false);

        tweetSubmit = rootView.findViewById(R.id.tweet_submit);
        tweetSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        post();
                    }
                }
        );

        tweetCount = (TextView) rootView.findViewById(R.id.tweet_count);
        tweetText = (EditText) rootView.findViewById(R.id.tweet_text);
        tweetText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCount();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        return rootView;
    }


    void updateCount() {
        int n = tweetText.getText().length();
        tweetSubmit.setEnabled(canPost(n));

        n = tweetMaxLen - n;

        int color;
        if (n < tweetMinLen) { color = colorError; }
        else if (n < tweetWarnLen) { color = colorWarn; }
        else { color = colorOk; }
        tweetCount.setTextColor(color);

        tweetCount.setText(String.valueOf(n));
    }

    void post() {
        String tweet = tweetText.getText().toString();
        if (BuildConfig.DEBUG) { Log.d(TAG, "posting: " + tweet); }

        if (!canPost(tweet.length())) { return; }

        tweetSubmit.setEnabled(false);
        tweetText.setText(null);

        // Simulate failing network call
        try { Thread.sleep(3 * 30 * 1000); }
        catch (InterruptedException ignore) { }
    }

    private boolean canPost(int n) {
        return (tweetMinLen < n) && (tweetMaxLen >= n);
    }
}
