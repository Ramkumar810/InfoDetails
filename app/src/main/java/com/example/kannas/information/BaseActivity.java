package com.example.kannas.information;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.deviceinformation.Userinformation;

/**
 * Created by kannas on 6/23/2017.
 */

public class BaseActivity extends AppCompatActivity{
    Userinformation userinformation;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        userinformation.crashReport();
    }
}
