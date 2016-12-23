package josenavarro.cbaapp.adaptadores;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import josenavarro.cbaapp.R;
import josenavarro.cbaapp.modelos.Equipo;
import josenavarro.cbaapp.modelos.Partido;

/**
 * Created by José on 21/12/2016.
 */

public class AdaptadorPartidos extends BaseExpandableListAdapter{
    private Context contexto;
    private ArrayList<Partido> arrayPartidos;
    private Equipo equipoSeleccionado;
    private static LayoutInflater inflater = null;

    public AdaptadorPartidos(Context applicationContext, ArrayList<Partido> arrayPartidos, Equipo equipoSeleccionado){
        contexto = applicationContext;
        this.arrayPartidos = arrayPartidos;
        this.equipoSeleccionado = equipoSeleccionado;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return arrayPartidos.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return arrayPartidos.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return arrayPartidos.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return Integer.parseInt(arrayPartidos.get(i).getId());
    }

    @Override
    public long getChildId(int i, int i1) {
        return Integer.parseInt(arrayPartidos.get(i).getId());
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        Partido p = (Partido) getGroup(i);
        String tituloCabecera = p.getFecha();


        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cabecera_lista_partidos_equipos, null);
        }

        TextView textoCabeceraPartidosEquipo = (TextView) view.findViewById(R.id.texto_cabecera_lista_partidos_equipo);
        textoCabeceraPartidosEquipo.setText(tituloCabecera);

        return view;

    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Partido p = (Partido) arrayPartidos.get(i);
        String nombreEquipoLocal = equipoSeleccionado.getNombre();
        int resultadoEquipoLocal = p.getPuntosCBA();
        String nombreEquipoVisitante = p.getNombreRival();
        int resultadoEquipoVisitante = p.getPuntosRival();

        if(p.getJugadoEnCasa() == 0){
            String tempNombre = nombreEquipoLocal;
            nombreEquipoLocal = nombreEquipoVisitante;
            nombreEquipoVisitante = tempNombre;

            int tempResultado = resultadoEquipoLocal;
            resultadoEquipoLocal = resultadoEquipoVisitante;
            resultadoEquipoVisitante = tempResultado;
        }

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lista_partidos_equipo, null);
        }

        TextView textoNombreEquipoLocal = (TextView) view.findViewById(R.id.nombreEquipoLocal);
        TextView textoNombreEquipoVisitante = (TextView) view.findViewById(R.id.nombreEquipoVisitante);

        TextView textoResultadoEquipoLocal = (TextView) view.findViewById(R.id.resultadoEquipoLocal);
        TextView textoResultadoEquipoVisitante = (TextView) view.findViewById(R.id.resultadoVisitante);

        //Color naranja para cualquier equipo CBA
        if(p.getJugadoEnCasa() == 1){
            if (Build.VERSION.SDK_INT < 23) {
                textoNombreEquipoLocal.setTextAppearance(contexto.getApplicationContext(), R.style.estiloTextoItemPartido_naranja);
            }
            else{
                textoNombreEquipoLocal.setTextAppearance(R.style.estiloTextoItemPartido_naranja);
            }
        }
        else{
            //Comprobar la version del SKD para usar un metodo que esta deprecated ahora
            if (Build.VERSION.SDK_INT < 23) {
                textoNombreEquipoVisitante.setTextAppearance(contexto.getApplicationContext(), R.style.estiloTextoItemPartido_naranja);
            }
            else{
                textoNombreEquipoVisitante.setTextAppearance(R.style.estiloTextoItemPartido_naranja);
            }
        }

        textoNombreEquipoLocal.setText(nombreEquipoLocal);
        textoNombreEquipoVisitante.setText(nombreEquipoVisitante);

        textoResultadoEquipoLocal.setText(String.valueOf(resultadoEquipoLocal));
        textoResultadoEquipoVisitante.setText(String.valueOf(resultadoEquipoVisitante));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
