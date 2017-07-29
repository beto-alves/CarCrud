package br.com.betoalves.carcrud.repository.implementation;

import org.greenrobot.greendao.AbstractDao;

import br.com.betoalves.carcrud.App;
import br.com.betoalves.carcrud.entity.Type;
import br.com.betoalves.carcrud.repository.interfaces.ITypeRepository;

/**
 * Created by Roberto on 7/22/2017.
 */

public class TypeRepository extends AbstractRepository<Type,Long> implements ITypeRepository {

    public TypeRepository() {
        super(App.getDaoSession().getTypeDao());
    }
}
