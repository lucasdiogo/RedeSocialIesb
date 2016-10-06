package android2.iesb.br.redesocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CadastroActivity extends AppCompatActivity {

    private Realm realm;
    private RealmConfiguration realmConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);



        Button btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText tvNome = (EditText) findViewById(R.id.etNome);
                EditText tvSobrenome = (EditText) findViewById(R.id.etSobrenome);
                EditText tvEmail = (EditText) findViewById(R.id.etEmail);
                EditText tvTelefone = (EditText) findViewById(R.id.etTelefone);
                EditText tvSenha = (EditText) findViewById(R.id.etSenhaCadastro);
                EditText tvConfirmaSenha = (EditText) findViewById(R.id.etConfirmaSenha);
                String senha1 = tvSenha.getText().toString();
                String senha2 = tvConfirmaSenha.getText().toString();

                if (senha2.equals(senha1)) {
                    Login usuarioLogin = new Login();
                    Contatos contato = new Contatos();
                    usuarioLogin.setUsuario(tvEmail.getText().toString());
                    usuarioLogin.setSenha(tvSenha.getText().toString());
                    inserirUsuario(realm, usuarioLogin);
                    contato.setNome(tvNome.getText().toString());
                    contato.setSobrenome(tvSobrenome.getText().toString());
                    contato.setEmail(tvEmail.getText().toString());
                    contato.setTelefone(tvTelefone.getText().toString());
                    inserirContato(realm, contato);

                    Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    alert("Senha não Confere.");
                }

            }


        });

    }

    private void inserirUsuario (Realm realm, final Login login){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Login login1 = new Login();

                login1 = realm.createObject(Login.class);

                login1.setUsuario(login.getUsuario());
                login1.setSenha(login.getSenha());
            }
        });
    }

    private void inserirContato (Realm realm, final Contatos contato){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Contatos contato1 = realm.createObject(Contatos.class);

                contato1.setNome(contato.getNome());
                contato1.setSobrenome(contato.getSobrenome());
                contato1.setEmail(contato.getEmail());
                contato1.setTelefone(contato.getTelefone());
                contato1.setUuid();
            }
        });

    }

    private void alert (String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();
    }

}
