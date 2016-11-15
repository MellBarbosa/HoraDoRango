package com.projeto.horadorango.DAO;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.QueryBuilder;
import com.projeto.horadorango.Model.Cidade;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mell on 31/10/2016.
 */

public class CidadeDao extends GenericDao {

    Context context;

    public CidadeDao(Context context) throws SQLException {
        super(context, Cidade.class);
    }

    public List<Cidade> pesquisarPorNome(String nome) throws SQLException {
        CidadeDao DAO = new CidadeDao(context);
        QueryBuilder<Cidade,Integer> QB = DAO.getCurrentDao().queryBuilder();
        try {
            QB.where().eq(Cidade.DESCRICAO, nome);
            return QB.query();
        } catch (SQLException e) {
            Log.e("Pesquisa Cidade", e.getMessage());
            return null;
        }
    }
}
