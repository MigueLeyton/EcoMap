package com.example.ecomap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class bdEcoMap extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mi_base_datos.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public bdEcoMap(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase(); // Abrimos la base de datos al instanciar bdEcoMap
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "apellido TEXT, " +
                "correo TEXT, " +
                "contrasena TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }

    public void agregarUsuario(String nombre, String apellido, String correo, String contrasena) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("correo", correo);
        values.put("contrasena", contrasena);

        try {
            db.insert("usuarios", null, values);
        } catch (Exception e) {
            Log.e("agregarUsuario", "Error al agregar usuario: " + e.getMessage());
        }
    }

    public boolean verificarUsuario(String nombreCompleto, String contrasena) {
        String[] partes = nombreCompleto.trim().split(" ");
        if (partes.length < 2) return false;

        String nombre = partes[0];
        String apellido = partes[1];
        Cursor cursor = null;
        boolean existe = false;

        try {
            String query = "SELECT * FROM usuarios WHERE nombre = ? AND apellido = ? AND contrasena = ?";
            cursor = db.rawQuery(query, new String[]{nombre, apellido, contrasena});
            existe = cursor.moveToFirst();
        } catch (Exception e) {
            Log.e("verificarUsuario", "Error al verificar usuario: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return existe;
    }

    public boolean eliminarUsuario(String nombreCompleto) {
        String[] partes = nombreCompleto.trim().split(" ");
        if (partes.length < 2) return false;

        String nombre = partes[0];
        String apellido = partes[1];
        String whereClause = "nombre = ? AND apellido = ?";
        String[] whereArgs = new String[]{nombre, apellido};

        int rowsAffected = 0;
        try {
            rowsAffected = db.delete("usuarios", whereClause, whereArgs);
        } catch (Exception e) {
            Log.e("eliminarUsuario", "Error al eliminar usuario: " + e.getMessage());
        }
        return rowsAffected > 0;
    }

    public boolean modificarUsuario(String nombreCompleto, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevaContrasena) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            Log.d("modificarUsuario", "nombreCompleto está vacío o es nulo.");
            return false;
        }

        String[] partes = nombreCompleto.trim().split(" ");
        if (partes.length < 2) {
            Log.d("modificarUsuario", "nombreCompleto no contiene al menos dos palabras.");
            return false;
        }

        String nombre = partes[0];
        String apellido = partes[partes.length - 1]; // Apellido original, para la búsqueda

        // Asegúrate de que la base de datos esté abierta
        if (db == null || !db.isOpen()) {
            db = this.getWritableDatabase();
        }

        ContentValues values = new ContentValues();

        // Agrega solo los nuevos valores que no sean nulos o vacíos
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            values.put("nombre", nuevoNombre); // Agrega el nuevo nombre
        }
        if (nuevoApellido != null && !nuevoApellido.isEmpty()) {
            values.put("apellido", nuevoApellido);
        }
        if (nuevoCorreo != null && !nuevoCorreo.isEmpty()) {
            values.put("correo", nuevoCorreo);
        }
        if (nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
            values.put("contrasena", nuevaContrasena);
        }

        // Si no hay valores para actualizar, devuelve false
        if (values.size() == 0) {
            Log.d("modificarUsuario", "No se proporcionaron datos para modificar.");
            return false;
        }

        // Actualiza en la base de datos
        String whereClause = "nombre = ? AND apellido = ?";
        String[] whereArgs = new String[]{nombre, apellido};

        int rowsAffected = 0;
        try {
            rowsAffected = db.update("usuarios", values, whereClause, whereArgs);
            Log.d("modificarUsuario", "Filas afectadas: " + rowsAffected);
        } catch (Exception e) {
            Log.e("modificarUsuario", "Error al modificar usuario: " + e.getMessage());
        }
        return rowsAffected > 0; // Retorna true si se modificó al menos un registro
    }

    // Llamado opcional si necesitas cerrar la base de datos manualmente
    public void cerrar() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
