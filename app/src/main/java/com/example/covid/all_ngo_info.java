package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class all_ngo_info extends AppCompatActivity {
    DatabaseReference ref;
    TextView name, email, city, address, phone, type, glooves, mask, waste, caps, sani, details;
    ImageView image;
    FirebaseUser user;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ngo_info);

        this.setTitle("Details");

        name = (TextView) (findViewById(R.id.name));
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.contact);
        address = (TextView) findViewById(R.id.address);
        city = (TextView) findViewById(R.id.city);
        type = (TextView) findViewById(R.id.type);
        glooves = (TextView) findViewById(R.id.glooves);
        mask = (TextView) findViewById(R.id.masks);
        waste = (TextView) findViewById(R.id.waste);
        caps = (TextView) findViewById(R.id.head);
        sani = (TextView) findViewById(R.id.sanitizers);
        details = (TextView) findViewById(R.id.details);
        image=(ImageView)findViewById(R.id.img);



        String key = getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("Ngo").child(key);


        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        address.setText(getIntent().getStringExtra("address"));
        String message=getIntent().getStringExtra("url");

        Picasso.get()
                .load(message)
                .into(image);
        city.setText(getIntent().getStringExtra("city"));
        phone.setText(getIntent().getStringExtra("phone"));
        type.setText(getIntent().getStringExtra("type"));
        glooves.setText(getIntent().getStringExtra("glooves"));
        mask.setText(getIntent().getStringExtra("masks"));
        caps.setText(getIntent().getStringExtra("caps"));
        sani.setText(getIntent().getStringExtra("sani"));
        details.setText(getIntent().getStringExtra("Extra"));


    }
}