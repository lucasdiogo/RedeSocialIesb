package android2.iesb.br.redesocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ContatosActivity extends AppCompatActivity {

    private List<Contatos> listaContatos = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContatosAdapter contatosAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        realm = Realm.getDefaultInstance();
        recyclerView = (RecyclerView) findViewById(R.id.rvcontatos);
        RealmResults<Contatos> results = realm.where(Contatos.class).findAll();
//        for (int i = 0; i < results.size(); i++) {
//            listaContatos = (List<Contatos>) results.get(i);
//        }
        listaContatos = results.subList(0,results.size());
        contatosAdapter = new ContatosAdapter(listaContatos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contatosAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.itcadastrar:
                Intent intent = new Intent(ContatosActivity.this, CadastroContatoActivity.class);
                startActivity(intent);
                return true;
            case R.id.itbluetooth:
                return true;
            case R.id.itmapa:
                Intent intent2 = new Intent(ContatosActivity.this, MapsActivity.class);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
