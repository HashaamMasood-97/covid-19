package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class your_req extends AppCompatActivity {

    ListView li;
    ArrayAdapter<String> adapter;
    FirebaseUser user;
    List<String> itemlist;
    String uid;
    DatabaseReference database;
    reqData users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_req);
        li = (ListView) findViewById(R.id.listview1);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        itemlist =new ArrayList<>();
        users= new reqData();

        this.setTitle("Pending Request");

        database = FirebaseDatabase.getInstance().getReference().child("requests");



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                    for (DataSnapshot ds: dataSnapshot.getChildren())
                    {
                        {
                            users = ds.getValue(reqData.class);
                            String role = users.getuid();
                            String role1 = users.getStatus();
                            if (role.equals(uid)) {

                                    itemlist.add("Glooves: " + users.getGlooves() + "\n" + "Masks: " + users.getMask() + "\n" + "Waste Bags: " + users.getWaste() + "\n" +
                                            "Head Caps: " + users.getCaps() + "\n" + "Sanitizer: " + users.getSani() + "\n" + "Details: " + users.getDetails() + "\n" + "Status: " + users.getStatus()
                                            + "\n"  + "Date: " + users.getDate()
                                    );






                            }
                        }
                    }



                    adapter = new ArrayAdapter(your_req.this, R.layout.your_pen_req_p, R.id.Info1, itemlist);
                    li.setAdapter(adapter);


                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

}
}
