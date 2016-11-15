package com.projeto.horadorango.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mell on 25/10/2016.
 */
@DatabaseTable(tableName = "PEDIDO")
public class Pedido implements Serializable {

    public final static String ID = "id";
    public final static String DATA = "data";
    public final static String STATUS = "status";
    public final static String USUARIO_ID = "usuario_id";
    public final static String EMPRESA_ID = "empresa_id";
    public final static String HORA_AGEND_ENTREGA = "hora_agend_entrega";
    public final static String HORARIO_ENTREGA = "horario_entrega";
    public final static String ENDERECO_ID = "endereco_id";

    @DatabaseField(columnName = ID)
    private long id;

    @DatabaseField(columnName = DATA)
    private Date data;

    @DatabaseField(columnName = STATUS)
    private String status;

    @DatabaseField(columnName = HORA_AGEND_ENTREGA)
    private int hora_agend_entrega;

    @DatabaseField(columnName = HORARIO_ENTREGA)
    private int horario_entrega;

    @DatabaseField(columnName = USUARIO_ID, foreign = true, foreignAutoRefresh = true)
    private Usuario usuario;

    @DatabaseField(columnName = EMPRESA_ID, foreign = true, foreignAutoRefresh = true)
    private Empresa empresa;

    @DatabaseField(columnName = ENDERECO_ID, foreign = true, foreignAutoRefresh = true)
    private Endereco endereco;

    public static String getID() {
        return ID;
    }

    public static String getDATA() {
        return DATA;
    }

    public static String getSTATUS() {
        return STATUS;
    }

    public static String getUsuarioId() {
        return USUARIO_ID;
    }

    public static String getEmpresaId() {
        return EMPRESA_ID;
    }

    public static String getHoraAgendEntrega() {
        return HORA_AGEND_ENTREGA;
    }

    public static String getHorarioEntrega() {
        return HORARIO_ENTREGA;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHora_agend_entrega() {
        return hora_agend_entrega;
    }

    public void setHora_agend_entrega(int hora_agend_entrega) {
        this.hora_agend_entrega = hora_agend_entrega;
    }

    public int getHorario_entrega() {
        return horario_entrega;
    }

    public void setHorario_entrega(int horario_entrega) {
        this.horario_entrega = horario_entrega;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static String getEnderecoId() {
        return ENDERECO_ID;
    }
}
