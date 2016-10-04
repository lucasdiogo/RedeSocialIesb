package android2.iesb.br.redesocial;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CadastroContatoActivity extends AppCompatActivity {

    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);
        Button btCadastrar = (Button) findViewById(R.id.btCadastrarContato);
        btCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText tvNome = (EditText) findViewById(R.id.etNomeContato);
                EditText tvSobrenome = (EditText) findViewById(R.id.etSobrenomeContato);
                EditText tvTelefone = (EditText) findViewById(R.id.etTelefoneContato);

                Contatos contato = new Contatos();
                contato.setNome(tvNome.getText().toString());
                contato.setSobrenome(tvSobrenome.getText().toString());
                contato.setTelefone(tvTelefone.getText().toString());
                inserirContato(realm, contato);
                Intent intent = new Intent(CadastroContatoActivity.this, ContatosActivity.class);
                startActivity(intent);

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
                contato1.setTelefone(contato.getTelefone());
                contato1.setUuid();
            }
        });

    }


}
