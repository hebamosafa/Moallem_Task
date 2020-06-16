package com.example.moallem_test;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaCas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity  {
    StateBroadcastingVideoView videoView ;
     ImageButton mPlayButton;
    StateBroadcastingVideoView videoView2 ;
     ImageButton mPlayButton2;
     Uri uri;
    android.widget.MediaController mediaController;
    int stopPosition1=0;
    int stopPosition2=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView =(StateBroadcastingVideoView)findViewById(R.id.videoView);
        mPlayButton = (ImageButton) findViewById(R.id.imageButton);

        videoView2 =(StateBroadcastingVideoView)findViewById(R.id.videoView2);
        mPlayButton2 = (ImageButton) findViewById(R.id.imageButton2);
        //the vedio path
        uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.road_car_view);

        //Creating MediaController
        mediaController= new MediaController(this);
        //set listeners when paused to get the last position



        Controll_vedio(videoView,mPlayButton,1);
        Controll_vedio(videoView2,mPlayButton2,2);

    }

    void Controll_vedio(final StateBroadcastingVideoView videoView, final ImageButton mPlayButton,  final int id){
        videoView.setPlayPauseListener(new StateBroadcastingVideoView.PlayPauseListener() {
            @Override
            public void onPause() {
                mPlayButton.setVisibility(View.VISIBLE);
                if(id==1)
                {stopPosition1 = videoView.getCurrentPosition(); }
                else { stopPosition2 = videoView.getCurrentPosition(); }
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    mPlayButton.setVisibility(View.VISIBLE);
                } else {
                    videoView.setMediaController(mediaController);
                    videoView.setVideoURI(uri);
                    videoView.requestFocus();
                    if(id==1)
                    {videoView.seekTo(stopPosition1);}
                    else {videoView.seekTo(stopPosition2); }
                    videoView.start();
                    mPlayButton.setVisibility(View.INVISIBLE);
                }
            }
        });

    }


}
