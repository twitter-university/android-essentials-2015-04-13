/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class TweetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle state) {
        View rootView = inflater.inflate(R.layout.fragment_tweet, root, false);
        return rootView;
    }
}
