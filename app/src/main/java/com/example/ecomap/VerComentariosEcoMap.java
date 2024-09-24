package com.example.ecomap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerComentariosEcoMap extends AppCompatActivity {

    private TextView textViewComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_comentarios_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarVerComentario);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita el botón de retroceso
        getSupportActionBar().setTitle("Ver comentarios");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa el TextView para mostrar los comentarios
        textViewComentarios = findViewById(R.id.idComentariosPrueba); // Asegúrate de tener este TextView en tu layout

        // Recuperar y mostrar el comentario
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        String comentarioGuardado = prefs.getString("Comentario", "No hay comentarios.");
        textViewComentarios.setText(comentarioGuardado);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(VerComentariosEcoMap.this, menuEcoMap.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpia la pila de actividades
                startActivity(intent);
                finish(); // Cierra la actividad actual
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}