package com.example.deviceinformation;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by kannas on 6/21/2017.
 */

public class Userinformation extends AppCompatActivity {
    public static String androidDeviceId;

    public String userIp(String name){
        String name1;
        name1 = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d("name",name+"");
        return name1;
        //name=androidDeviceId;
    }

}
