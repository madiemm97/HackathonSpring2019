package com.example.hackathonspring2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class gamePage2 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page2);
    }

    public void onButton1Pressed(View v)
    {
        Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT).show();
    }

    public void onButton2Pressed(View v)
    {
        Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT).show();
    }

    public void onButton3Pressed(View v)
    {
        Intent i = new Intent(this, gamePage3.class);
        this.startActivity(i);
    }

    public void onButton4Pressed(View v)
    {
        Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT).show();
    }
}
