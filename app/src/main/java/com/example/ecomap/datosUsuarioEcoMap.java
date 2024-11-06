package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class datosUsuarioEcoMap extends AppCompatActivity {

    private ListView userListView;
    private bdEcoMap databaseHelper;
    private String usuarioSeleccionado; // Variable para almacenar el usuario seleccionado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datos_usuario_eco_map);

        // Inicializa la base de datos
        databaseHelper = new bdEcoMap(this);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarDatosUsuario);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Datos almacenados");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botón para modificar usuario
        Button btnModificarUsuario = findViewById(R.id.buttonAddUser);
        btnModificarUsuario.setOnClickListener(v -> irModificarUsuario());

        // Botón para eliminar usuario
        Button btnEliminarUsuario = findViewById(R.id.buttonDeleteUser);
        btnEliminarUsuario.setOnClickListener(v -> eliminarUsuario());

        // Configura el ListView y muestra la lista de usuarios
        userListView = findViewById(R.id.userListView);
        List<String> userList = databaseHelper.getAllUsers();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        userListView.setAdapter(adapter);

        // Evento de clic para seleccionar el usuario de la lista
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usuarioSeleccionado = userList.get(position);
                Toast.makeText(datosUsuarioEcoMap.this, "Usuario seleccionado: " + usuarioSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Redirige a la actividad de loginEcoMap y limpia la pila de actividades
                Intent intent = new Intent(datosUsuarioEcoMap.this, loginMenuEcoMap.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpia la pila de actividades
                startActivity(intent);
                finish(); // Cierra la actividad actual
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void irModificarUsuario() {
        if (usuarioSeleccionado != null && !usuarioSeleccionado.isEmpty()) {
            Intent intent = new Intent(datosUsuarioEcoMap.this, modificarUsuarioEcoMap.class);
            intent.putExtra("nombreCompleto", usuarioSeleccionado); // Enviar el usuario seleccionado
            startActivity(intent);
        } else {
            Toast.makeText(this, "Seleccione un usuario para modificar.", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarUsuario() {
        if (usuarioSeleccionado != null && !usuarioSeleccionado.isEmpty()) {
            if (databaseHelper.eliminarUsuario(usuarioSeleccionado)) {
                Toast.makeText(this, "Usuario eliminado exitosamente.", Toast.LENGTH_SHORT).show();
                actualizarListaUsuarios(); // Actualizar la lista después de eliminar
            } else {
                Toast.makeText(this, "Error al eliminar el usuario. Verifica que exista.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Seleccione un usuario para eliminar.", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarListaUsuarios() {
        List<String> userList = databaseHelper.getAllUsers();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        userListView.setAdapter(adapter);
    }
}
