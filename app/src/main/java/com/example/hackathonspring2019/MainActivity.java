package com.example.hackathonspring2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText emailET;
    private EditText passwordET;
    private TextView signupTV;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.emailET = (EditText)findViewById(R.id.emailET);
        this.passwordET = (EditText)findViewById(R.id.passwordET);
        this.signupTV = (TextView) findViewById(R.id.signupTV);
        this.signupButton = (Button)findViewById(R.id.signupButton);

        findViewById(R.id.signupTV).setOnClickListener(this);
        Core.mAuth = FirebaseAuth.getInstance();


    }


    public void onSubmitButtonPressed(View v)
    {
        Intent i = new Intent(this, SignUpActivity.class);
        this.startActivity(i);
    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.signupTV:
                startActivity(new Intent(this, SignUpActivity.class));

                break;
        }
    }
}
