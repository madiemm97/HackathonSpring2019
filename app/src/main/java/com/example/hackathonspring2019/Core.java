package com.example.hackathonspring2019;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Core
{
    // Write a message to the database
    public static FirebaseDatabase database; // = FirebaseDatabase.getInstance();
    public static DatabaseReference myRef; // = database.getReference("message");
    public static FirebaseAuth mAuth;
    public static FirebaseUser currentUser;
}
