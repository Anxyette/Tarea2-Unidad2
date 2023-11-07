package com.example.tarea2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoAgenda {
    SQLiteDatabase bd;
    ArrayList<Agenda>lista = new ArrayList<Agenda>();
    Agenda a;
    Context ct;
    String nombreBD = "BDAgendas";

    String tabla = "create table if not exists agenda(id integer primary key autoincrement, nombre text, apellido text, email text, telefono text, ciudad text, fecha text)";

    public daoAgenda(Context c){
        this.ct=c;

        bd=c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);
    }
    public boolean insertar(Agenda a){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", a.getNombre());
        contenedor.put("apellido", a.getApellido());
        contenedor.put("email", a.getEmail());
        contenedor.put("telefono", a.getTelefono());
        contenedor.put("ciudad", a.getCiudad());
        contenedor.put("fecha", a.getFecha());
        return (bd.insert("agenda", null, contenedor))>0;
    }
    public boolean eliminar(int id){
        return (bd.delete("agenda","id="+id,null ))>0;
    }
    public boolean editar(Agenda a){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", a.getNombre());
        contenedor.put("apellido", a.getApellido());
        contenedor.put("email", a.getEmail());
        contenedor.put("telefono", a.getTelefono());
        contenedor.put("ciudad", a.getCiudad());
        contenedor.put("fecha", a.getFecha());
        return (bd.update("agenda", contenedor, "id="+a.getId(), null))>0;
    }
    public ArrayList<Agenda>verTodo() {
        lista.clear();
        Cursor cursor = bd.rawQuery("select * from agenda",null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Agenda(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6)));
            }while (cursor.moveToNext());
        }
        return lista;
    }
    public Agenda verUno(int posicion){
        Cursor cursor = bd.rawQuery("select * from agenda",null);
        cursor.moveToPosition(posicion);
        a = new Agenda(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6));
        return a;
    }
}
