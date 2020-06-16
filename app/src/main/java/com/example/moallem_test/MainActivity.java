package com.example.moallem_test;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaCas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
     VideoView videoView ;
     ImageButton mPlayButton;
     VideoView videoView2 ;
     ImageButton mPlayButton2;
     Uri uri;
    android.widget.MediaController mediaController;
    android.widget.MediaController mediaController2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView =(VideoView)findViewById(R.id.videoView);
        mPlayButton = (ImageButton) findViewById(R.id.imageButton);

        videoView2 =(VideoView)findViewById(R.id.videoView2);
        mPlayButton2 = (ImageButton) findViewById(R.id.imageButton2);
        uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.road_car_view);

        //Creating MediaController
        mediaController= new MediaController(this);
        mediaController2= new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView2.setMediaController(mediaController2);


        test(videoView,mPlayButton);
        test(videoView2,mPlayButton2);

    }
    void test(final VideoView videoView,final ImageButton mPlayButton){
        mPlayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    mPlayButton.setVisibility(View.VISIBLE);
                } else {
                    videoView.setVideoURI(uri);
                    videoView.requestFocus();
                    videoView.start();
                    mPlayButton.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

}
