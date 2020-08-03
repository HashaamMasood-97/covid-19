package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    ProgressBar progressBar;
    EditText editTextEmail, editTextPassword, editTextName;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        this.setTitle("Signup");

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        final Spinner spnweight = findViewById(R.id.weight_select_bmi);

        final ArrayAdapter<CharSequence> adapterweight = ArrayAdapter.createFromResource(this, R.array.weightUnit, android.R.layout.simple_spinner_item);
        adapterweight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnweight.setAdapter(adapterweight);

        TextView plcord = (TextView) findViewById(R.id.textViewLogin);
        plcord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), login.class);
                startActivity(startplcord);
            }
        });

        final Button submit2 = (Button) findViewById(R.id.buttonSignUp);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = editTextName.getText().toString().trim();
                final String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                final String role =spnweight.getSelectedItem().toString();








                if (name.isEmpty()) {
                    editTextName.setError("Name is required");
                    editTextName.requestFocus();
                    return;
                }

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

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            User user = new User(
                                    name,
                                    email,
                                    role
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        Toast.makeText(signup.this, "Signed Up Successfully", Toast.LENGTH_LONG).show();
                                    } else {

                                    }
                                }
                            });

                            FirebaseAuth.getInstance().signOut();
                            finish();
                            startActivity(new Intent(signup.this, login.class) );

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
        });

    }




}

