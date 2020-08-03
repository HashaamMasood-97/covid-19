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
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class all_acc_req_info extends AppCompatActivity {

    DatabaseReference ref;
    TextView  name, email, city, address,  phone, glooves, mask, waste, caps ,sani, details, status, acc_by, acc_com ,gen_at, acc_at ;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_acc_req_info);

        this.setTitle("Request Information");

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
        acc_by = (TextView) findViewById(R.id.acc_req);
        acc_com = (TextView) findViewById(R.id.acc_con);
        gen_at = (TextView) findViewById(R.id.date);
        acc_at = (TextView) findViewById(R.id.acc_date);
        image=(ImageView)findViewById(R.id.img);


        String key = getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("accepted_requests").child(key);


        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        address.setText(getIntent().getStringExtra("address"));
        city.setText(getIntent().getStringExtra("city"));
        phone.setText(getIntent().getStringExtra("phone"));
        glooves.setText(getIntent().getStringExtra("glooves"));
        String message=getIntent().getStringExtra("url");

        Picasso.get()
                .load(message)
                .into(image);
        mask.setText(getIntent().getStringExtra("masks"));
        caps.setText(getIntent().getStringExtra("caps"));
        waste.setText(getIntent().getStringExtra("waste"));
        sani.setText(getIntent().getStringExtra("sani"));
        details.setText(getIntent().getStringExtra("details"));
        status.setText(getIntent().getStringExtra("status"));
        acc_by.setText(getIntent().getStringExtra("acc_by"));
        acc_com.setText(getIntent().getStringExtra("acc_con"));
        gen_at.setText(getIntent().getStringExtra("gen_at"));
        acc_at.setText(getIntent().getStringExtra("acc_at"));










    }

    public void buttondelete(View view) {

        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(all_acc_req_info.this, "Record Deleted Successfull", Toast.LENGTH_LONG).show();
                    all_acc_req_info.this.finish();
                } else {
                    Toast.makeText(all_acc_req_info.this, "Record Not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }




}
