/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba;

import android.app.Fragment;

public class TimelineActivity extends YambaActivity {
    @Override
    public Fragment getFragment() {
        return new TimelineFragment();
    }
}
