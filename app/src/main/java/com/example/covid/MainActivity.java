package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseUser user;
    String uid;
    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        database = FirebaseDatabase.getInstance().getReference().child("Users");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String name=dataSnapshot.child(uid).child("name").getValue(String.class);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        this.setTitle(name); */

        Button plcord = (Button) findViewById(R.id.hospital);
        plcord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), all_hos.class);
                startActivity(startplcord);
            }
        });

        Button plcor = (Button) findViewById(R.id.Ngo);
        plcor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), all_ngo.class);
                startActivity(startplcord);
            }
        });

        Button plco = (Button) findViewById(R.id.acc_req);
        plco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), all_acc_req.class);
                startActivity(startplcord);
            }
        });


        Button plc = (Button) findViewById(R.id.pen_req);
        plc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), all_req.class);
                startActivity(startplcord);
            }
        });

        Button lc = (Button) findViewById(R.id.about);
        lc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), about.class);
                startActivity(startplcord);
            }
        });


        Button pc = (Button) findViewById(R.id.terms);
        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), terms.class);
                startActivity(startplcord);
            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, login.class) );

                break;
        }

        return true;
    }
}
