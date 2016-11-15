package com.projeto.horadorango.DAO;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.QueryBuilder;
import com.projeto.horadorango.Model.Bairro;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mell on 31/10/2016.
 */

public class BairroDao extends GenericDao {

    Context context;

    public BairroDao(Context context) throws SQLException {
        super(context, Bairro.class);
    }

    public List<Bairro> pesquisarPorNome(String nome) throws SQLException {
        BairroDao DAO = new BairroDao(context);
        QueryBuilder<Bairro,Integer> QB = DAO.getCurrentDao().queryBuilder();
        try {
            QB.where().eq(Bairro.DESCRICAO, nome);
            return QB.query();
        } catch (SQLException e) {
            Log.e("Pesquisa Descrição", e.getMessage());
            return null;
        }
    }
}
