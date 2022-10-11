package com.example.a04_creaciondeelementosporcodigo;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04_creaciondeelementosporcodigo.modelos.Alumno;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import com.example.a04_creaciondeelementosporcodigo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Binding es una variable que inicializa todos los ID de esta actividad//
    // 1. Definir la variable dell Binding
    private ActivityMainBinding binding;

    private ActivityResultLauncher<Intent> loucherCreateAlumno;

    //Elementos necesarios para Mostrar Datos en un contenedor
    // 1º. El contenedor de los datos (binding.contenido.contenidoMain)
    // 2º. Coger los datos
    private ArrayList<Alumno> alumnosList;
    // 3º. Lógica para montar los elementos en el contenedor


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. Instanciar el Binding al layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // 3. Asignar el Binding como Contenido de la Actividad
        setContentView(binding.getRoot());

        alumnosList = new ArrayList<>();

        inicializarLaunchers();
        pintarElementos();
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loucherCreateAlumno.launch(new Intent(MainActivity.this, AddAlumnoActivity.class));
            }
        });
    }

    private void inicializarLaunchers() {
        loucherCreateAlumno = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK && result.getData() != null){
                            Alumno alumno = (Alumno) result.getData().getExtras().getSerializable("ALUMNO");
                            alumnosList.add(alumno);
                            pintarElementos();
                        }
                    }
                });
    }

    /*
    * Encargada de recoger la lista de Alumnos y por cada uno insertarlo en el contenedor
    */
    private void pintarElementos() {
        //limpiar el contenedor
        binding.content.contenedorMain.removeAllViews();
        //recoger la lista y mostrar los datos de cada alumno
        for (Alumno alumno: alumnosList) {
            TextView alumnoTxt = new TextView(this);
            alumnoTxt.setText(alumno.toString());
            binding.content.contenedorMain.addView(alumnoTxt);
        }
    }
}