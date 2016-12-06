package com.projeto.horadorango.model;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.annotations.PrimaryKey;

import static com.projeto.horadorango.util.RealmUtil.getNextPrimaryKey;

/**
 * Created by Mell on 06/12/2016.
 */

public class PedidoRequest {

    private String status;
    private Date horario_entrega;
    private List<PedidoItem> itens;
    private int usuario_id;
    private long endereco_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getHorario_entrega() {
        return horario_entrega;
    }

    public void setHorario_entrega(Date horario_entrega) {
        this.horario_entrega = horario_entrega;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public long getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(long endereco_id) {
        this.endereco_id = endereco_id;
    }
}