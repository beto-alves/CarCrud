package br.com.betoalves.carcrud.repository.interfaces;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collection;
import java.util.List;

/**
 * Created by Roberto on 18/07/2017.
 */

public interface IAbstractRepository<T, K> {

    T load(K key);

    T loadByRowId(long rowId);

    List<T> loadAll();

    boolean detach(T entity);

    void detachAll();

    void insertInTx(Iterable<T> entities);

    void insertInTx(T[] entities);

    void insertInTx(Iterable<T> entities, boolean setPrimaryKey);

    void insertOrReplaceInTx(Iterable<T> entities, boolean setPrimaryKey);

    void insertOrReplaceInTx(Iterable<T> entities);

    void insertOrReplaceInTx(T[] entities);

    long insert(T entity);

    long insertWithoutSettingPk(T entity);

    long insertOrReplace(T entity);

    void save(T entity);

    void saveInTx(T[] entities);

    void saveInTx(Iterable<T> entities);

    List queryRaw(String where, String... selectionArg);

    Query queryRawCreate(String where, Object... selectionArg);

    Query queryRawCreateListArgs(String where, Collection<Object> selectionArg);

    void deleteAll();

    void delete(T entity);

    void deleteByKey(K key);

    void deleteInTx(Iterable<T> entities);

    void deleteInTx(T[] entities);

    void deleteByKeyInTx(Iterable<K> keys);

    void deleteByKeyInTx(K[] keys);

    void refresh(T entity);

    void update(T entity);

    QueryBuilder queryBuilder();

    void updateInTx(T entities);

    void updateInTx(T[] entities);

    long count();
}
