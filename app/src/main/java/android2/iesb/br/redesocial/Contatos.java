package android2.iesb.br.redesocial;

import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static java.util.UUID.*;

/**
 * Created by lucasdiogo on 03/09/16.
 */

public class Contatos extends RealmObject{

    @PrimaryKey
    private String uuid;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String foto;



    public String getUuid() {
        return uuid;
    }

    public void setUuid() {
        uuid = randomUUID().toString();;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    



    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}
