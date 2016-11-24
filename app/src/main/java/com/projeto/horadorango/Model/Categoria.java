package com.projeto.horadorango.model;


import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;

/**
 * Created by Mell on 25/10/2016.
 */
public class Categoria extends RealmObject {

    @PrimaryKey
    private int id;

    private String descricao;

    public int getId() {
        return id;
    }

    public Categoria setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public String toString(){
        return descricao;
    }

    public static Categoria create(Realm realm){
        return realm.createObject(Categoria.class, getNextPrimaryKey(realm, Categoria.class));
    }

}
