package josenavarro.cbaapp.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import josenavarro.cbaapp.R;
import josenavarro.cbaapp.modelos.Equipo;

/**
 * Created by José on 20/12/2016.
 */
public class AdaptadorEquipos extends BaseAdapter {
    private Context contexto;
    private ArrayList<Equipo> arrayEquipos;
    private static LayoutInflater inflater = null;

    public AdaptadorEquipos(Context applicationContext, ArrayList<Equipo> arrayEquipos) {
        contexto = applicationContext;
        this.arrayEquipos = arrayEquipos;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayEquipos.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayEquipos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Integer.parseInt(arrayEquipos.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.item_listview_equipos, viewGroup, false);
        //Componentes de cada item de la lista de equipos
        TextView textoNombreEquipo = (TextView) rowView.findViewById(R.id.nombreEquipo);
        ImageView imagenEquipo = (ImageView) rowView.findViewById(R.id.imagenEquipo);

        //Rellenar datos de los componentes
        textoNombreEquipo.setText(arrayEquipos.get(i).getNombre());
        Picasso.with(contexto).load(arrayEquipos.get(i).getImagen()).into(imagenEquipo);

        return rowView;
    }
}

