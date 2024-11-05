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
    private bdEcoMap baseDeDatos; // Declarar la instancia de bdEcoMap

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

        // Inicializar la base de datos
        baseDeDatos = new bdEcoMap(this); // 'this' es el contexto correcto

        // Campos de texto
        editTextNombre = findViewById(R.id.idNombre);
        editTextPassword = findViewById(R.id.idPassword);

        // Configuración del btn registrar
        Button btnRegistrar = findViewById(R.id.idRegistrarRegistro);
        btnRegistrar.setOnClickListener(v -> irARegistroEcoMap());

        // Configuración del btn salir
        Button btnSalir = findViewById(R.id.idSalir);
        btnSalir.setOnClickListener(v -> cerrarAplicacion());

        // Configuración del btn iniciar
        Button btnInicio = findViewById(R.id.idInicio);
        btnInicio.setOnClickListener(v -> iniciarSesion());
    }

    // Método para redirigir al activity registroEcoMap
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
        String nombreCompleto = editTextNombre.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Lógica del inicio de sesión
        if (nombreCompleto.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
        } else {
            // Verificar usuario en la base de datos
            if (baseDeDatos.verificarUsuario(nombreCompleto, password)) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                // Redirigir al activity loginMenuEcoMap
                Intent intent = new Intent(loginEcoMap.this, loginMenuEcoMap.class);
                intent.putExtra("NOMBRE_USUARIO", nombreCompleto); // Pasar el nombre completo
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
