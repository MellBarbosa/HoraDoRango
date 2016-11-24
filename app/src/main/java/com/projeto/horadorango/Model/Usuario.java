package com.projeto.horadorango.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;

/**
 * Created by Mell on 24/10/2016.
 */
public class Usuario extends RealmObject {


    @PrimaryKey
    private int id;
    private String login;
    private String telefone;
    private String email;
    private String senha;
    private boolean termos;

    public int getId() {
        return id;
    }

    public Usuario setId(int id){
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Usuario setLogin(String login){
        this.login = login;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Usuario setTelefone(String telefone){
        this.telefone = telefone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email){
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha){
        this.senha = senha;
        return this;
    }
    public boolean isTermos() {
        return termos;
    }

    public Usuario setTermos(boolean termos){
        this.termos = termos;
        return this;
    }

    public static Usuario create(Realm realm){
        return realm.createObject(Usuario.class, getNextPrimaryKey(realm,Usuario.class));
    }
}
