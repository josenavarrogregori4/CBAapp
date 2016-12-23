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
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import josenavarro.cbaapp.R;
import josenavarro.cbaapp.adaptadores.AdaptadorPartidos;
import josenavarro.cbaapp.modelos.Equipo;
import josenavarro.cbaapp.modelos.Partido;

public class ListaPartidosEquipoFragment extends Fragment {
    private Equipo equipoSeleccionado;

    private View view;
    private ExpandableListView expListView;


    private OnFragmentInteractionListener mListener;

    private static final String NOMBREARGUMENTO = "equipo";

    private FirebaseDatabase baseDatos;
    private DatabaseReference referenciaBaseDatos;
    private ArrayList<Partido> arrayPartidos;

    public ListaPartidosEquipoFragment() {
        // Required empty public constructor
    }

    //Si se le quiere pasar algo al crear el fragment usamos el siguiente constructor
    // TODO: Rename and change types and number of parameters
    public static ListaPartidosEquipoFragment newInstance(Equipo e) {
        ListaPartidosEquipoFragment fragment = new ListaPartidosEquipoFragment();
        Bundle args = new Bundle();

        args.putSerializable(NOMBREARGUMENTO, e);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            equipoSeleccionado = (Equipo) getArguments().getSerializable(NOMBREARGUMENTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista_partidos_equipo, container, false);

        //Se recoge la instancia de la lista expandible
        expListView = (ExpandableListView) view.findViewById(R.id.lista_partidos_equipo);

        //Base de datos
        baseDatos = FirebaseDatabase.getInstance();
        referenciaBaseDatos = baseDatos.getReference("Partidos/"+equipoSeleccionado.getId());

        //Inicialización arrayPartido
        arrayPartidos = new ArrayList<Partido>();

        //Evento para obtención de los datos de partidos
        referenciaBaseDatos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Se limpia el arrayPartidos antes de recoger los datos de la base de datos
                arrayPartidos.clear();
                for(DataSnapshot partidos : dataSnapshot.getChildren()){
                    Partido p = partidos.getValue(Partido.class);
                    p.setId(partidos.getKey());
                    arrayPartidos.add(p);
                }
                expListView.setAdapter(new AdaptadorPartidos(getActivity().getApplicationContext(), arrayPartidos, equipoSeleccionado));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Evento para que cada item de la ListView llame a un nuevo fragment
        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //CONTINUAR
            }
        });

        return view;
    }

    public void goToFragment(Fragment f) {
        FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.frame_layout_equipos, f).addToBackStack(null).commit();
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
