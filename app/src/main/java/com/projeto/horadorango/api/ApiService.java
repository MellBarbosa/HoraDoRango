package com.projeto.horadorango.api;

import com.projeto.horadorango.model.Bairro;
import com.projeto.horadorango.model.Categoria;
import com.projeto.horadorango.model.Cidade;
import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.model.Produto;
import com.projeto.horadorango.model.Sincronizacao;
import com.projeto.horadorango.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Mell on 01/12/2016.
 */

public interface ApiService {

 //   @POST("/login")
 //   Call<Usuario> basicLogin();

    @GET("api/sincronizar")
    Call<Sincronizacao> sincronizar();

}