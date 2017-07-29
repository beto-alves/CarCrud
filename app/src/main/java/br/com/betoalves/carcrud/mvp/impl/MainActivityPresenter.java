package br.com.betoalves.carcrud.mvp.impl;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.lang.ref.WeakReference;

import br.com.betoalves.carcrud.adapter.CarAdapter;
import br.com.betoalves.carcrud.entity.Car;
import br.com.betoalves.carcrud.mvp.MainActivityMvp;
import br.com.betoalves.carcrud.repository.interfaces.ICarRepository;
import br.com.betoalves.carcrud.utils.DependencyCacheHelper;
import br.com.betoalves.carcrud.utils.ExtendedBaseObservable;

/**
 * Created by Roberto Alves on 7/26/2017.
 */

public class MainActivityPresenter extends ExtendedBaseObservable implements MainActivityMvp.IMainActivityPresenter, CarAdapter.ICarAdapterListener {

    private WeakReference<MainActivityMvp.IMainActivity> mainActivityWeakReference;

    private ICarRepository carRepository = DependencyCacheHelper.getInstance(ICarRepository.class);

    private CarAdapter carAdapter;

    @Override
    public void setMainActivity(MainActivityMvp.IMainActivity mainActivity) {
        mainActivityWeakReference = new WeakReference<MainActivityMvp.IMainActivity>(mainActivity);
    }

    @Override
    public void callNewCarForm() {
        if(this.mainActivityWeakReference.get() != null) {
            this.mainActivityWeakReference.get().callNewCarForm();
        }
    }

    @Override
    public Object getBindingBean() {
        return this;
    }

    @Override
    public void loadCarList() {
        carAdapter = new CarAdapter(carRepository.loadAll());
        carAdapter.setCarAdapterListener(this);
        notifyPropertyChanged(BR.carAdapter);
    }

    @Bindable
    public CarAdapter getCarAdapter() {
        return carAdapter;
    }

    @Override
    public void onItemEditRequest(Car car) {
        if (this.mainActivityWeakReference.get() != null) {
            this.mainActivityWeakReference.get().openEditForm(car.getId());
        }
    }
}
