package android2.iesb.br.redesocial;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;


/**
 * Created by lucasdiogo on 05/10/16.
 */

public class LocationService extends Service {

    public static final String SERVICE_TERMINATED = "br.iesb.map.SERVICE_TERMINATED";
    private boolean running;
    Intent sendLocation = new Intent(MapsActivity.SEND_LOCATION);
    LatLng loc;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("testeService", "Service Create");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiverEncerraService, new IntentFilter(SERVICE_TERMINATED));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        running = true;
        Log.d("testeService", "Service Start");
        new ConsultaLocalizacao().start();
        return super.onStartCommand(intent, flags, startId);
    }


    class ConsultaLocalizacao extends Thread {
        @Override
        public void run() {
            super.run();

            while (running){
 //               Log.d("testeService", "Service Executando");
//                sendLocation.putExtra("LOC", loc);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(sendLocation);
            }
        }
    }

    @Override
    public void onDestroy() {
        running = false;

        super.onDestroy();
    }

    private BroadcastReceiver receiverEncerraService = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("testeService", "Service Destroy");
            running = false;
//            LatLng loc = intent.getParcelableExtra("LOC");
//            updateMap(loc);
        }
    };


}