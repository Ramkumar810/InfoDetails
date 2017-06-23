package com.example.kannas.information;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.deviceinformation.Userinformation;

import static android.R.attr.level;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

      Userinformation userinformation;
    TextView textView;
   public static String deviceIdInfo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userinformation=new Userinformation(this);
        String userinfo=userinformation.getDeviceInformation(deviceIdInfo);
        Log.d("userInfo",userinfo+"");
        String applicationVersion=Userinformation.applicationVersion;
        String memory=Userinformation.applicationMemoryUsage;
        String batteryState=Userinformation.batteryState;
        String deviceName=Userinformation.deviceName;
        String deviceLanguage=Userinformation.deviceLanguage;
        String  deviceCountry=Userinformation.deviceCountry;
        String  deviceId=Userinformation.deviceId;
        String  deviceModel= Userinformation.deviceModel;
        String deviceOsVersion=Userinformation.deviceOsVersion;
        String deviceVersionRelease= Userinformation.deviceVersionRelease;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         textView=(TextView)findViewById(R.id.text);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        textView.setText(" Application Version : " + applicationVersion + "\n Device OS Version : " + deviceOsVersion + "\n Device Battery Percentage : " + Userinformation.batteryPercentage + "\n Device Battery State : " + batteryState
        + "\n Device Language : " + deviceLanguage + "\n Device Country : " + deviceCountry + "\n Device UUID : " + deviceId + "\n Application Memory Usage : " + memory + " \n Version Release : " + deviceVersionRelease
        + " \n Device Name : " + deviceName + "\n Device Type : " + deviceModel);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
