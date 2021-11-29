package com.example.listaejercicio;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSonSerialicer {
    List<Dato> datos = null;
    private String fileName;
    private Context contexto;
    private Gson gson;

    JSonSerialicer(String fileName, Context contexto,List<Dato> datos) {
        this.fileName = fileName;
        this.contexto = contexto;
        this.datos=datos;
    }

    public void save(){
        gson = new Gson();
        String json = gson.toJson(datos);
        Writer writer = null;

        try{
            OutputStream out = contexto.openFileOutput(fileName,contexto.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Dato> load(){
        List<Dato> returner = null;

        BufferedReader reader = null;

        try{
            reader= new BufferedReader(new InputStreamReader(contexto.openFileInput(fileName)));
            gson = new Gson();
            Type type = new TypeToken<ArrayList<Dato>> (){}.getType();
            returner= gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(returner==null){
            return new ArrayList<>();
        }
        return returner;
    }
}
