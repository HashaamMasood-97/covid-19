package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class admin_req_details extends AppCompatActivity {

    DatabaseReference ref, ngo ,accreqDB;
    TextView  name, email, city, address,  phone, glooves, mask, waste, caps ,sani, details, status, date;
    FirebaseUser user;
    String acc_uid;
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_req_details);

        this.setTitle("Details");

        name = (TextView) (findViewById(R.id.name));
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.contact);
        address = (TextView) findViewById(R.id.address);
        city = (TextView) findViewById(R.id.city);
        glooves = (TextView) findViewById(R.id.glooves);
        mask = (TextView) findViewById(R.id.masks);
        waste = (TextView) findViewById(R.id.waste);
        caps = (TextView) findViewById(R.id.caps);
        sani = (TextView) findViewById(R.id.sani);
        details = (TextView) findViewById(R.id.details);
        status = (TextView) findViewById(R.id.status);
        date = (TextView) findViewById(R.id.date);
        image=(ImageView)findViewById(R.id.img);



        String key = getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("requests").child(key);


        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        address.setText(getIntent().getStringExtra("address"));
        city.setText(getIntent().getStringExtra("city"));
        phone.setText(getIntent().getStringExtra("phone"));
        String message=getIntent().getStringExtra("url");

        Picasso.get()
                .load(message)
                .into(image);
        glooves.setText(getIntent().getStringExtra("glooves"));
        mask.setText(getIntent().getStringExtra("masks"));
        caps.setText(getIntent().getStringExtra("caps"));
        sani.setText(getIntent().getStringExtra("sani"));
        waste.setText(getIntent().getStringExtra("waste"));
        status.setText(getIntent().getStringExtra("status"));
        date.setText(getIntent().getStringExtra("date"));
        details.setText(getIntent().getStringExtra("details"));




        user= FirebaseAuth.getInstance().getCurrentUser();
        acc_uid=user.getUid();


        ngo = FirebaseDatabase.getInstance().getReference("Ngo");


        accreqDB= FirebaseDatabase.getInstance().getReference("accepted_requests");



    }




}
