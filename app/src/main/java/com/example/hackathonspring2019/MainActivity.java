package com.example.hackathonspring2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText emailET;
    private EditText passwordET;
    private TextView signupTV;
    private Button loginButton;
    ProgressBar progressBar;
    private MainActivity myself;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.emailET = (EditText)findViewById(R.id.emailET);
        this.passwordET = (EditText)findViewById(R.id.passwordET);
        this.signupTV = (TextView) findViewById(R.id.signupTV);
        this.loginButton = (Button)findViewById(R.id.loginButton);
        this.myself = this;

        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        findViewById(R.id.signupTV).setOnClickListener(this);
        findViewById(R.id.loginButton).setOnClickListener(this);
        Core.mAuth = FirebaseAuth.getInstance();


    }

    public void onLoginButtonPressed(View v)
    {
        if(emailET.getText().toString().equals("") || passwordET.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter your information", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Core.mAuth.signInWithEmailAndPassword(emailET.getText().toString(), passwordET.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Core.currentUser = Core.mAuth.getCurrentUser();
                                Intent i = new Intent(myself, Screen3.class);
                                myself.startActivity(i);
                            }
                            else
                                {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(myself, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }


    public void onSubmitButtonPressed(View v)
    {
        Intent i = new Intent(this, SignUpActivity.class);
        this.startActivity(i);
    }

    private void userLogin()
    {
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        if (email.isEmpty()) {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("Please enter a valid email");
            emailET.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            emailET.setError("Password is required");
            emailET.requestFocus();
            return;
        }

        if (password.length() < 6) {
            emailET.setError("Minimum lenght of password should be 6");
            emailET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        Core.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    //finish();
                    FirebaseUser user = Core.mAuth.getCurrentUser();
                    //updateUI(user);
                    Intent intent = new Intent(MainActivity.this, Screen3.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    System.out.println("***" + task.getException().getMessage());
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Core.mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, Screen3.class));
        }
    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.signupTV:
                startActivity(new Intent(this, SignUpActivity.class));

                break;
            case R.id.loginButton:
                userLogin();
                break;
        }
    }
}
