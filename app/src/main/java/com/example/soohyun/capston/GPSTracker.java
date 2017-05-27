package com.example.soohyun.capston;

/**
 * Created by sooHyun on 2017-05-23.
 */

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by JaeBeome on 2017. 5. 20..
 */


public class GPSTracker extends Service implements LocationListener {
   //locationManager 객체 선언

    private LocationManager locationManager;
    private Location previous_location, current_location;
    double total_moved_distance;
    private boolean isGPSon = false;

    public GPSTracker() {//생성한 후 사용하기 여기서 초기화
        previous_location = null;
        current_location = null;
        total_moved_distance = 0;
    }

    public void startGPS(Context context, Activity activity) {//gps사용 시작 context : getApplicationContext()로 activity는 현재 클래스이름.this
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//ACCESS_FINE_LOACTION permission check

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, this);//2초마다 혹은 5미터? 움직일 때마다 갱신
        current_location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        isGPSon = true;

    }

    public double getDistance() {//총 움직인 거리 m단위로 반환
        return total_moved_distance * 1609.34;
    }

    public void stopGps() {//gps사용 정지 및 초기화
        locationManager.removeUpdates(this);
        previous_location = null;
        current_location = null;
        total_moved_distance = 0;
        isGPSon = false;
    }

    public boolean isGPSon() {
        return isGPSon;
    }


    @Override
    public void onLocationChanged(Location location) {

        previous_location = current_location;
        current_location = location;
        total_moved_distance += calc_distance(previous_location, current_location);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public double calc_distance(Location start, Location dest) {
        double s_lat, s_lon, d_lat, d_lon, distance_lat, distance_lon, distance;
        s_lat = start.getLatitude();
        s_lon = start.getLongitude();
        d_lat = dest.getLatitude();
        d_lon = dest.getLongitude();

        distance_lat = 69.1 * (d_lat - s_lat);
        distance_lon = 53 * (d_lon - s_lon);

        distance = Math.sqrt((distance_lat * distance_lat) + (distance_lon * distance_lon));

        return distance;
    }

}