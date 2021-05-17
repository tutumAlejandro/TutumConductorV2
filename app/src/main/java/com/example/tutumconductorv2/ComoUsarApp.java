package com.example.tutumconductorv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ComoUsarApp extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_como_usar_app);

       videoView = (VideoView) findViewById(R.id.videoView3);


        Uri uri = Uri.parse("https://www.youtube.com/watch?v=CJ5sIEqYJ6Q&t=73s");
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        videoView.isPlaying();

    }

    public void btnRegresarUsarApp(View V){
        Intent intentIni = new Intent(ComoUsarApp.this, activity_ayuda.class);
        startActivity(intentIni);
    }

}