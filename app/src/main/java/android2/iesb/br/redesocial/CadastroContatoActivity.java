package android2.iesb.br.redesocial;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CadastroContatoActivity extends AppCompatActivity {

    private Realm realm;
    private RealmConfiguration realmConfig;
    private String funcao;
    EditText tvNome;
    EditText tvSobrenome;
    EditText tvEmail;
    EditText tvTelefone;
    Button btCadastrar;
    Contatos contato = new Contatos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);
        btCadastrar = (Button) findViewById(R.id.btCadastrarContato);
        tvNome = (EditText) findViewById(R.id.etNomeContato);
        tvSobrenome = (EditText) findViewById(R.id.etSobrenomeContato);
        tvEmail = (EditText) findViewById(R.id.etEmailContato);
        tvTelefone = (EditText) findViewById(R.id.etTelefoneContato);
//        Contatos contato = new Contatos();
        Bundle args = getIntent().getExtras();
        funcao = args.getString("funcao");
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);

        switch (funcao){
            case "consultar":

                contato.setUuid2(args.getString("uuid"));
                contato.setNome(args.getString("nome"));
                contato.setSobrenome(args.getString("sobreNome"));
                contato.setTelefone(args.getString("telefone"));
                contato.setEmail(args.getString("email"));
                contato.setFoto(args.getString("foto"));

                tvNome.setText(contato.getNome());
                tvSobrenome.setText(contato.getSobrenome());
                tvTelefone.setText(contato.getTelefone());
                tvEmail.setText(contato.getEmail());
                tvNome.setEnabled(false);
                tvSobrenome.setEnabled(false);
                tvTelefone.setEnabled(false);
                tvEmail.setEnabled(false);

                btCadastrar.setText("Voltar");

            case "cadastrar":

        }

        btCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CadastroContatoActivity.this, ContatosActivity.class);

                switch (funcao) {
                    case "consultar":
                        startActivity(intent);
                    case "cadastrar":
                        contato.setNome(tvNome.getText().toString());
                        contato.setSobrenome(tvSobrenome.getText().toString());
                        contato.setEmail(tvEmail.getText().toString());
                        contato.setTelefone(tvTelefone.getText().toString());
                        inserirContato(realm, contato);
                        startActivity(intent);
                }
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




}
