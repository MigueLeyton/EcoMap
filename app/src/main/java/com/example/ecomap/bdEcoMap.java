package com.example.ecomap;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class bdEcoMap extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mi_base_datos.db";
    private static final int DATABASE_VERSION = 1;

    public bdEcoMap(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, correo TEXT, contrasena TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }

    public void agregarUsuario(String nombre, String apellido, String correo, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("correo", correo);
        values.put("contrasena", contrasena);
        db.insert("usuarios", null, values);
        db.close();
    }

    public boolean verificarUsuario(String nombreCompleto, String contrasena) {
        String[] partes = nombreCompleto.split(" ");
        if (partes.length < 2) return false; // Asegúrate de que haya al menos nombre y apellido

        String nombre = partes[0];
        String apellido = partes[1];

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM usuarios WHERE nombre = ? AND apellido = ? AND contrasena = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre, apellido, contrasena});
        boolean existe = (cursor.getCount() > 0);

        cursor.close();
        db.close();
        return existe;
    }

}
