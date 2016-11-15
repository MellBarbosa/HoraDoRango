package com.projeto.horadorango.DAO;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mell on 27/10/2016.
 */

public class GenericDao<E> extends Banco<E> {

    protected Dao<E, Integer> dao;
    private Class<E> type;

    public GenericDao(Context context, Class<E> modelClass) throws SQLException {
        super(context);
        this.type = modelClass;
        setDao();
    }

    protected void setDao() throws SQLException  {
        this.dao = DaoManager.createDao(getConnectionSource(), this.type);
    }

    public void delete(E model)  throws SQLException {
        this.dao.delete(model);
        return;
    }

    public void deleteByCod(int paramCod) throws SQLException  {
        this.dao.deleteById(paramCod);
        return;
    }

    public List<E> getAll() throws SQLException  {
        List<E> list = this.dao.queryForAll();
        return list;
    }

    public E getByCod(long cod) throws SQLException  {
        Integer intCod = Integer.parseInt(cod + "");
        E model = this.dao.queryForId(intCod);
        return model;
    }

    public void insert(E model) throws SQLException  {
        this.dao.create(model);

        model.toString();
    }

    public void update(E model) throws SQLException  {
        this.dao.update(model);
    }
    public void deleteAll() throws SQLException {
        this.dao.deleteBuilder().delete();
    }

    public Dao getCurrentDao() throws SQLException {
        return this.dao;
    }
}