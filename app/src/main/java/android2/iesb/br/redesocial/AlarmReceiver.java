package android2.iesb.br.redesocial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;


/**
 * Created by lucasdiogo on 05/10/16.
 */

public class AlarmReceiver extends BroadcastReceiver{

    public static final String ACTION = "android2.iesb.br.redesocial.ALARM_ACTIVATE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("testeAlarme", "Alarme Start");
/*        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            LatLng loc = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());

           Intent updateIntent = new Intent(LocationService.ALARM_RECEIVED_EVENT);
            updateIntent.putExtra("LOC", loc);
            LocalBroadcastManager.getInstance(context).sendBroadcast(updateIntent);
        } */
    }
}
