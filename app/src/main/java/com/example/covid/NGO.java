package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class NGO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_g_o);


        Button plcord = (Button) findViewById(R.id.profile_ngo);
        plcord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), profile_nr.class);
                startActivity(startplcord);
            }
        });


        Button plord = (Button) findViewById(R.id.inventory);
        plord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), inventory.class);
                startActivity(startplcord);
            }
        });

        Button pord = (Button) findViewById(R.id.request);
        pord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), all_req.class);
                startActivity(startplcord);
            }
        });

        Button ord = (Button) findViewById(R.id.acc_req);
        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), ngo_req_accp.class);
                startActivity(startplcord);
            }
        });

        Button rd = (Button) findViewById(R.id.hospital);
        rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), all_hos.class);
                startActivity(startplcord);
            }
        });

        Button r = (Button) findViewById(R.id.update);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startplcord = new Intent(getApplicationContext(), profile_n.class);
                startActivity(startplcord);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.ngo, menu);
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
