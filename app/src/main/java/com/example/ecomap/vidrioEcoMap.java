package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class vidrioEcoMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vidrio_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarVidrio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita el botón de retroceso
        getSupportActionBar().setTitle("Vidrio");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración del btn actualizar datos
        Button btnActualizar = findViewById(R.id.idActualizarDatosVidrio);
        btnActualizar.setOnClickListener(v -> irAActualizacionDatosEcoMap());

        // Configuración del btn dejar comentario
        Button btnDejarComentario = findViewById(R.id.idDejarComentarioVidrio);
        btnDejarComentario.setOnClickListener(v -> irAComentarioEcoMap());

        // Configuración del btn calificar punto
        Button btnCalificarPunto = findViewById(R.id.idCalificarPuntoVidrio);
        btnCalificarPunto.setOnClickListener(v -> irACalificacionEcoMap());

        // Configuración del btn ver comentario
        Button btnVerComentarios = findViewById(R.id.idVerComentariosVidrio);
        btnVerComentarios.setOnClickListener(v -> irAVerComentariosEcoMap());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(vidrioEcoMap.this, menuEcoMap.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpia la pila de actividades
                startActivity(intent);
                finish(); // Cierra la actividad actual
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Método para redirigir al activity actualizacionDatosEcoMap
    private void irAActualizacionDatosEcoMap() {
        Intent intent = new Intent(vidrioEcoMap.this, actualizacionDatosEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity comentarioEcoMap
    private void irAComentarioEcoMap() {
        Intent intent = new Intent(vidrioEcoMap.this, comentarioEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity calificacionEcoMap
    private void irACalificacionEcoMap() {
        Intent intent = new Intent(vidrioEcoMap.this, calificacionEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity VerComentariosEcoMap
    private void irAVerComentariosEcoMap() {
        Intent intent = new Intent(vidrioEcoMap.this, VerComentariosEcoMap.class);
        startActivity(intent);
    }
}