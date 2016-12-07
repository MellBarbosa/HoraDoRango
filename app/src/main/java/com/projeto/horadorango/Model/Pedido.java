package com.projeto.horadorango.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;

/**
 * Created by Mell on 25/10/2016.
 */
public class Pedido extends RealmObject {

    @PrimaryKey
    private int id;
    private Date data;
    private String status;
    private Usuario usuario;
    private Empresa empresa;
    private Date hora_agend_entrega;
    private Date horario_entrega;
    private Endereco endereco;
    private RealmList<PedidoItem> itens;

    @Ignore
    private Double Valor;

    public int getId() {
        return id;
    }

    public Pedido setId(int id) {
        this.id = id;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Pedido setData(Date data) {
        this.data = data;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Pedido setStatus(String status) {
        this.status = status;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pedido setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Pedido setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public Date getHora_agend_entrega() {
        return hora_agend_entrega;
    }

    public Pedido setHora_agend_entrega(Date hora_agend_entrega) {
        this.hora_agend_entrega = hora_agend_entrega;
        return this;
    }

    public Date getHorario_entrega() {
        return horario_entrega;
    }

    public Pedido setHorario_entrega(Date horario_entrega) {
        this.horario_entrega = horario_entrega;
        return this;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Pedido setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public Pedido setItens(RealmList<PedidoItem> itens) {
        this.itens = itens;
        return this;
    }

    public static Pedido create(Realm realm){
        return realm.createObject(Pedido.class, getNextPrimaryKey(realm, Pedido.class));
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }
}
