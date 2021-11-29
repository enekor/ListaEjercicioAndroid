package com.example.listaejercicio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick {

    private JSonSerialicer serialicer;

    private RecyclerView lista;
    private LinearLayoutManager linearLayout;
    private Button boton;
    private EditText titulo,contenido;
    private List<Dato> datos = new ArrayList<>();
    private Adaptador adapter;
    private Button borrarOk;
    private Button borrarCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datos = leerFichero();

        lista = findViewById(R.id.listaView);
        adapter = new Adaptador(datos,this);
        lista.setAdapter(adapter);

        linearLayout = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayout);

        titulo = findViewById(R.id.titulo);
        contenido = findViewById(R.id.contenido);
        boton = findViewById(R.id.boton);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.add(new Dato(titulo.getText().toString(),contenido.getText().toString()));

                lista.setAdapter(adapter);
                addDatos();

                titulo.setText("");
                contenido.setText("");
            }
        });
    }

    private Adaptador addDatos(){
       return adapter = new Adaptador(datos,this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        escribirFichero();
    }

    private List<Dato> leerFichero(){
        serialicer = new JSonSerialicer("prueba.json",MainActivity.this.getApplicationContext(),datos);//lo mismo que poner this

        return serialicer.load();
    }

    private void escribirFichero(){
        serialicer = new JSonSerialicer("prueba.json",MainActivity.this.getApplicationContext(),datos);//lo mismo que poner this

        serialicer.save();
    }

    @Override
    public void onClick(int posicion) {
        Toast.makeText(this, "manten pulsado para borrar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int posicion) {
        Toast.makeText(this, "en proceso de borrado", Toast.LENGTH_SHORT).show();

        borrarObjeto(posicion).show();
    }

    private AlertDialog borrarObjeto(int posicion) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setMessage("se procedera a borrar la nota")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datos.remove(posicion);
                        lista.setAdapter(adapter);
                        addDatos();
                    }
                })
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        addDatos();

        return alerta.create();
    }
}