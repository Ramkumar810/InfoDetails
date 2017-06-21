package com.example.deviceinformation;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothClass;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Locale;

import static java.security.AccessController.getContext;

/**
 * Created by kannas on 6/21/2017.
 */

public class Userinformation extends AppCompatActivity {
    public static String androidDeviceId;
     Context context;
    int deviceStatus;
    String currentBatteryStatus="Battery Info";
    IntentFilter intentfilter;
    public void userIp(Context context){
      /*  Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        Log.v("onCreate", "maxMemory:" + Long.toString(maxMemory));*/
       /* ActivityManager am = (ActivityManager)context.getSystemService(ACTIVITY_SERVICE);
        int memoryClass = am.getMemoryClass();
        Log.v("onCreate", "memoryClass:" + Integer.toString(memoryClass));*/
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)context.getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        double availableMegs = mi.availMem / 0x100000L;

//Percentage can be calculated for API 16+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            double percentAvail = mi.availMem / (double)mi.totalMem;
        }
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            int versionNumber=info.versionCode;
            Log.d("version",versionNumber+"");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String  androidDeviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        String manufacturer =android.os.Build.MANUFACTURER;
        String model = Build.MODEL;
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;
        Log.e("MyActivity", "manufacturer " + manufacturer
                + " \n model " + model
                + " \n version " + version
                + " \n versionRelease " + versionRelease
                + " \n Device Language "+  Locale.getDefault().getDisplayLanguage()
                + " \n Device Country " + Locale.getDefault().getDisplayCountry()


        );
        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        context.registerReceiver(broadcastreceiver,intentfilter);
    }
    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryLevel=(int)(((float)level / (float)scale) * 100.0f);

            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){
                Log.d("name",currentBatteryStatus+" = Charging at "+batteryLevel+" %");
            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){
                Log.d("name",currentBatteryStatus+" = Discharging at "+batteryLevel+" %");
            }

            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){
                Log.d("name",currentBatteryStatus+"= Battery Full at "+batteryLevel+" %");

            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){
                Log.d("name",currentBatteryStatus+" = Charging at "+batteryLevel+" %");
            }


            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                Log.d("name",currentBatteryStatus+" = Not Charging at "+batteryLevel+" %");
            }

        }
    };




}
