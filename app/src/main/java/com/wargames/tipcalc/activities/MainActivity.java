package com.wargames.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wargames.tipcalc.R;
import com.wargames.tipcalc.TipCalcApp;
import com.wargames.tipcalc.fragments.TipHistoryListFragment;
import com.wargames.tipcalc.fragments.TipHistoryListFragmentListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;


    private TipHistoryListFragmentListener fragmentListener;
    //definir constantes
    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // instanciar el fragmento ?
        // como ya viene dentro del layout no se tiene que instanciar, se tiene que obtener
        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager()
                                            .findFragmentById(R.id.fragmentList);
        /*
         El fragmento puede retenera la instancia para que no se este recreando cuando hay un cambio de configuracion
         Eventualmente, ésto va a tener una mayor utilidad cuando tengamos un contenido en el fragmento,
         que es recreado en el "uncreate". Entonces, lo que vamos a ponerle es que guarde la instancia.
         Y entonces, va a permitir que los valores asociados no sean reiniciados cuando
        se cambia la configuración, se rota la pantalla, se muestra el teclado, etcétera.
         */
        fragment.setRetainInstance(true);


        fragmentListener= (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //obtener el identificador del item seleccionado
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit()
    {

        hideKeyboard();

        //Log.e(getLocalClassName(),"Se hizo click en submit");
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty())
        {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            double tip = total *(tipPercentage/100d);
            String strTip = String.format(getString(R.string.global_message_tip),tip);

            // le enviamos el total al fragmento
            fragmentListener.action(strTip);


            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }

    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease()
    {
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease()
    {
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    private void handleTipChange(int tipStepChange) {
        //Obtener el porcentaje de propina actual
        int tipPercentage = getTipPercentage();
        tipPercentage += tipStepChange;
        if ( tipPercentage > 0)
        {
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }

    public int getTipPercentage()
    {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        if ( !strInputTipPercentage.isEmpty())
        {
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }else
        {
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }


    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try
        {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }catch(NullPointerException e)
        {
            Log.e(getLocalClassName(),Log.getStackTraceString(e));
        }
    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);

    }

}
