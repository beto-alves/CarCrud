package br.com.betoalves.carcrud.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;

import br.com.betoalves.carcrud.R;
import br.com.betoalves.carcrud.databinding.ActivityMainBinding;
import br.com.betoalves.carcrud.mvp.MainActivityMvp;
import br.com.betoalves.carcrud.utils.DependencyCacheHelper;

import static br.com.betoalves.carcrud.mvp.MainActivityMvp.IMainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityMvp.IMainActivity {

    IMainActivityPresenter presenter = DependencyCacheHelper.getInstance(IMainActivityPresenter.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.setMainActivity(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setVariable(BR.mainPresenter, presenter.getBindingBean());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter != null) {
            presenter.loadCarList();
        }
    }

    @Override
    public void callNewCarForm() {

        Intent it = new Intent(this,CarFormActivity.class);
        startActivity(it);
    }

    @Override
    public void openEditForm(Long id) {
        Intent it = new Intent(this, CarFormActivity.class);
        it.putExtra(CarFormActivity.CAR_ID, id);
        startActivity(it);
    }
}
