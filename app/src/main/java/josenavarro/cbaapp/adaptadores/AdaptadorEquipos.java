package josenavarro.cbaapp.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.item_listview_equipos, viewGroup, false);
        //Componentes de cada item de la lista de equipos
        TextView textoNombreEquipo = (TextView) rowView.findViewById(R.id.nombreEquipo);
        final ImageView imagenEquipo = (ImageView) rowView.findViewById(R.id.imagenEquipo);
        final ImageView imagenEquipoDesenfocada = (ImageView) rowView.findViewById(R.id.imagenEquipoDesenfocada);

        //Rellenar datos de los componentes
        textoNombreEquipo.setText(arrayEquipos.get(i).getNombre());

        //API que de una URL saca la imagen, siempre busca la imagen
        //Picasso.with(contexto).load(arrayEquipos.get(i).getImagen()).networkPolicy(NetworkPolicy.OFFLINE).into(imagenEquipo);
        //Picasso.with(contexto).load(arrayEquipos.get(i).getImagen()).transform(new BlurTransformation(contexto, 30, 1)).networkPolicy(NetworkPolicy.OFFLINE).into(imagenEquipoDesenfocada);

        Glide.with(contexto).load(arrayEquipos.get(i).getImagen()).skipMemoryCache(false).into(imagenEquipo);
        Glide.with(contexto).load(arrayEquipos.get(i).getImagen()).skipMemoryCache(false).bitmapTransform(new jp.wasabeef.glide.transformations.BlurTransformation(contexto, 30, 1)).into(imagenEquipoDesenfocada);

        return rowView;
    }
}

