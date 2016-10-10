package android2.iesb.br.redesocial;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public  Realm realm;
    private RealmConfiguration realmConfig;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private TextView tvUsuario;
    private TextView tvSenha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        Firebase.setAndroidContext(this);


        realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getInstance(realmConfig);

        tvUsuario = (TextView) findViewById(R.id.etUsuario);
        tvSenha = (TextView) findViewById(R.id.etSenha);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
                // [START_EXCLUDE]
//                updateUI(user);
                // [END_EXCLUDE]
            }
        };


        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                mAuth.signInWithEmailAndPassword(tvUsuario.getText().toString(), tvSenha.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"Usuário ou Senha incorreto.",Toast.LENGTH_SHORT).show();
                                }else{
//                                    FirebaseUser user = task.getResult().getUser();
//                                    if(user != null){
                                        Intent intent = new Intent(MainActivity.this, ContatosActivity.class);
                                        startActivity(intent);
//                                    }else{
//                                        Toast.makeText(MainActivity.this,"Usuário ou Senha incorreto.",Toast.LENGTH_SHORT).show();
//                                    }
                                }
                            }
                        }
                );

              //  Login usuarioLogin = new Login();
              //  usuarioLogin.setUsuario(tvUsuario.getText().toString());
              //  usuarioLogin.setSenha(tvSenha.getText().toString());

              //  validaUsuario(realm, usuarioLogin);

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

        createAlarm();
       Intent intentService = new Intent(this, LocationService.class);
        startService(intentService);
    }

    private void createAlarm(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60 * 1000), 60 * 1000 , pendingIntent);
        Log.d("testeAlarme", "Criar Alarme");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("testeAlarme", "Criar Alarme");
        Intent endIntent = new Intent(LocationService.SERVICE_TERMINATED);
        LocalBroadcastManager.getInstance(this).sendBroadcast(endIntent);
        stopService(new Intent(this, LocationService.class));
    }

 /*   private void validaUsuario(Realm realm, Login login){
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

    } */


}
