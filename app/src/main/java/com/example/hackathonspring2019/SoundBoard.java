package com.example.hackathonspring2019;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_board);

        final MediaPlayer quizBT = MediaPlayer.create(this, R.raw.quiz);
        final MediaPlayer shutupBT = MediaPlayer.create(this, R.raw.shutup);
        final MediaPlayer helloBT = MediaPlayer.create(this, R.raw.goodmorning);
        final MediaPlayer goodbyeBT = MediaPlayer.create(this, R.raw.goodbye);

        Button quiz = (Button) this.findViewById(R.id.quizBT);
        Button shutup = (Button) this.findViewById(R.id.shutupBT);
        Button hello = (Button) this.findViewById(R.id.helloBT);
        Button goodbye = (Button) this.findViewById(R.id.goodbyeBT);

        quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                quizBT.start();
            }
        });
        shutup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                shutupBT.start();
            }
        });
        hello.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                helloBT.start();
            }
        });
        goodbye.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goodbyeBT.start();
            }
        });

    }
}
