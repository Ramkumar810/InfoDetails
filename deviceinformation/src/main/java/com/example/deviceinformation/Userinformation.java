package com.example.deviceinformation;

import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by kannas on 6/21/2017.
 */

public class Userinformation extends AppCompatActivity {
    public static String androidDeviceId;

    public void userIp(){
      /*  String name1;
       // name1 = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);*/
       //
       // Log.d("name",name1+"");
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;

        Log.e("MyActivity", "manufacturer " + manufacturer
                + " \n model " + model
                + " \n version " + version
                + " \n versionRelease " + versionRelease
        );


        //name=androidDeviceId;
    }

}
