package com.projeto.horadorango.model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;
/**
 * Created by Mell on 25/10/2016.
 */
public class PedidoItem extends RealmObject {

    @PrimaryKey
    private int id;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;
    private double valor_item;

    public int getId() {
        return id;
    }

    public PedidoItem setId(int id) {
        this.id = id;
        return this;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public PedidoItem setPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public Produto getProduto() {
        return produto;
    }

    public PedidoItem setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public PedidoItem setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public double getValor_item() {
        return valor_item;
    }

    public PedidoItem setValor_item(double valor_item) {
        this.valor_item = valor_item;
        return this;
    }

    public static PedidoItem create(Realm realm){
        return realm.createObject(PedidoItem.class, getNextPrimaryKey(realm, PedidoItem.class));
    }
}
