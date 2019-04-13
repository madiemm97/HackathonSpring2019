package com.example.hackathonspring2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Screen3 extends AppCompatActivity
{
    private TextView titleTV;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        this.titleTV = (TextView)findViewById(R.id.titleTV);
        this.submitButton = (Button)findViewById(R.id.submitButton);

        Core.mAuth = FirebaseAuth.getInstance();

    }

    public void onSignOutButtonPressed(View v) {
        Core.mAuth.getInstance().signOut();
        //FirebaseAuth.getInstance().signOut();
        System.out.println("***Did I sign out?)");
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }

    public void onAnnisScreenButtonPressed(View v)
    {
        Intent i = new Intent(this, SoundBoard.class);
        this.startActivity(i);
    }

    public void onMadiesQuizButtonPressed(View v)
    {
        Intent i = new Intent(this, gamePage1.class);
        this.startActivity(i);
    }

}
