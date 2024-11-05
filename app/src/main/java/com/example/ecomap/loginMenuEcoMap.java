package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginMenuEcoMap extends AppCompatActivity {

    private LinearLayout descripcionLayout;
    private bdEcoMap baseDeDatos; // Instancia de la base de datos
    private String nombreUsuarioActual; // Variable para almacenar el nombre del usuario que ha iniciado sesión

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_menu_eco_map);

        // Inicializa la base de datos
        baseDeDatos = new bdEcoMap(this);

        // Suponiendo que ya tienes el nombre del usuario actual, puedes establecerlo aquí
        // Por ejemplo, podrías obtenerlo de la actividad de inicio de sesión
        nombreUsuarioActual = getIntent().getStringExtra("NOMBRE_USUARIO"); // Asegúrate de enviar esto al iniciar la actividad

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarLoginMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bienvenido");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia al LinearLayout que contiene la descripción
        descripcionLayout = findViewById(R.id.descripcionLayout);
        TextView titulo = findViewById(R.id.titulo);

        // Establece el listener para mostrar/ocultar la descripción
        titulo.setOnClickListener(v -> {
            if (descripcionLayout.getVisibility() == View.VISIBLE) {
                descripcionLayout.setVisibility(View.GONE);
            } else {
                descripcionLayout.setVisibility(View.VISIBLE);
            }
        });

        // Configuración de los botones
        Button btnIrTiposMenu = findViewById(R.id.btnIrTiposMenu);
        btnIrTiposMenu.setOnClickListener(v -> irAMenuEcoMap());

        Button btnModificarUsuario = findViewById(R.id.btnModificarUsuario);
        btnModificarUsuario.setOnClickListener(v -> irModificarUsuario());

        Button btnEliminarUsuario = findViewById(R.id.btnEliminarUsuario);
        btnEliminarUsuario.setOnClickListener(v -> eliminarUsuario());

        Button btnSalirUsuarioMenu = findViewById(R.id.btnSalirUsuarioMenu);
        btnSalirUsuarioMenu.setOnClickListener(v -> irALoginEcoMap());
    }

    // Métodos para redirigir a las actividades correspondientes
    private void irAMenuEcoMap() {
        Intent intent = new Intent(loginMenuEcoMap.this, menuEcoMap.class);
        startActivity(intent);
    }

    private void irModificarUsuario() {
        Intent intent = new Intent(loginMenuEcoMap.this, modificarUsuarioEcoMap.class);
        intent.putExtra("nombreCompleto", nombreUsuarioActual); // Asegúrate de enviar el nombre completo
        startActivity(intent);
    }

    private void irALoginEcoMap() {
        Intent intent = new Intent(loginMenuEcoMap.this, loginEcoMap.class);
        startActivity(intent);
    }

    // Método para eliminar el usuario
    private void eliminarUsuario() {
        if (nombreUsuarioActual != null && !nombreUsuarioActual.isEmpty()) {
            if (baseDeDatos.eliminarUsuario(nombreUsuarioActual)) {
                Toast.makeText(this, "Usuario eliminado exitosamente.", Toast.LENGTH_SHORT).show();
                irALoginEcoMap(); // Redirigir a la pantalla de inicio de sesión después de eliminar
            } else {
                Toast.makeText(this, "Error al eliminar el usuario. Verifica que exista.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No se pudo obtener el nombre del usuario.", Toast.LENGTH_SHORT).show();
        }
    }
}
