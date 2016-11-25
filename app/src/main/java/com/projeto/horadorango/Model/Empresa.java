package com.projeto.horadorango.model;

import android.support.v7.view.menu.MenuPresenter;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;

/**
 * Created by Mell on 24/10/2016.
 */
public class Empresa extends RealmObject {


    @PrimaryKey
    private int id;
    private String nome_fantasia;
    private String horario_func;
    private String tel1;
    private String tel2;
    private String tel3;
    private String horario_entrega;
    private double taxa_entrega;
    private Endereco endereco;

    public int getId(){
        return id;
    }

    public Empresa setId(int id){
        this.id = id;
        return this;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public Empresa setNome_fantasia(String nome_fantasia){
        this.nome_fantasia = nome_fantasia;
        return this;
    }

    public String getHorario_func() {
        return horario_func;
    }

    public Empresa setHorario_func(String horario_func){
        this.horario_func = horario_func;
        return this;
    }

    public String getTel1() {
        return tel1;
    }

    public Empresa setTel1(String tel1){
        this.tel1 = tel1;
        return this;
    }

    public String getTel2() {
        return tel2;
    }

    public Empresa setTel2(String tel2){
        this.tel2 = tel2;
        return this;
    }

    public String getTel3() {
        return tel3;
    }

    public Empresa setTel3(String tel3){
        this.tel3 = tel3;
        return this;
    }

    public String getHorario_entrega() {
        return horario_entrega;
    }

    public Empresa setHorario_entrega(String horario_entrega){
        this.horario_entrega = horario_entrega;
        return this;
    }

    public double getTaxa_entrega() {
        return taxa_entrega;
    }

    public void setTaxa_entrega(double taxa_entrega) {
        this.taxa_entrega = taxa_entrega;
    }

    public static Empresa create(Realm realm){
        return realm.createObject(Empresa.class, getNextPrimaryKey(realm, Empresa.class));
    }

    public String toString(){
        return nome_fantasia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Empresa setEndereco(Endereco endereco){
        this.endereco = endereco;
        return this;
    }

}
