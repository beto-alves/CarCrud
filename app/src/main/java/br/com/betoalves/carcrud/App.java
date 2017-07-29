package br.com.betoalves.carcrud;

import android.app.Application;

import br.com.betoalves.carcrud.entity.DaoMaster;
import br.com.betoalves.carcrud.entity.DaoSession;

/**
 * Created by Roberto on 7/22/2017.
 */

public class App extends Application {

    private static final String DB_NAME = "car_crud.db";

    private static App app;
    private static DaoSession daoSession;

    public static App getApp() {
        return app;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this,DB_NAME);
        daoSession = new DaoMaster(openHelper.getWritableDb()).newSession();
    }
}
