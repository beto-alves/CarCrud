package br.com.betoalves.carcrud.repository.implementation;

import br.com.betoalves.carcrud.App;
import br.com.betoalves.carcrud.entity.Brand;
import br.com.betoalves.carcrud.repository.interfaces.IBrandRepository;

/**
 * Created by Roberto on 7/22/2017.
 */

public class BrandRepository extends AbstractRepository<Brand,Long> implements IBrandRepository {

    public BrandRepository() {
        super(App.getDaoSession().getBrandDao());
    }
}
