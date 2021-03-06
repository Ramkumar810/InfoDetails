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
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

/**
 * Created by kannas on 6/21/2017.
 */

public class Userinformation extends AppCompatActivity {
    public static String applicationMemoryUsage;
    public static String applicationVersion;
    public static String batteryPercentage;
    public static String batteryState;
    public static String deviceName;
    public static String deviceLanguage;
    public static String deviceCountry;
    public static String deviceId;
    public static String deviceModel;
    public static String deviceOsVersion;
    public static String deviceCity;
    public static String deviceVersionRelease;
    int deviceStatus;
    IntentFilter intentfilter;
    String deviceIdName;
    public Context mContext;
    private static String acccess;
    public Userinformation(Context context){
        this.mContext=context;
        userBattery();
        isTablet();
    }
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
            applicationMemoryUsage= String.valueOf(percentAvail);
            Log.e("percentAvail",percentAvail+"");

        }
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            String packageName=info.packageName;
            Log.d("packageName",packageName+"");
            String versionName = info.versionName;
            applicationVersion=versionName;
            Log.e("version",versionName+"");
            int versionNumber=info.versionCode;
            Log.e("version",versionNumber+"");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String  androidDeviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        deviceId=androidDeviceId;
        Log.e("DeviceId",androidDeviceId+"");
        String manufacturer =android.os.Build.MANUFACTURER;
        deviceName=manufacturer;
        String model = Build.MODEL;
        deviceModel=model;
        int version = Build.VERSION.SDK_INT;
        deviceOsVersion= String.valueOf(version);
        String versionRelease = Build.VERSION.RELEASE;
        deviceVersionRelease=versionRelease;
        deviceCountry=Locale.getDefault().getDisplayCountry();
        deviceLanguage=Locale.getDefault().getDisplayLanguage();
        Log.e("MyActivity", "manufacturer " + manufacturer
                + " \n model " + model
                + " \n version " + version
                + " \n versionRelease " + versionRelease
                + " \n Device Language "+  Locale.getDefault().getDisplayLanguage()
                + " \n Device Country " + Locale.getDefault().getDisplayCountry()


        );

    }
   /* public BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryLevel=(int)(((float)level / (float)scale) * 100.0f);
            batteryPercentage=String.valueOf(batteryLevel + " %");
            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){
                Log.e("batteryPercentage",currentBatteryStatus+" = Charging at "+batteryLevel+" %");
                batteryState="Charging";
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){
                Log.e("batteryPercentage",currentBatteryStatus+" = Discharging at "+batteryLevel+" %");
                batteryPercentage=String.valueOf(batteryLevel + " %");
                batteryState=" Discharging";
            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){
                Log.e("batteryPercentage",currentBatteryStatus+"= Battery Full at "+batteryLevel+" %");
                batteryPercentage=String.valueOf(batteryLevel + " %");
                batteryState="Battery Full";
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){
                Log.e("batteryPercentage",currentBatteryStatus+" = Charging at "+batteryLevel+" %");
                batteryPercentage=String.valueOf(batteryLevel + " %");
                batteryState="Charging";
            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                Log.e("batteryPercentage",currentBatteryStatus+" = Not Charging at "+batteryLevel+" %");
                batteryPercentage=String.valueOf(batteryLevel + " %");
                batteryState="Not Charging";
            }
            //userIp(context);

        }
    };*/
    public void userBattery(){
        int level=0;
        int scale=0;
        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = mContext.registerReceiver(null, intentfilter);
        if (batteryStatus != null) {
            deviceStatus = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){
                batteryState="Charging";
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){
                batteryState=" Discharging";
            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){
                batteryState="Battery Full";
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){
                batteryState="Charging";
            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                batteryState="Not Charging";
            }

        }
        if (batteryStatus != null) {
             level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        }
        if (batteryStatus != null) {
             scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }
        int batteryLevel=(int)(((float)level / (float)scale) * 100.0f);
        batteryPercentage=String.valueOf(batteryLevel + " %");

        //context.registerReceiver(broadcastreceiver,intentfilter);
        userIp(mContext);


    }
    public String getDeviceInformation(String deviceId){
        //deviceId = Settings.Secure.getString(context.getContentResolver(),
        deviceId= Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return deviceId;
    }
    public boolean isTablet() {
        boolean xlarge = ((mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
    public void crashReport(){
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                Log.e("Alert","Lets See if it Works !!!");
            }
        });
    }


}
