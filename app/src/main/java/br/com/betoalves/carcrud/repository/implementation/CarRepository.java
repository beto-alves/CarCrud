package br.com.betoalves.carcrud.repository.implementation;

import br.com.betoalves.carcrud.App;
import br.com.betoalves.carcrud.entity.Car;
import br.com.betoalves.carcrud.repository.interfaces.ICarRepository;

/**
 * Created by Roberto on 7/22/2017.
 */

public class CarRepository extends AbstractRepository<Car,Long> implements ICarRepository {

    public CarRepository() {
        super(App.getDaoSession().getCarDao());
    }
}
