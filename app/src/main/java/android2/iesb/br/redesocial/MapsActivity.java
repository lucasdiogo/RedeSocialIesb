package android2.iesb.br.redesocial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.location.LocationListener;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final int MAP_PERMISSION_ACCESS_COURSE_LOCATION = 9999;
    public static final String SEND_LOCATION = "br.iesb.map.SEND_LOCATION";

    private Marker marker;
    private GoogleMap mMap;
    LocationManager locationManager;
    private int currentZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiverSendLocation, new IntentFilter(SEND_LOCATION));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MAP_PERMISSION_ACCESS_COURSE_LOCATION);
        } else {
//            getLocation();
        }

        // Add a marker in Sydney and move the camera
        //LatLng eu = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(eu).title("Estou Aqui..."));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(eu));
    }

/*    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    LatLng eu = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(eu).title("Eu"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eu, 10));
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }else{

        }
    }
*/
    protected void updateMap(LatLng loc) {
        if (marker != null) {
            marker.remove();
        }
        marker = mMap.addMarker(new MarkerOptions().position(loc).title("Última Localização!!!"));
        currentZoom = currentZoom == 10 ? 15 : 10;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, currentZoom));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MAP_PERMISSION_ACCESS_COURSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    getLocation();
                } else {
                    //Permissão negada
                }
                return;
            }
        }
    }

    private BroadcastReceiver receiverSendLocation = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
 //           Log.d("testeService", "Service UpdateMap");
/*            LatLng loc = intent.getParcelableExtra("LOC");
            updateMap(loc); */
        }
    };



}
