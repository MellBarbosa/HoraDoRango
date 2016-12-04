package com.projeto.horadorango.model;

import java.util.List;

/**
 * Created by Mell on 03/12/2016.
 */

public class Sincronizacao {

    private List<Bairro> bairros;
    private List<Categoria> categorias;
    private List<Cidade> cidades;
    private List<Endereco> enderecos;
    private List<Produto> produtos;
    private List<Empresa> empresas;

    public List<Bairro> getBairros() {
        return bairros;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public List<Empresa> getEmpresas() { return empresas; }

    public List<Categoria> getCategorias() { return categorias; }

    public List<Endereco> getEnderecos() { return enderecos; }

    public List<Produto> getProdutos() { return produtos; }
}
