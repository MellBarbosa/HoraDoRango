package com.projeto.horadorango.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Mell on 24/10/2016.
 */
@DatabaseTable(tableName = "USUARIO")
public class Usuario implements Serializable {

    public final static String ID = "id";
    public final static String LOGIN = "login";
    public final static String TELEFONE = "telefone";
    public final static String EMAIL = "email";
    public final static  String SENHA = "senha";
    public final static String TERMOS = "termos";

    @DatabaseField(columnName = ID, id = true)
    private long id;

    @DatabaseField(columnName = LOGIN)
    private String login;

    @DatabaseField(columnName = TELEFONE)
    private String telefone;

    @DatabaseField(columnName = EMAIL)
    private String email;

    @DatabaseField(columnName = SENHA)
    private String senha;

    @DatabaseField(columnName = TERMOS)
    private boolean termos;

    public static String getID() {
        return ID;
    }

    public static String getLOGIN() {
        return LOGIN;
    }

    public static String getTELEFONE() {
        return TELEFONE;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getSENHA() {
        return SENHA;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static String getTermos() { return TERMOS; }

    public boolean isTermos() { return termos; }

    public void setTermos(boolean termos) { this.termos = termos; }
}
