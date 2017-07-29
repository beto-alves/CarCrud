package br.com.betoalves.carcrud.mvp.impl;

import android.databinding.Bindable;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.betoalves.carcrud.BR;
import br.com.betoalves.carcrud.entity.Brand;
import br.com.betoalves.carcrud.entity.Car;
import br.com.betoalves.carcrud.entity.Type;
import br.com.betoalves.carcrud.mvp.CarFormMvp;
import br.com.betoalves.carcrud.repository.interfaces.IBrandRepository;
import br.com.betoalves.carcrud.repository.interfaces.ICarRepository;
import br.com.betoalves.carcrud.repository.interfaces.ITypeRepository;
import br.com.betoalves.carcrud.utils.DependencyCacheHelper;
import br.com.betoalves.carcrud.utils.ExtendedBaseObservable;

/**
 * Created by Beto Alves on 7/26/2017.
 */

public class CarFormPresenter extends ExtendedBaseObservable implements CarFormMvp.ICarFormPresenter {

    private WeakReference<CarFormMvp.ICarFormActivity> carFormActivityWeakReference;

    private List<Brand> brandList;
    private List<Type> typeList;

    private Integer selectedBrandPosition;
    private Integer selectedTypePosition;

    private String name;
    private double price;
    private double weight;
    private Long brandId;
    private Long typeId;
    private Long idCar;

    private IBrandRepository brandRepository = DependencyCacheHelper.getInstance(IBrandRepository.class);
    private ITypeRepository typeRepository = DependencyCacheHelper.getInstance(ITypeRepository.class);
    private ICarRepository carRepository = DependencyCacheHelper.getInstance(ICarRepository.class);

    private boolean init;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        notifyPropertyChanged(BR.weight);
    }

    @Bindable
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
        notifyPropertyChanged(BR.brandId);
    }

    @Bindable
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
        notifyPropertyChanged(BR.typeId);
    }

    @Override
    public void setCarFormActivity(CarFormMvp.ICarFormActivity carFormActivity) {
        carFormActivityWeakReference = new WeakReference<CarFormMvp.ICarFormActivity>(carFormActivity);
    }

    @Override
    public void setCar(Long idCar) {

        if (!init) {
            this.idCar = idCar;

            if (idCar != null && idCar > 0) {
                Car car = carRepository.load(idCar);
                if (car != null) {
                    setName(car.getName());
                    setWeight(car.getWeight());
                    setPrice(car.getPrice());
                    brandId = car.getBrandId();
                    typeId = car.getTypeId();
                }
            } else {
                setName(null);
                setWeight(0);
                setPrice(0);
                brandId = null;
                typeId = null;
            }
            refreshBrandList();
            refreshTypeList();
            init = true;
        }
    }

    public void saveNewCar() {

        new AsyncTask<Object, Void, Long>() {
            @Override
            protected Long doInBackground(Object... params) {
                Car car = new Car(idCar,name,price,weight,brandId,typeId);
                carRepository.save(car);
                return null;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);

                if (carFormActivityWeakReference.get() != null) {
                    carFormActivityWeakReference.get().goBack();
                }
            }
        }.execute();
    }

    private void refreshBrandList() {
        refreshBrandList(brandId);
    }

    private void refreshBrandList(Long id) {
        setBrandList(brandRepository.loadAll());
        int index = 0;
        if (id != null && id > 0) {
            for (Brand brand : brandList) {
                if (brand.getId().equals(id)) {
                    index = brandList.indexOf(brand);
                    break;
                }
            }
        }
        setSelectedBrandPosition(index);
    }

    private void refreshTypeList() {
        refreshTypeList(typeId);
    }

    private void refreshTypeList(Long id) {
        setTypeList(typeRepository.loadAll());
        int index = 0;
        if (id != null && id > 0) {
            for (Type type : typeList) {
                if (type.getId().equals(id)) {
                    index = typeList.indexOf(type);
                    break;
                }
            }
        }
        setSelectedTypePosition(index);
    }

    @Override
    public Object getBindingBean() {
        return this;
    }

    @Override
    public void saveNewBrand(String name) {

        new AsyncTask<Object, Void, Long>() {
            @Override
            protected Long doInBackground(Object... params) {

                String name = (String)params[0];

                Brand brand = new Brand();
                brand.setBrand(name);

                return brandRepository.insert(brand);
            }

            @Override
            protected void onPostExecute(Long insertedResult) {
                super.onPostExecute(insertedResult);

                if (insertedResult != null && insertedResult > 0) {
                    refreshBrandList(insertedResult);
                    carFormActivityWeakReference.get().showToast("Marca Inserida com Sucesso!");
                }
            }

        }.execute(name);
    }

    @Override
    public void saveNewType(String name) {

        new AsyncTask<Object, Void, Long>() {
            @Override
            protected Long doInBackground(Object... params) {

                String name = (String)params[0];

                Type type = new Type();
                type.setType(name);

                return typeRepository.insert(type);
            }

            @Override
            protected void onPostExecute(Long insertedResult) {
                super.onPostExecute(insertedResult);

                if (insertedResult != null && insertedResult > 0) {
                    refreshTypeList(insertedResult);
                    carFormActivityWeakReference.get().showToast("Tipo Inserido com Sucesso!");
                }
            }
        }.execute(name);
    }

    public void requestNewBrand() {
        if (carFormActivityWeakReference.get() != null) {
            carFormActivityWeakReference.get().requestNewBrand();
        }
    }

    public void requestNewType() {
        if (carFormActivityWeakReference.get() != null) {
            carFormActivityWeakReference.get().requestNewType();
        }
    }

    @Bindable
    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
        notifyPropertyChanged(BR.brandList);
    }

    @Bindable
    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
        notifyPropertyChanged(BR.typeList);
    }

    @Bindable
    public Integer getSelectedBrandPosition() {
        return selectedBrandPosition;
    }

    public void setSelectedBrandPosition(Integer selectedBrandPosition) {
        this.selectedBrandPosition = selectedBrandPosition;

        if (selectedBrandPosition != null && brandList.size() > 0) {
            brandId = brandList.get(selectedBrandPosition).getId();
        }
        notifyPropertyChanged(BR.selectedBrandPosition);
    }

    @Bindable
    public Integer getSelectedTypePosition() {
        return selectedTypePosition;
    }

    public void setSelectedTypePosition(Integer selectedTypePosition) {
        this.selectedTypePosition = selectedTypePosition;

        if (selectedTypePosition != null && typeList.size() > 0) {
            typeId = typeList.get(selectedTypePosition).getId();
        }
        notifyPropertyChanged(BR.selectedTypePosition);
    }
}
