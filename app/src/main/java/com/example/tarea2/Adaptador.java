package com.example.tarea2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    ArrayList<Agenda> lista;
    daoAgenda dao;
    Agenda a;
    Activity act;
    int id = 0;

    public Adaptador(Activity act, ArrayList<Agenda> lista, daoAgenda dao){
        this.lista = lista;
        this.act = act;
        this.dao = dao;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @Override
    public int getCount(){
        return lista.size();
    }
    @Override
    public Object getItem(int i){
        a=lista.get(i);
            return null;
    }
    @Override
    public long getItemId(int i){
        a=lista.get(i);
            return a.getId();
    }
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
            LayoutInflater li = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }
        a=lista.get(posicion);
        TextView nombre=v.findViewById(R.id.nombre);
        TextView apellido=v.findViewById(R.id.apellido);
        TextView email=v.findViewById(R.id.email);
        TextView telefono=v.findViewById(R.id.telefono);
        TextView ciudad=v.findViewById(R.id.ciudad);
        TextView fecha = v.findViewById(R.id.fecha);
        Button editar= v.findViewById(R.id.btn_editar);
        Button eliminar= v.findViewById(R.id.btn_eliminar);

        nombre.setText(a.getNombre());
        apellido.setText(a.getApellido());
        email.setText(a.getEmail());
        telefono.setText(a.getTelefono());
        ciudad.setText(a.getCiudad());
        fecha.setText(a.getFecha());
        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo de editar
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(act);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText apellido = dialog.findViewById(R.id.et_apellido);
                final EditText email = dialog.findViewById(R.id.et_email);
                final EditText telefono = dialog.findViewById(R.id.et_telefono);
                final EditText ciudad = dialog.findViewById(R.id.et_ciudad);
                final EditText fecha = dialog.findViewById(R.id.et_fecha);

                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                a=lista.get(pos);
                setId(a.getId());
                nombre.setText(a.getNombre());
                apellido.setText(a.getApellido());
                email.setText(a.getEmail());
                telefono.setText(a.getTelefono());
                ciudad.setText(a.getCiudad());
                fecha.setText(a.getFecha());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            a = new Agenda(getId(), nombre.getText().toString(),
                                    apellido.getText().toString(),
                                    email.getText().toString(),
                                    telefono.getText().toString(),
                                    ciudad.getText().toString(),
                                    fecha.getText().toString());
                            dao.editar(a);
                            lista=dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(act, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                a = lista.get(posicion);
                setId(a.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(act);
                del.setMessage("¿Estás seguro que deseas eliminar?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista=dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
