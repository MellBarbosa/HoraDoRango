package com.projeto.horadorango.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Mell on 25/10/2016.
 */
@DatabaseTable(tableName = "PEDIDO_ITEM")
public class PedidoItem implements Serializable {

    public final static String ID = "id";
    public final static String PEDIDO_ID = "pedido_id";
    public final static String PRODUTO_ID = "produto_id";
    public final static String QUANTIDADE = "quantidade";
    public final static String VALOR_ITEM = "valor_item";

    @DatabaseField(columnName = ID, id = true)
    private long id;

    @DatabaseField(columnName = PEDIDO_ID, foreign = true, foreignAutoRefresh = true)
    private Pedido pedido;

    @DatabaseField(columnName = PRODUTO_ID, foreign = true, foreignAutoRefresh = true)
    private Produto produto;

    @DatabaseField(columnName = QUANTIDADE)
    private int quantidade;

    @DatabaseField(columnName = VALOR_ITEM)
    private double valor_item;

    public static String getID() {
        return ID;
    }

    public static String getPedidoId() {
        return PEDIDO_ID;
    }

    public static String getProdutoId() {
        return PRODUTO_ID;
    }

    public static String getQUANTIDADE() {
        return QUANTIDADE;
    }

    public static String getValorItem() {
        return VALOR_ITEM;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_item() {
        return valor_item;
    }

    public void setValor_item(double valor_item) {
        this.valor_item = valor_item;
    }
}
