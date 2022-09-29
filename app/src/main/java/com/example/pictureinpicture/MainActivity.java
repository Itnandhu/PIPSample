package com.example.pictureinpicture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String videoUrlOne = "https://www.youtube.com/watch?v=mnaePNH9V3k";
    private static final String videoUrlTwo = "https://www.youtube.com/watch?v=b21fiIyOW4A";
    private static final String videoUrlThree = "https://www.youtube.com/watch?v=e7pgKwnDwyU";

    private Button videoOneBtn, videoTwoBtn, videoThreeBtn;
    private TextView tvDummy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoOneBtn = findViewById(R.id.videoOneBtn);
        videoTwoBtn = findViewById(R.id.videoTwoBtn);
        videoThreeBtn = findViewById(R.id.videoThreeBtn);
        tvDummy = findViewById(R.id.tvDummy);


        videoOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(videoUrlOne);

            }
        });
        videoTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(videoUrlTwo);
            }
        }
        );
        videoThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(videoUrlThree);

            }
        });

        tvDummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ToggleActivity.class);
                startActivity(intent);
            }
        });


    }

    private void playVideo(String videoUrl) {
        Intent intent = new Intent(MainActivity.this, PIPActivity.class);
        intent.putExtra("videoURL", videoUrl);
        startActivity(intent);
    }
}