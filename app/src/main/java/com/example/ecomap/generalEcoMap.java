package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class generalEcoMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_general_eco_map);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración del btn actualizar datos
        Button btnActualizar = findViewById(R.id.idActualizarDatosGeneral);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAActualizacionDatosEcoMap();
            }
        });

        // Configuración del btn dejar comentario
        Button btnDejarComentario = findViewById(R.id.idDejarComentarioGeneral);
        btnDejarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAComentarioEcoMap();
            }
        });

        // Configuración del btn calificar punto
        Button btnCalificarPunto = findViewById(R.id.idCalificarPuntoGeneral);
        btnCalificarPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irACalificacionEcoMap();
            }
        });

        // Configuración del btn ver comentario
        Button btnVerComentarios = findViewById(R.id.idVerComentariosGeneral);
        btnVerComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAVerComentariosEcoMap();
            }
        });

    }

    // Método para redirigir al activity actualizacionDatosEcoMap
    private void irAActualizacionDatosEcoMap() {
        Intent intent = new Intent(generalEcoMap.this, actualizacionDatosEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity cometarioEcoMap
    private void irAComentarioEcoMap() {
        Intent intent = new Intent(generalEcoMap.this, comentarioEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity calificacionEcoMap
    private void irACalificacionEcoMap() {
        Intent intent = new Intent(generalEcoMap.this, calificacionEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity VerComentariosEcoMap
    private void irAVerComentariosEcoMap() {
        Intent intent = new Intent(generalEcoMap.this, VerComentariosEcoMap.class);
        startActivity(intent);
    }
}