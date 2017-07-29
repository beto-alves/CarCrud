package br.com.betoalves.carcrud.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.betoalves.carcrud.R;
import br.com.betoalves.carcrud.databinding.CarRowBinding;
import br.com.betoalves.carcrud.entity.Car;

/**
 * Created by Beto Alves on 7/29/2017.
 */


public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;
    private ICarAdapterListener carAdapterListener;

    public void setCarAdapterListener(ICarAdapterListener carAdapterListener) {
        this.carAdapterListener = carAdapterListener;
    }

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CarRowBinding carRowBinding = DataBindingUtil.inflate(inflater, R.layout.car_row, parent, false);
        CarViewHolder carViewHolder = new CarViewHolder(carRowBinding.getRoot());
        carRowBinding.setCar(carViewHolder);
        return carViewHolder;
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        holder.setCar(carList.get(position));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public interface ICarAdapterListener {
        void onItemEditRequest(Car car);
    }

    public void notifyItemEditRequest(Car car) {
        if (carAdapterListener != null) {
            carAdapterListener.onItemEditRequest(car);
        }
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        public ObservableField<String> name = new ObservableField<>();
        public ObservableField<String> type = new ObservableField<>();
        public ObservableField<String> brand = new ObservableField<>();
        public ObservableDouble price = new ObservableDouble();
        public ObservableDouble weight = new ObservableDouble();
        private Car car;

        public CarViewHolder(View itemView) {
            super(itemView);
        }

        public void setCar(Car car) {
            this.name.set(car.getName());
            this.type.set(car.getType().getType());
            this.brand.set(car.getBrand().getBrand());
            this.price.set(car.getPrice());
            this.weight.set(car.getWeight());

            this.car = car;
        }

        public void onClickLayout() {
            notifyItemEditRequest(this.car);
        }
    }
}
