package com.projeto.horadorango.model;


import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;

/**
 * Created by Mell on 24/10/2016.
 */
public class Endereco extends RealmObject{

    @PrimaryKey
    private long id;
    private String endereco;
    private Bairro bairro;
    private String cep;
    private String numero;
    private String complemento;
    private Cidade cidade;
    private Usuario usuario;
    private Empresa empresa;
    private String ponto_referencia;

    public long getId() {
        return id;
    }

    public Endereco setId(long id){
        this.id = id;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public Endereco setEndereco(String endereco){
        this.endereco = endereco;
        return this;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public Endereco setBairro(Bairro bairro){
        this.bairro = bairro;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Endereco setCep(String cep){
        this.cep = cep;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public Endereco setNumero(String numero){
        this.numero = numero;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Endereco setComplemento(String complemento){
        this.complemento = complemento;
        return this;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Endereco setCidade(Cidade cidade){
        this.cidade = cidade;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Endereco setUsuario(Usuario usuario){
        this.usuario = usuario;
        return this;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Endereco setEmpresa(Empresa empresa){
        this.empresa = empresa;
        return this;
    }

    public String getPonto_referencia() {
        return ponto_referencia;
    }

    public Endereco setPonto_referencia(String ponto_referencia){
        this.ponto_referencia = ponto_referencia;
        return this;
    }

    public static Endereco create(Realm realm){
        return realm.createObject(Endereco.class, getNextPrimaryKey(realm, Endereco.class));
    }
}
