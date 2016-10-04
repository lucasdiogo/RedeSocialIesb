package android2.iesb.br.redesocial;





import io.realm.RealmObject;

import io.realm.annotations.PrimaryKey;


/**
 * Created by lucasdiogo on 06/09/16.
 */

public class Login extends RealmObject{

    @PrimaryKey
    private String usuario;
    private String senha;


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
