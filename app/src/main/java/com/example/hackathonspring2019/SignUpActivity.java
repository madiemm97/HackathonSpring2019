package com.example.hackathonspring2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText emailET;
    private EditText passwordET;
    private TextView signInTV;
    private Button signUpButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.emailET = (EditText)findViewById(R.id.emailET);
        this.passwordET = (EditText)findViewById(R.id.passwordET);
        this.signInTV = (TextView) findViewById(R.id.signupTV);
        this.signUpButton = (Button)findViewById(R.id.signupButton);

        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser()
    {
        String email = emailET.getText().toString().trim();
        String password = this.passwordET.getText().toString().trim();

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

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
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


}
