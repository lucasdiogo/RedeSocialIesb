package android2.iesb.br.redesocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.widget.TextView;

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
        realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getInstance(realmConfig);


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

    private void validaUsuario(Realm realm, Login login){
        RealmResults<Login> logins  = realm.where(Login.class).equalTo("usuario", login.getUsuario()).findAll();
        if (logins.size() > 0){
            Intent intent = new Intent(MainActivity.this, ContatosActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"Usuário ou Senha incorreto.",Toast.LENGTH_SHORT).show();
        }

    }
}