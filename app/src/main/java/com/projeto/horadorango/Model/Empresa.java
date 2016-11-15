package com.projeto.horadorango.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Mell on 24/10/2016.
 */
@DatabaseTable(tableName = "EMPRESA")
public class Empresa implements Serializable {

    public final static String ID = "id";
    public final static String NOME_FANTASIA = "nome_fantasia";
    public final static String HORARIO_FUNC = "horario_func";
    public final static String TEL1 = "tel1";
    public final static String TEL2 = "tel2";
    public final static String TEL3 = "tel3";
    public final static String HORARIO_ENTREGA = "horario_entrega";
    public final static String TAXA_ENTREGA = "taxa_entrega";

    @DatabaseField(columnName = ID, id = true, generatedId = true)
    private long id;

    @DatabaseField(columnName = NOME_FANTASIA)
    private String nome_fantasia;

    @DatabaseField(columnName = HORARIO_FUNC)
    private String horario_func;

    @DatabaseField(columnName = TEL1)
    private String tel1;

    @DatabaseField(columnName = TEL2)
    private String tel2;

    @DatabaseField(columnName = TEL3)
    private String tel3;

    @DatabaseField(columnName = HORARIO_ENTREGA)
    private String horario_entrega;

    @DatabaseField(columnName = TAXA_ENTREGA)
    private String taxa_entrega;

    public static String getID() {
        return ID;
    }

    public static String getNomeFantasia() {
        return NOME_FANTASIA;
    }

    public static String getHorarioFunc() {
        return HORARIO_FUNC;
    }

    public static String getTEL1() {
        return TEL1;
    }

    public static String getTEL2() {
        return TEL2;
    }

    public static String getTEL3() {
        return TEL3;
    }

    public static String getHorarioEntrega() {
        return HORARIO_ENTREGA;
    }

    public static String getTaxaEntrega() {
        return TAXA_ENTREGA;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getHorario_func() {
        return horario_func;
    }

    public void setHorario_func(String horario_func) {
        this.horario_func = horario_func;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getHorario_entrega() {
        return horario_entrega;
    }

    public void setHorario_entrega(String horario_entrega) {
        this.horario_entrega = horario_entrega;
    }

    public String getTaxa_entrega() {
        return taxa_entrega;
    }

    public void setTaxa_entrega(String taxa_entrega) {
        this.taxa_entrega = taxa_entrega;
    }
}
