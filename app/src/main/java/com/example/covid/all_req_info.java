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

public class all_req_info extends AppCompatActivity {

    DatabaseReference ref, ngo ,accreqDB;
    TextView  name, email, city, address,  phone, glooves, mask, waste, caps ,sani, details, status, date;
    FirebaseUser user;
    String acc_uid;
    String stat;
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_req_info);

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


    public void buttonaccept(View view) {

      final String name=getIntent().getStringExtra("name");
      final  String email=getIntent().getStringExtra("email");
      final String address=getIntent().getStringExtra("address");
      final String city=getIntent().getStringExtra("city");
      final  String phone=getIntent().getStringExtra("phone");
      final  String glooves=getIntent().getStringExtra("glooves");
      final String mask=getIntent().getStringExtra("masks");
      final String caps=getIntent().getStringExtra("caps");
      final String waste=getIntent().getStringExtra("waste");
      final String sani=getIntent().getStringExtra("sani");
      final String details=getIntent().getStringExtra("details");
      final  String date=getIntent().getStringExtra("date");
      final  String url=getIntent().getStringExtra("url");
      final String uid = getIntent().getStringExtra("uid");



                ngo.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapsho) {
                        String acc_by=dataSnapsho.child(acc_uid).child("name").getValue(String.class);
                        String acc_phone=dataSnapsho.child(acc_uid).child("phone").getValue(String.class);



                       // dataSnapshot.getRef().child("status").setValue("Accepted");
                       // dataSnapshot.getRef().child("acc_by").setValue(named);
                      //  dataSnapshot.getRef().child("acc_con").setValue(phone);
                     //   dataSnapshot.getRef().child("acc_uid").setValue(uid);


                        stat="Accepted";

                        String reqID = accreqDB.push().getKey();
                        reqData placeorder = new reqData( reqID, name, email, phone, address, city, glooves, mask, waste, caps, sani, details, stat,  uid, acc_by, acc_phone , date, url, acc_uid ,getDateCurrentTimeZone(ServerValue.TIMESTAMP));
                        accreqDB.child(reqID).setValue(placeorder);


                        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(all_req_info.this, "Request Accepted Successfully", Toast.LENGTH_LONG).show();
                                    all_req_info.this.finish();
                                } else {
                                    Toast.makeText(all_req_info.this, "Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


            }



    public  String getDateCurrentTimeZone(Map<String, String> timestamp) {
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getTimeZone("Asia/karachi");
            //   calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date currenTimeZone = (Date) calendar.getTime();
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }


}
