package br.com.betoalves.carcrud.mvp;

/**
 * Created by Beto Alves on 7/26/2017.
 */

public interface CarFormMvp {

    interface ICarFormActivity {
        void requestNewBrand();
        void requestNewType();

        void goBack();
        void showToast(String msg);
    }

    interface ICarFormPresenter {

        void setCarFormActivity(ICarFormActivity carFormActivity);

        Object getBindingBean();

        void saveNewBrand(String name);
        void saveNewType(String name);

        void setCar(Long id);

    }

}