package com.projeto.horadorango.model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;

/**
 * Created by Mell on 24/10/2016.
 */
public class Produto extends RealmObject {

    @PrimaryKey
    private int id;
    private String descricao;
    private double valor;
    private Categoria categoria;
    private Empresa empresa;

    public int getId() {
        return id;
    }

    public Produto setId(int id){
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto setDescricao(String descricao){
        this.descricao = descricao;
        return this;
    }

    public double getValor() {
        return valor;
    }

    public Produto setValor(double valor){
        this.valor = valor;
        return this;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Produto setCategoria(Categoria categoria){
        this.categoria = categoria;
        return this;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Produto setEmpresa(Empresa empresa){
        this.empresa = empresa;
        return this;
    }

    @Override
    public String toString(){
        return descricao;
    }

    public static Produto create(Realm realm){
        return realm.createObject(Produto.class, getNextPrimaryKey(realm, Produto.class));
    }

}
