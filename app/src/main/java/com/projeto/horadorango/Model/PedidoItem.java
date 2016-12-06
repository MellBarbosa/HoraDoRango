package com.projeto.horadorango.model;

import org.parceler.Parcel;

import io.realm.PedidoItemRealmProxy;
import io.realm.Realm;
import io.realm.RealmObject;

@Parcel(implementations = { PedidoItemRealmProxy.class },
        value = Parcel.Serialization.FIELD, analyze = { PedidoItem.class })
public class PedidoItem extends RealmObject {

    private Produto produto;
    private int quantidade;
    private double valor_item;
    private long produto_id;

    public Produto getProduto() {
        return produto;
    }

    public PedidoItem setProduto(Produto produto) {
        this.produto = produto;
        this.produto_id = produto.getId();
        this.valor_item = produto.getValor();
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

    public static PedidoItem create(Realm realm) {
        return realm.createObject(PedidoItem.class);
    }
}
