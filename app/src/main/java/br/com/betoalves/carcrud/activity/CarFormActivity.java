package br.com.betoalves.carcrud.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import br.com.betoalves.carcrud.BR;
import br.com.betoalves.carcrud.R;
import br.com.betoalves.carcrud.databinding.ActivityCarFormBinding;
import br.com.betoalves.carcrud.mvp.CarFormMvp;
import br.com.betoalves.carcrud.utils.DependencyCacheHelper;

import static br.com.betoalves.carcrud.mvp.CarFormMvp.*;

public class CarFormActivity extends AppCompatActivity implements ICarFormActivity {

    public final static String CAR_ID = "CAR_ID";

    ICarFormPresenter presenter = DependencyCacheHelper.getInstance(ICarFormPresenter.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.setCarFormActivity(this);
        ActivityCarFormBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_car_form);
        binding.setVariable(BR.viewCar, presenter.getBindingBean());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it = getIntent();
        if (it.hasExtra(CAR_ID)) {
            presenter.setCar(it.getLongExtra(CAR_ID,0));
        } else {
            presenter.setCar(null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()) {
            DependencyCacheHelper.disposeInstance(ICarFormPresenter.class);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void requestNewBrand() {

        final EditText editText = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastrar Marca");
        builder.setView(editText);
        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText() != null && editText.getText().toString().length() > 0) {
                    presenter.saveNewBrand(editText.getText().toString());
                }
            }
        });
        builder.show();

    }

    @Override
    public void requestNewType() {

        final EditText editText = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastrar Tipo");
        builder.setView(editText);
        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (editText.getText() != null && editText.getText().toString().length() > 0) {
                    presenter.saveNewType(editText.getText().toString());
                }
            }
        });
        builder.show();
    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
