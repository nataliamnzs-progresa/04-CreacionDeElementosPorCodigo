package com.example.a04_creaciondeelementosporcodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a04_creaciondeelementosporcodigo.databinding.ActivityAddAlumnoBinding;
import com.example.a04_creaciondeelementosporcodigo.modelos.Alumno;

public class AddAlumnoActivity extends AppCompatActivity {

    private ActivityAddAlumnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Inflate -> Instancia los objetos de la vista
        // 2. LayoutInflater -> se encarga de leer el xml/ encargado del trabajo del inflate
        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());
        // 3. No se olvidar de inicializar el binding
        setContentView(binding.getRoot());

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        binding.btnCrearAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alumno alumno;
                if((alumno = alumnoOK()) != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO", alumno);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(AddAlumnoActivity.this,"FALTAN DATOS",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Alumno alumnoOK(){
        if(binding.txtNombreAddAlumno.getText().toString().isEmpty() || binding.txtApellidosAddAlumno.getText().toString().isEmpty())
            return null;

        if(binding.spCiclosAddAlumno.getSelectedItemPosition() == 0)
            return null;

        if(binding.rgGruposAddAlumno.getCheckedRadioButtonId() == -1)
            return null;

        String nombre = binding.txtNombreAddAlumno.getText().toString();
        String apellidos = binding.txtApellidosAddAlumno.getText().toString();
        String ciclo = (String)binding.spCiclosAddAlumno.getSelectedItem();
        RadioButton grupo = findViewById(binding.rgGruposAddAlumno.getCheckedRadioButtonId());
        char grupoChar = grupo.getText().toString().charAt(0);

        return new Alumno(nombre, apellidos, ciclo, grupoChar);
    }
}