package com.example.hackathonspring2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import static com.example.hackathonspring2019.Core.mAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText emailET;
    private EditText passwordET, nameET;
    private TextView signInTV;
    private Button signUpButton;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.emailET = (EditText)findViewById(R.id.emailET);
        this.passwordET = (EditText)findViewById(R.id.passwordET);
        this.nameET = (EditText)findViewById(R.id.nameET);
        this.signInTV = (TextView) findViewById(R.id.signInTV);
        this.signUpButton = (Button)findViewById(R.id.signupButton);
        this.progressBar = (ProgressBar)findViewById(R.id.progressbar);

        Core.mAuth = FirebaseAuth.getInstance();

        //findViewById(R.id.signupTV).setOnClickListener(this);
        //findViewById(R.id.loginButton).setOnClickListener(this);

        this.findViewById(R.id.signupButton).setOnClickListener(this);
        this.findViewById(R.id.signInTV).setOnClickListener(this);
    }

    private void registerUser()
    {
        String email = emailET.getText().toString().trim();
        String password = this.passwordET.getText().toString().trim();
        String name = this.nameET.getText().toString().trim();

        if(email.isEmpty())
        {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailET.setError("Enter a valid email");
            emailET.requestFocus();
            return;
        }

        if(password.length() < 6)
        {
            passwordET.setError("Minimum length of 6");
            passwordET.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            passwordET.setError("Password is required");
            passwordET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //Core.mAuth.
        Core.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    //Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, Screen3.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    //finish();
                    //startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.signupButton:
                registerUser();
                break;
            case R.id.signInTV:
                startActivity(new Intent(this, MainActivity.class));

                break;
        }
    }

    public void onSignUpButtonPressed(View v)
    {
        System.out.println("Sign up button");
        this.registerUser();
    }




}
