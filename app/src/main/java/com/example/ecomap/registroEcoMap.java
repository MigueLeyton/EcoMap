package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class registroEcoMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarRegistro);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Registro de usuario");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        // Configurar campos de entrada
        EditText editTextNombre = findViewById(R.id.idNombreRegistro);
        EditText editTextApellido = findViewById(R.id.idApellidoRegistro);
        EditText editTextCorreo = findViewById(R.id.idCorreoRegistro);
        EditText editTextContrasena = findViewById(R.id.editTextTextPassword);
        EditText editTextContrasenaRepetir = findViewById(R.id.editTextTextPassword2);

        // Configurar el botón para registrar
        Button buttonRegistrar = findViewById(R.id.idRegistrarRegistro);
        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                String apellido = editTextApellido.getText().toString();
                String correo = editTextCorreo.getText().toString();
                String contrasena = editTextContrasena.getText().toString();
                String contrasenaRepetir = editTextContrasenaRepetir.getText().toString();

                // Validar campos
                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || contrasenaRepetir.isEmpty()) {
                    Toast.makeText(registroEcoMap.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else if (!contrasena.equals(contrasenaRepetir)) {
                    Toast.makeText(registroEcoMap.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                } else if (!validarContrasena(contrasena)) {
                    // Si la contraseña no es válida, no se prosigue
                    return;
                } else {
                    // Guardar en la base de datos
                    bdEcoMap dbHelper = new bdEcoMap(registroEcoMap.this);
                    dbHelper.agregarUsuario(nombre, apellido, correo, contrasena);

                    // Notificación de éxito
                    Toast.makeText(registroEcoMap.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();

                    // Volver a la pantalla de login
                    volverALoginEcoMap();
                }
            }
        });

        // Configurar botón para cancelar
        Button btnCancelar = findViewById(R.id.idCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverALoginEcoMap();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                volverALoginEcoMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Método para volver a loginEcoMap
    private void volverALoginEcoMap() {
        Intent intent = new Intent(registroEcoMap.this, loginEcoMap.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }

    // Método para validar la contraseña
    private boolean validarContrasena(String contrasena) {
        // Validar longitud mínima
        if (contrasena.length() < 8) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validar que contenga al menos una letra y un número
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
