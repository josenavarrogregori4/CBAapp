package josenavarro.cbaapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import josenavarro.cbaapp.R;
import josenavarro.cbaapp.adaptadores.AdaptadorEquipos;
import josenavarro.cbaapp.modelos.Equipo;

public class ListaEquiposFragment extends Fragment {
    private View view;

    private DatabaseReference referenciaBaseDatos;
    private FirebaseDatabase baseDatos;
    private ArrayList<Equipo> arrayEquipos;

    private OnFragmentInteractionListener mListener;

    public ListaEquiposFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista_equipos, container, false);
        //Se obtiene la instancia de la lista
        final ListView listViewEquipos = (ListView) view.findViewById(R.id.list_view_equipos);

        //Base de datos
        baseDatos = FirebaseDatabase.getInstance();
        referenciaBaseDatos = baseDatos.getReference("Equipos");

        //Inicialización arrayEquipos
        arrayEquipos = new ArrayList<Equipo>();

        //Evento para obtención de los datos de equipos
        referenciaBaseDatos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Se limpia el arrayEquipos antes de recoger los datos de la base de datos
                arrayEquipos.clear();
                for(DataSnapshot equipos : dataSnapshot.getChildren()){
                    Equipo e = equipos.getValue(Equipo.class);
                    e.setId(equipos.getKey());
                    arrayEquipos.add(e);
                }
                listViewEquipos.setAdapter(new AdaptadorEquipos(getActivity().getApplicationContext(), arrayEquipos));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Evento para que cada item de la ListView llame a un nuevo fragment
        listViewEquipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*
                Se crea un nuevo fragment para listar los resultados
                Tambien se crea un Bundle para pasarle al fragment el equipo seleccionado
                 */
                Equipo e = arrayEquipos.get(i);
                Fragment fragmentListaPartidos = ListaPartidosEquipoFragment.newInstance(e);

                goToFragment(fragmentListaPartidos);
            }
        });

        return view;
    }

    public void goToFragment(Fragment f) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout_equipos, f).addToBackStack(null).commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
