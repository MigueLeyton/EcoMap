package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginEcoMap extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_eco_map);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar los campos de texto
        editTextNombre = findViewById(R.id.idNombre);
        editTextPassword = findViewById(R.id.idPassword);

        // Configurar el botón para registrar
        Button btnRegistrar = findViewById(R.id.idRegistrarRegistro);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irARegistroEcoMap();
            }
        });

        // Configurar el botón para salir
        Button btnSalir = findViewById(R.id.idSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarAplicacion();
            }
        });

        // Configurar el botón para iniciar sesión
        Button btnInicio = findViewById(R.id.idInicio);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
    }

    // Método para redirigir a la actividad registroEcoMap
    private void irARegistroEcoMap() {
        Intent intent = new Intent(loginEcoMap.this, registroEcoMap.class);
        startActivity(intent);
    }

    // Método para cerrar la aplicación
    private void cerrarAplicacion() {
        finish();
    }

    // Método para iniciar sesión
    private void iniciarSesion() {
        String nombre = editTextNombre.getText().toString();
        String password = editTextPassword.getText().toString();

        // Aquí puedes implementar la lógica de verificación de credenciales
        if (nombre.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
        } else {
            // Simulamos un inicio de sesión exitoso
            if (nombre.equals("miguel") && password.equals("1234")) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                // Redirigir a la actividad menuEcoMap
                Intent intent = new Intent(loginEcoMap.this, menuEcoMap.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}