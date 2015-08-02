package com.zjiajun.firstapp.activity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.utils.HttpUtil;

import java.util.List;
import java.util.Map;

public class LocationActivity extends BaseActivity {

    private static final String TAG = "LocationActivity";
    private TextView tv_location;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        String provider;
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(LocationActivity.this, "No Location Provider to use", Toast.LENGTH_LONG).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            showLocation(location);
        }
        locationManager.requestLocationUpdates(provider, 5 * 1000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i(TAG, "onLocationChanged : " + location.toString());
                showLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i(TAG, "onStatusChanged : " + provider + "---" + status);
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.i(TAG, "onProviderEnabled : " + provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.i(TAG, "onProviderDisabled : " + provider);
            }
        });
    }

    private void showLocation(Location location) {
        final StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/geocode/json?address=");
        stringBuilder.append(location.getLatitude()).append(",").append(location.getLongitude())
                .append("&sensor=false");
        String content = HttpUtil.sendHttpGetRequest(stringBuilder.toString(),true);
        if (!TextUtils.isEmpty(content)) {
            Map map = new Gson().fromJson(content, Map.class);
            if (map != null && "OK".equals(map.get("status"))) {
                Object results = map.get("results");
                List list = (List) results;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    Map mMap = ((Map) list.get(i));
                    String formattedAddress = mMap.get("formatted_address").toString() + "\n";
                    sb.append(formattedAddress);
                }
                tv_location.setText(sb.toString());
            } else {
                tv_location.setText("Not Found");
            }
        } else {
            Toast.makeText(LocationActivity.this, "fucking bug!!!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_location);
    }

    @Override
    protected void initViews() {
        tv_location = (TextView) findViewById(R.id.tv_location);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
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
}
