package com.example.mediaplayerfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bouree);
        inicializarSeekBar();
    }

    private void inicializarSeekBar(){
        seekVolume = findViewById(R.id.seekVolume);

        //Configura AudioManager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Volume máximo e volume atual
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Configura valores máximos SeekBar
        seekVolume.setMax(volumeMaximo);

        //Configura valores atuais
        seekVolume.setProgress(volumeAtual);

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, AudioManager.FLAG_SHOW_UI);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void executarSom(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void pausarSom(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void pararSom(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.whiteroom);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

}