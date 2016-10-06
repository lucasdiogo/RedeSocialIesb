package android2.iesb.br.redesocial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



/**
 * Created by lucasdiogo on 05/10/16.
 */

public class AlarmReceiver extends BroadcastReceiver{

    public static final String ACTION = "android2.iesb.br.redesocial.ALARM_ACTIVATE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, LocationService.class);
        context.startService(i);
    }
}
