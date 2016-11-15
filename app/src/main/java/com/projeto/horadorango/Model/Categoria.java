package com.projeto.horadorango.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Mell on 25/10/2016.
 */
@DatabaseTable(tableName = "CATEGORIA")
public class Categoria implements Serializable {

    public final static String ID = "id";
    public final static String DESCRICAO = "descricao";


    @DatabaseField(columnName = ID, id = true)
    private long id;

    @DatabaseField(columnName = DESCRICAO)
    private String descricao;

    public static String getID() {
        return ID;
    }

    public static String getDESCRICAO() {
        return DESCRICAO;
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
}
