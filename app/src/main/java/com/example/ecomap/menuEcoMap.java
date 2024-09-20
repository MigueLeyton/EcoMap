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

public class menuEcoMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_eco_map);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el botón para ir a vidrioEcoMap
        Button btnVidrio = findViewById(R.id.idVidrio);
        btnVidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAVidrioEcoMap();
            }
        });

        // Configurar el botón para ir a plasticoEcoMap
        Button btnPlastico = findViewById(R.id.idPlastico);
        btnPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAPlasticoEcoMap();
            }
        });

        // Configurar el botón para ir a cartonEcoMap
        Button btnCarton = findViewById(R.id.idCarton);
        btnCarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irACartonEcoMap();
            }
        });

        // Configurar el botón para ir a organicaEcoMap
        Button btnOrganica = findViewById(R.id.idOrganica);
        btnOrganica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAOrganicaEcoMap();
            }
        });

        // Configurar el botón para ir a generalEcoMap
        Button btnGeneral = findViewById(R.id.idGeneral);
        btnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAGeneralEcoMap();
            }
        });

        // Configurar el botón para ir a historicaEcoMap
        Button btnHistorica = findViewById(R.id.idHistorica);
        btnHistorica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAHistoricaEcoMap();
            }
        });
    }

    // Método para redirigir a la actividad vidrioEcoMap
    private void irAVidrioEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, vidrioEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir a la actividad plasticoEcoMap
    private void irAPlasticoEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, plasticoEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir a la actividad cartonEcoMap
    private void irACartonEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, cartonEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir a la actividad organicaEcoMap
    private void irAOrganicaEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, organicaEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir a la actividad generalEcoMap
    private void irAGeneralEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, generalEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir a la actividad historicaEcoMap
    private void irAHistoricaEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, historicaEcoMap.class);
        startActivity(intent);
    }
}