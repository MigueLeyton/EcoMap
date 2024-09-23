package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class calificacionEcoMap extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button buttonGuardarCalificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calificacion_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarCalificacion);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Calificación");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa RatingBar y botón
        ratingBar = findViewById(R.id.ratingBar);
        buttonGuardarCalificacion = findViewById(R.id.idGuardarCalificacion);

        // Configura el botón para guardar la calificación
        buttonGuardarCalificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCalificacion();
            }
        });

        // Configuración del botón salir
        Button btnVidrio = findViewById(R.id.idSalirCalificacion);
        btnVidrio.setOnClickListener(v -> irAMenuEcoMap());
    }

    private void guardarCalificacion() {
        float calificacion = ratingBar.getRating();
        // Aquí puedes guardar la calificación en una base de datos o en preferencias, si es necesario
        // SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        // prefs.edit().putFloat("calificacion", calificacion).apply();

        // Mensaje de confirmación
        Toast.makeText(this, "Calificación guardada: " + calificacion, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(calificacionEcoMap.this, menuEcoMap.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Métodos para redirigir a las actividades correspondientes
    private void irAMenuEcoMap() {
        Intent intent = new Intent(calificacionEcoMap.this, menuEcoMap.class);
        startActivity(intent);
    }
}