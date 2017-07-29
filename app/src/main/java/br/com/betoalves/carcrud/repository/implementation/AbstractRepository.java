package br.com.betoalves.carcrud.repository.implementation;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collection;
import java.util.List;

import br.com.betoalves.carcrud.repository.interfaces.IAbstractRepository;

/**
 * Created by Roberto on 18/07/2017.
 */

public abstract class AbstractRepository<T, K> implements IAbstractRepository<T, K> {
    protected AbstractDao<T, K> dao;

    protected AbstractRepository(AbstractDao<T, K> dao){
        this.dao = dao;
    }

    @Override
    public T load(K key) {
        return dao.load(key);
    }

    @Override
    public T loadByRowId(long rowId) {
        return dao.loadByRowId(rowId);
    }

    @Override
    public List<T> loadAll() {
        return dao.loadAll();
    }

    @Override
    public boolean detach(T entity) {
        return dao.detach(entity);
    }

    @Override
    public void detachAll() {
        dao.detachAll();
    }

    @Override
    public void insertInTx(Iterable<T> entities) {
        dao.insertInTx(entities);
    }

    @Override
    public void insertInTx(T[] entities) {
        dao.insertInTx(entities);
    }

    @Override
    public void insertInTx(Iterable<T> entities, boolean setPrimaryKey) {
        dao.insertInTx(entities, setPrimaryKey);
    }

    @Override
    public void insertOrReplaceInTx(Iterable<T> entities, boolean setPrimaryKey) {
        dao.insertOrReplaceInTx(entities, setPrimaryKey);
    }

    @Override
    public void insertOrReplaceInTx(Iterable<T> entities) {
        dao.insertOrReplaceInTx(entities);
    }

    @Override
    public void insertOrReplaceInTx(T[] entities) {
        dao.insertOrReplaceInTx(entities);
    }

    @Override
    public long insert(T entity) {
        return dao.insert(entity);
    }

    @Override
    public long insertWithoutSettingPk(T entity) {
        return dao.insertWithoutSettingPk(entity);
    }

    @Override
    public long insertOrReplace(T entity) {
        return dao.insertOrReplace(entity);
    }

    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Override
    public void saveInTx(T[] entities) {
        dao.saveInTx(entities);
    }

    @Override
    public void saveInTx(Iterable<T> entities) {
        dao.saveInTx(entities);
    }

    @Override
    public List queryRaw(String where, String... selectionArg) {
        return dao.queryRaw(where, selectionArg);
    }

    @Override
    public Query queryRawCreate(String where, Object... selectionArg) {
        return dao.queryRawCreate(where, selectionArg);
    }

    @Override
    public Query queryRawCreateListArgs(String where, Collection<Object> selectionArg) {
        return dao.queryRawCreateListArgs(where, selectionArg);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteByKey(K key) {
        dao.deleteByKey(key);
    }

    @Override
    public void deleteInTx(Iterable<T> entities) {
        dao.deleteInTx(entities);
    }

    @Override
    public void deleteInTx(T[] entities) {
        dao.deleteInTx(entities);
    }

    @Override
    public void deleteByKeyInTx(Iterable<K> keys) {
        dao.deleteByKeyInTx(keys);
    }

    @Override
    public void deleteByKeyInTx(K[] keys) {
        dao.deleteByKeyInTx(keys);
    }

    @Override
    public void refresh(T entity) {
        dao.refresh(entity);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public QueryBuilder queryBuilder() {
        return dao.queryBuilder();
    }

    @Override
    public void updateInTx(T entities) {
        dao.updateInTx(entities);
    }

    @Override
    public void updateInTx(T[] entities) {
        dao.updateInTx(entities);
    }

    @Override
    public long count() {
        return dao.count();
    }
}
