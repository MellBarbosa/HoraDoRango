package com.projeto.horadorango.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.StringBytesType;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Mell on 24/10/2016.
 */
@DatabaseTable(tableName = "ENDERECO")
public class Endereco implements Serializable{

    public final static String ID = "id";
    public final static String ENDERECO = "endereco";
    public final static String BAIRRO_ID = "bairro_id";
    public final static String CEP = "cep";
    public final static String NUMERO = "numero";
    public final static String COMPLEMENTO = "complemento";
    public final static String CIDADE_ID = "cidade_id";
    public final static String USUARIO_ID = "usuario_id";
    public final static String EMPRESA_ID = "empresa_id";
    public final static String PONTO_REFERENCIA = "ponto_referencia";

    @DatabaseField(columnName = ID, id = true, generatedId = true)
    private long id;

    @DatabaseField(columnName = ENDERECO)
    private String endereco;

    @DatabaseField(columnName = CEP)
    private String cep;

    @DatabaseField(columnName = NUMERO)
    private int numero;

    @DatabaseField(columnName = COMPLEMENTO)
    private String complemento;

    @DatabaseField(columnName = PONTO_REFERENCIA)
    private String ponto_referencia;

    @DatabaseField(columnName = BAIRRO_ID, foreign = true, foreignAutoRefresh = true)
    private Bairro bairro;

    @DatabaseField(columnName = CIDADE_ID, foreign = true, foreignAutoRefresh = true)
    private Cidade cidade;

    @DatabaseField(columnName = USUARIO_ID, foreign = true, foreignAutoRefresh = true)
    private Usuario usuario;

    @DatabaseField(columnName = EMPRESA_ID, foreign = true, foreignAutoRefresh = true)
    private Empresa empresa;


    public static String getID() {
        return ID;
    }

    public static String getENDERECO() {
        return ENDERECO;
    }

    public static String getBairroId() {
        return BAIRRO_ID;
    }

    public static String getCEP() {
        return CEP;
    }

    public static String getNUMERO() {
        return NUMERO;
    }

    public static String getCOMPLEMENTO() {
        return COMPLEMENTO;
    }

    public static String getCidadeId() {
        return CIDADE_ID;
    }

    public static String getUsuarioId() {
        return USUARIO_ID;
    }

    public static String getEmpresaId() {
        return EMPRESA_ID;
    }

    public static String getPontoReferencia() {
        return PONTO_REFERENCIA;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPonto_referencia() {
        return ponto_referencia;
    }

    public void setPonto_referencia(String ponto_referencia) {
        this.ponto_referencia = ponto_referencia;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
}
