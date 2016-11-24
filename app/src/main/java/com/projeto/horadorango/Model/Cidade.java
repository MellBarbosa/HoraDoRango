package com.projeto.horadorango.model;


import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;

/**
 * Created by Mell on 24/10/2016.
 */
public class Cidade extends RealmObject {

    @PrimaryKey
    private int id;

    private String descricao;

    private String uf;

    public int getId() {
        return id;
    }

    public Cidade setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Cidade setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public Cidade setUf(String uf) {
        this.uf = uf;
        return this;
    }

    @Override
    public String toString(){
        return descricao;
    }

    public static Cidade create(Realm realm){
        return realm.createObject(Cidade.class, getNextPrimaryKey(realm, Cidade.class));
    }

}
