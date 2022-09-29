package com.example.pictureinpicture;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class PIPActivity extends AppCompatActivity {

    private Uri videoUri;
    private static final String TAG = "PIP_TAG";

    private VideoView videoView;
    private ImageButton pipBtn;
    private ActionBar actionBar;

    private PictureInPictureParams.Builder pictureInPictureParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip);

        actionBar=getSupportActionBar();
        videoView=findViewById(R.id.videoView);
        pipBtn=findViewById(R.id.pipBtn);
        setVideoView(getIntent());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            pictureInPictureParams = new PictureInPictureParams.Builder();
        }


        pipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictureInPictureMode();

            }
        });
    }

    private void setVideoView(Intent intent) {
        String videoURL = intent.getStringExtra("videoURL");
        Log.d(TAG, "setVideoView: URL"+ videoURL);

        MediaController mediaController=new MediaController(this);
        videoUri= Uri.parse(videoURL);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "onPrepared: Video Prepared, Playing...");
                mp.start();
            }
        });

    }

    private void pictureInPictureMode(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Log.d(TAG, "pictureInPictureMode: supports PIP ");

            Rational aspectRation = new Rational(videoView.getWidth(),videoView.getHeight());
            pictureInPictureParams.setAspectRatio(aspectRation).build();
            enterPictureInPictureMode(pictureInPictureParams.build());
        }
        else {
            Log.d(TAG, "pictureInPictureMode: Doesn't supports PIP ");
    }
}

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            if (!isInPictureInPictureMode()){
                Log.d(TAG, "onUserLeaveHint: was not in PIP");
                pictureInPictureMode();
            }
    }
            else {
            Log.d(TAG, "onUserLeaveHint: Already in PIP");
                
            }
}

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if (isInPictureInPictureMode){
            Log.d(TAG, "onPictureInPictureModeChanged: Entered PIP");

            pipBtn.setVisibility(View.GONE);
            actionBar.hide();
        }
        else {
            Log.d(TAG, "onPictureInPictureModeChanged: Exited PIP");

            pipBtn.setVisibility(View.VISIBLE);
            actionBar.show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d(TAG, "onNewIntent: Play new  Video ");
        setVideoView(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (videoView.isPlaying()){
            videoView.stopPlayback();
        }
    }
}

