package com.projeto.horadorango.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Mell on 24/10/2016.
 */
@DatabaseTable(tableName = "CIDADE")
public class Cidade implements Serializable {

    public final static String ID = "id";
    public final static String DESCRICAO = "descricao";
    public final static String UF = "uf";

    @DatabaseField(columnName = ID, id = true)
    private long id;

    @DatabaseField(columnName = DESCRICAO)
    private String descricao;

    @DatabaseField(columnName = UF)
    private String uf;

    public Cidade(){

    }

    public static String getID() {
        return ID;
    }

    public static String getDESCRICAO() {
        return DESCRICAO;
    }

    public static String getUF() {
        return UF;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
