package android2.iesb.br.redesocial;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public  Realm realm;
    private RealmConfiguration realmConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Firebase.setAndroidContext(this);

        Intent intentService = new Intent(this, LocationService.class);
        startService(intentService);

        realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getInstance(realmConfig);
        createAlarm();


        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                TextView tvUsuario = (TextView) findViewById(R.id.etUsuario);
                TextView tvSenha = (TextView) findViewById(R.id.etSenha);

                Login usuarioLogin = new Login();
                usuarioLogin.setUsuario(tvUsuario.getText().toString());
                usuarioLogin.setSenha(tvSenha.getText().toString());

                validaUsuario(realm, usuarioLogin);

            }
        });

        TextView tvCadastrar = (TextView) findViewById(R.id.tvCadastro);
        tvCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createAlarm(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60 * 1000), 60 * 1000 , pendingIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent endIntent = new Intent(LocationService.SERVICE_TERMINATED);
        LocalBroadcastManager.getInstance(this).sendBroadcast(endIntent);
    }

    private void validaUsuario(Realm realm, Login login){
        RealmResults<Login> logins  = realm.where(Login.class).equalTo("usuario", login.getUsuario()).findAll();
        if (logins.size() > 0){
            String senhaUsuario = logins.get(0).getSenha();
            String senhaDigitada = login.getSenha();

            if (senhaUsuario.equals(senhaDigitada)) {
                Intent intent = new Intent(MainActivity.this, ContatosActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Usuário ou Senha incorreto.",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Usuário ou Senha incorreto.",Toast.LENGTH_SHORT).show();
        }

    }


}
