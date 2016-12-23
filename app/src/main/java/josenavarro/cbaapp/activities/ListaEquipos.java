package josenavarro.cbaapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import josenavarro.cbaapp.R;
import josenavarro.cbaapp.fragments.ListaEquiposFragment;
import josenavarro.cbaapp.fragments.ListaPartidosEquipoFragment;

public class ListaEquipos extends AppCompatActivity implements ListaEquiposFragment.OnFragmentInteractionListener, ListaPartidosEquipoFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        /*
         Activar el boton "UP"
         getActionBar() devuelve NullPointerException, según stackoverflow es porque el tema no lo soporta
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Se añade el fragment que lista los equipos

        Fragment listaEquiposFragment = new ListaEquiposFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_layout_equipos, listaEquiposFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
