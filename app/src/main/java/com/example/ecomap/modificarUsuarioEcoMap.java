package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Importar Log
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class modificarUsuarioEcoMap extends AppCompatActivity {

    private EditText editTextNuevoNombre; // Nuevo campo para el nombre
    private EditText editTextNuevoApellido;
    private EditText editTextNuevoCorreo;
    private EditText editTextNuevaContrasena;
    private EditText editTextNuevaContrasenaRepetir;
    private String nombreCompleto; // Nombre completo del usuario a modificar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarModificar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Modificar Usuario");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        // Inicializar los campos de entrada
        editTextNuevoNombre = findViewById(R.id.idNuevoNombreModificar); // Inicializa el nuevo EditText
        editTextNuevoApellido = findViewById(R.id.idNuevoApellidoModificar);
        editTextNuevoCorreo = findViewById(R.id.idNuevoCorreoModificar);
        editTextNuevaContrasena = findViewById(R.id.editTextNuevaContrasena);
        editTextNuevaContrasenaRepetir = findViewById(R.id.editTextNuevaContrasenaRepetir);

        // Obtén el nombre completo del usuario a modificar (puede venir desde otra actividad)
        nombreCompleto = getIntent().getStringExtra("nombreCompleto");
        Log.d("ModificarUsuario", "Nombre completo recibido: " + nombreCompleto); // Registro del nombre completo

        // Configurar el botón para guardar cambios
        Button buttonGuardarCambios = findViewById(R.id.idGuardarCambios);
        buttonGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarUsuario();
            }
        });

        // Configurar botón para cancelar
        Button btnCancelar = findViewById(R.id.idCancelarModificar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAInicio();
            }
        });
    }

    private void modificarUsuario() {
        String nuevoNombre = editTextNuevoNombre.getText().toString(); // Obtener nuevo nombre
        String nuevoApellido = editTextNuevoApellido.getText().toString();
        String nuevoCorreo = editTextNuevoCorreo.getText().toString();
        String nuevaContrasena = editTextNuevaContrasena.getText().toString();
        String nuevaContrasenaRepetir = editTextNuevaContrasenaRepetir.getText().toString();

        // Validar campos
        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoCorreo.isEmpty() || nuevaContrasena.isEmpty() || nuevaContrasenaRepetir.isEmpty()) {
            Toast.makeText(modificarUsuarioEcoMap.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!nuevaContrasena.equals(nuevaContrasenaRepetir)) {
            Toast.makeText(modificarUsuarioEcoMap.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!validarContrasena(nuevaContrasena)) {
            return;
        }

        // Modificar en la base de datos
        bdEcoMap dbHelper = new bdEcoMap(modificarUsuarioEcoMap.this);
        boolean exito = dbHelper.modificarUsuario(nombreCompleto, nuevoNombre, nuevoApellido, nuevoCorreo, nuevaContrasena);

        if (exito) {
            Toast.makeText(modificarUsuarioEcoMap.this, "Usuario modificado con éxito", Toast.LENGTH_SHORT).show();
            volverAInicio();
        } else {
            Toast.makeText(modificarUsuarioEcoMap.this, "Error al modificar el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para volver a la pantalla principal o actividad anterior
    private void volverAInicio() {
        Intent intent = new Intent(modificarUsuarioEcoMap.this, loginMenuEcoMap.class);
        startActivity(intent);
        finish();
    }

    // Método para validar la contraseña
    private boolean validarContrasena(String contrasena) {
        if (contrasena.length() < 8) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean tieneLetra = false;
        boolean tieneNumero = false;
        boolean tieneCaracterEspecial = false;

        for (char c : contrasena.toCharArray()) {
            if (Character.isLetter(c)) {
                tieneLetra = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            } else if (!Character.isWhitespace(c)) {
                tieneCaracterEspecial = true;
            }
        }

        if (!tieneLetra) {
            Toast.makeText(this, "La contraseña debe contener al menos una letra.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!tieneNumero) {
            Toast.makeText(this, "La contraseña debe contener al menos un número.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!tieneCaracterEspecial) {
            Toast.makeText(this, "La contraseña debe contener al menos un carácter especial.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
