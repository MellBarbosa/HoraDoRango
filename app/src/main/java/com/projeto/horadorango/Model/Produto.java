package com.projeto.horadorango.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Mell on 24/10/2016.
 */
@DatabaseTable(tableName = "PRODUTO")
public class Produto implements Serializable {

    public final static String ID = "id";
    public final static String DESCRICAO = "descricao";
    public final static String VALOR = "valor";
    public final static String CATEGORIA_ID = "categoria_id";
    public final static String EMPRESA_ID = "empresa_id";

    @DatabaseField(columnName = ID, id = true)
    private long id;

    @DatabaseField(columnName = DESCRICAO)
    private String descricao;

    @DatabaseField(columnName = VALOR)
    private double valor;

    @DatabaseField(columnName = CATEGORIA_ID, foreign = true, foreignAutoRefresh = true)
    private Categoria categoria;

    @DatabaseField(columnName = EMPRESA_ID, foreign = true, foreignAutoRefresh = true)
    private Empresa empresa;

    public static String getID() {
        return ID;
    }

    public static String getDESCRICAO() {
        return DESCRICAO;
    }

    public static String getVALOR() {
        return VALOR;
    }

    public static String getCategoriaId() {
        return CATEGORIA_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public static String getEmpresaId() { return EMPRESA_ID;   }

    public Empresa getEmpresa() { return empresa; }

    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
}
