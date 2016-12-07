package com.projeto.horadorango.model;

import org.parceler.Parcel;

import io.realm.BairroRealmProxy;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;
/**
 * Created by Mell on 24/10/2016.
 */
@Parcel(implementations = { BairroRealmProxy.class },
        value = Parcel.Serialization.FIELD, analyze = { Bairro.class })
public class Bairro extends RealmObject {

    @PrimaryKey
    private int id;

    private String descricao;

    public int getId() {
        return id;
    }

    public Bairro setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Bairro setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public String toString(){
        return descricao;
    }

    public static Bairro create(Realm realm){
        return realm.createObject(Bairro.class, getNextPrimaryKey(realm, Bairro.class));
    }

}
