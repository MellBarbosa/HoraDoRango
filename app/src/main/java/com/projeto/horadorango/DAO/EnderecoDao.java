package com.projeto.horadorango.DAO;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.QueryBuilder;
import com.projeto.horadorango.Model.Endereco;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mell on 14/11/2016.
 */

public class EnderecoDao extends GenericDao<Endereco> {

    Context context;

    public EnderecoDao(Context context) throws SQLException{
        super(context, Endereco.class);
    }

    public List<Endereco> pesquisarPorNome(String nome) throws SQLException {
        EnderecoDao DAO = new EnderecoDao(context);
        QueryBuilder<Endereco,Integer> QB = DAO.getCurrentDao().queryBuilder();
        try {
            QB.where().eq(Endereco.ENDERECO, nome);
            return QB.query();
        } catch (SQLException e) {
            Log.e("Pesquisa Endereço", e.getMessage());
            return null;
        }
    }

    public void salvar(Endereco en) throws SQLException {
        QueryBuilder<Endereco,Integer> qry = dao.queryBuilder();
        qry.where().eq(Endereco.ID, en.getId());
        List<Endereco> lista = qry.query();
        if(lista.size()==0)
            insert(en);
        else
            update(en);
    }

}
