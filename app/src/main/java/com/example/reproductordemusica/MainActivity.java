package com.example.reproductordemusica;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    int repetir = 2, posicion = 0;
    ArrayList<MediaPlayer> listaCanciones;
    Button play_pause,btn_repetir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause = findViewById(R.id.btnPlayAndPause);
        btn_repetir = findViewById(R.id.btnAleatorio);

        cargarPlayList();
    }

   public void cargarPlayList() {

        listaCanciones = new ArrayList<>();

        listaCanciones.add(MediaPlayer.create(this,R.raw.musica));
        listaCanciones.add(MediaPlayer.create(this,R.raw.eladiohugo));

    }

    public void playAndPause(View view) {
        if(listaCanciones.get(posicion).isPlaying()){
            listaCanciones.get(posicion).pause();
            play_pause.setBackgroundResource(R.drawable.play);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }else{
            listaCanciones.get(posicion).start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }

    }

    public void stop(View view) {
        if (listaCanciones.get(posicion) != null) {
            listaCanciones.get(posicion).stop();
            cargarPlayList();
            posicion = 0;
            play_pause.setBackgroundResource(R.drawable.play);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    public void repetir (View view){
        if(repetir == 1){
            //btn_repetir.setBackgroundResource(R.drawable.aleatorio);
            Toast.makeText(this, "No Repetir", Toast.LENGTH_SHORT).show();
            listaCanciones.get(posicion).setLooping(false);
            repetir = 2;
        }else{
            //btn_repetir.setBackgroundResource(R.drawable.aleatorio);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            listaCanciones.get(posicion).setLooping(true);
            repetir = 1;
        }
    }

    public void siguiente(View view){
        if(posicion < listaCanciones.size() -1){

            if(listaCanciones.get(posicion).isPlaying()){
                listaCanciones.get(posicion).stop();
                posicion++;
                listaCanciones.get(posicion).start();
            }else {
                posicion++;
            }

        }else{
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void anterior (View view){
        if(posicion >=1){
            if(listaCanciones.get(posicion).isPlaying()){
                listaCanciones.get(posicion).stop();
                cargarPlayList();
                posicion --;
                listaCanciones.get(posicion).start();
            }else{
                posicion --;
            }
        }else{
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }
}