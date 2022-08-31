package com.randompasswordgenerate.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;

import com.randompasswordgenerate.R;
import com.randompasswordgenerate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}