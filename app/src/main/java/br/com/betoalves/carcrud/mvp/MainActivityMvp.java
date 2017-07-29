package br.com.betoalves.carcrud.mvp;

/**
 * Created by Beto Alves on 7/26/2017.
 */

public interface MainActivityMvp {

    interface IMainActivity {

        void callNewCarForm();

        void openEditForm(Long id);
    }

    interface IMainActivityPresenter {

        void setMainActivity(IMainActivity mainActivity);
        void callNewCarForm();

        Object getBindingBean();

        void loadCarList();
    }

}
