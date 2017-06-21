package com.example.deviceinformation;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by kannas on 6/21/2017.
 */

public class Userinformation extends AppCompatActivity {
    public static String androidDeviceId;

    public  void userIp(String name){
        name = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d("name",name+"");
        //name=androidDeviceId;
    }

}
