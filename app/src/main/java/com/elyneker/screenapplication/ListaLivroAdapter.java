package com.elyneker.screenapplication;

import android.annotation.SuppressLint;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.ArrayList;


class ListaLivrosAdapter extends ArrayAdapter<Livro> {
    private Context context;
    private int auxResource;

    public ListaLivrosAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Livro> objects) {
        super(context, resource, objects);
        this.context = context;
        auxResource = resource;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        String materia = getItem(position).getMateria();
        String titulo = getItem(position).getTitulo();

        Livro livro = new Livro(id, materia, titulo);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(auxResource, parent, false);

        TextView textCodigo = (TextView) convertView.findViewById(R.id.textCodigo);
        TextView textMateria = (TextView) convertView.findViewById(R.id.textMateria);
        TextView textTitulo = (TextView) convertView.findViewById(R.id.textTitulo);

        textCodigo.setText("Id: " + Integer.toString(id));
        textMateria.setText(materia);
        textTitulo.setText(titulo);

        return convertView;
    }
}