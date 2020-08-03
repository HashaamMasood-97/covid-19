package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class hospital extends AppCompatActivity {

    FirebaseUser user;
    String uid;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        Button plcord = (Button) findViewById(R.id.profile_hos);
        plcord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), profile_h.class);
                startActivity(startplcord);
            }
        });

        Button plcor = (Button) findViewById(R.id.manage_hos);
        plcor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), profile_hr.class);
                startActivity(startplcord);
            }
        });

        Button plco = (Button) findViewById(R.id.gen_req);
        plco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), gen_req.class);
                startActivity(startplcord);
            }
        });

        Button plc = (Button) findViewById(R.id.your_req);
        plc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), your_req.class);
                startActivity(startplcord);
            }
        });

        Button pl = (Button) findViewById(R.id.your_req_acc);
        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), your_req_acc.class);
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

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        database = FirebaseDatabase.getInstance().getReference().child("hospitals");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.hospital, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, login.class) );

         /*   case R.id.userName:
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      TextView  name = (TextView) (findViewById(R.id.name));
                        dataSnapshot.child(uid).child("name").getValue(String.class);
                        name.setText(getIntent().getStringExtra("name"));

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                }); */


                break;
        }

        return true;
    }

}
