package josenavarro.cbaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import josenavarro.cbaapp.adaptadores.AdaptadorEquipos;
import josenavarro.cbaapp.modelos.Equipo;

public class ListaEquipos extends AppCompatActivity {
    private DatabaseReference referenciaBaseDatos;
    private FirebaseDatabase baseDatos;
    private ArrayList<Equipo> arrayEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ListView listViewEquipos = (ListView) findViewById(R.id.list_view_equipos);

        //Base de datos
        baseDatos = FirebaseDatabase.getInstance();
        referenciaBaseDatos = baseDatos.getReference("Equipos");

        //Inicialización arrayEquipos
        arrayEquipos = new ArrayList<Equipo>();

        //Evento para obtención de los datos de equipos
        referenciaBaseDatos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot equipos : dataSnapshot.getChildren()){
                    Equipo e = equipos.getValue(Equipo.class);
                    e.setId(equipos.getKey());
                    arrayEquipos.add(e);
                }
                listViewEquipos.setAdapter(new AdaptadorEquipos(getApplicationContext(), arrayEquipos));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
