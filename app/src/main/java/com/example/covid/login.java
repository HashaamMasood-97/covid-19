package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class login extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;


    DatabaseReference database;

    DatabaseReference hospitalDB;
    DatabaseReference ngoDB;

    FirebaseUser user;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();



        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);


       this.setTitle("Login");



        TextView plcord = (TextView) findViewById(R.id.textViewSignup);
        plcord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), signup.class);
                startActivity(startplcord);
            }
        });
        database = FirebaseDatabase.getInstance().getReference().child("Users");
        hospitalDB= FirebaseDatabase.getInstance().getReference("hospitals");
        ngoDB= FirebaseDatabase.getInstance().getReference("Ngo");


        final Button submit2 = (Button) findViewById(R.id.buttonLogin);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Please enter a valid email");
                    editTextEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    editTextPassword.setError("Minimum lenght of password should be 6");
                    editTextPassword.requestFocus();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            user=FirebaseAuth.getInstance().getCurrentUser();
                            uid=user.getUid();
                            database.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String role=dataSnapshot.child(uid).child("role").getValue(String.class);
                                    if(role.equals("NGO/Private Donor")){
                                        user=FirebaseAuth.getInstance().getCurrentUser();
                                        uid=user.getUid();
                                        ngoDB.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                Map<String, Object> map = (Map<String, Object>)dataSnapshot.child(uid).getValue();
                                                if (map != null) {
                                                    Intent intent = new Intent(login.this, NGO.class);
                                                    startActivity(intent);

                                                }
                                                else{
                                                    Intent intent = new Intent(login.this, profile_n.class);
                                                    startActivity(intent);
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                    else if(role.equals("Med Service/Hospital")){
                                        user=FirebaseAuth.getInstance().getCurrentUser();
                                        uid=user.getUid();
                                        hospitalDB.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                Map<String, Object> map = (Map<String, Object>)dataSnapshot.child(uid).getValue();
                                                if (map != null) {
                                                    Intent intent = new Intent(login.this, hospital.class);
                                                    startActivity(intent);

                                                }
                                                else{
                                                    Intent intent = new Intent(login.this, profile_h.class);
                                                    startActivity(intent);
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                    else {
                                        Intent intent = new Intent(login.this, MainActivity.class);
                                        startActivity(intent);
                                    }

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }

                        });

                        }
                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private void userLogin() {


    }


    @Override
    protected void onStart() {
        super.onStart();



        if (mAuth.getCurrentUser() != null) {
            database = FirebaseDatabase.getInstance().getReference().child("Users");
            user=FirebaseAuth.getInstance().getCurrentUser();
            uid=user.getUid();
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String role=dataSnapshot.child(uid).child("role").getValue(String.class);
                    if(role.equals("NGO/Private Donor")){
                        startActivity(new Intent(login.this, NGO.class));
                    }
                    else if(role.equals("Med Service/Hospital")){
                        startActivity(new Intent(login.this, hospital.class));
                    }
                    else{
                        startActivity(new Intent(login.this, MainActivity.class));
                    }

        };
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
    });


}
}
}
