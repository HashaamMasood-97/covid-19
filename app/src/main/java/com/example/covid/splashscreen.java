package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class splashscreen extends AppCompatActivity {
    public static int SPLASH_TIME_OUT=3000;
    FirebaseAuth mAuth;



    DatabaseReference database;;


    FirebaseUser user;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mAuth.getCurrentUser() != null) {
                    database = FirebaseDatabase.getInstance().getReference().child("Users");
                    user= FirebaseAuth.getInstance().getCurrentUser();
                    uid=user.getUid();
                    database.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String role=dataSnapshot.child(uid).child("role").getValue(String.class);
                            if(role.equals("NGO/Private Donor")){
                                startActivity(new Intent(splashscreen.this, NGO.class));
                            }
                            else if(role.equals("Med Service/Hospital")){
                                startActivity(new Intent(splashscreen.this, hospital.class));
                            }
                            else{
                                startActivity(new Intent(splashscreen.this, MainActivity.class));
                            }

                        };
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
                else{
                    startActivity(new Intent(splashscreen.this, login.class));
                }

            }
        }, SPLASH_TIME_OUT);
    }
    }

