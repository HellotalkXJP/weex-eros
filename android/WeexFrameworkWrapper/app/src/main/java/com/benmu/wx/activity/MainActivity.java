package com.benmu.wx.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.wx.R;

public class MainActivity extends AbstractWeexActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
