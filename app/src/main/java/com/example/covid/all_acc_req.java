package com.example.covid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class all_acc_req extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_acc_req);


        lv=(ListView) findViewById(R.id.LV);
        Query query = FirebaseDatabase.getInstance().getReference().child("accepted_requests");
        FirebaseListOptions<reqData> options= new FirebaseListOptions.Builder<reqData>()
                .setLayout(R.layout.allreqaccdata)
                .setQuery(query,reqData.class)
                .build();

        this.setTitle("Accepted Request");


        adapter2= new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView name= v.findViewById(R.id.name);
                TextView email= v.findViewById(R.id.date);
                TextView status= v.findViewById(R.id.status);
                ImageView image= v.findViewById(R.id.img);




                reqData user= (reqData) model;

                name.setText("Name: " + user.getName().toString());
                email.setText("Date: "  + user.getDate().toString());
                status.setText("Status: " + user.getStatus().toString());

                String message= user.getUrl().toString();

                Picasso.get()
                        .load(message)
                        .into(image);



            }
        };
        lv.setAdapter(adapter2);

        //lv.setStackFromBottom(true);

        //  // ADD THIS LINE TO REVERSE ORDER!
        // adapter2.notifyDataSetChanged();



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                Intent Deletepdate=  new Intent(all_acc_req.this, all_acc_req_info.class);
                reqData user= (reqData) adapterView.getItemAtPosition(position);
                Deletepdate.putExtra("email", user.getEmail());
                Deletepdate.putExtra("key", user.getReqID());
                Deletepdate.putExtra("name", user.getName());
                Deletepdate.putExtra("phone", user.getPhone());
                Deletepdate.putExtra("address", user.getAddress());
                Deletepdate.putExtra("city", user.getCity());
                Deletepdate.putExtra("details", user.getDetails());
                Deletepdate.putExtra("glooves", user.getGlooves());
                Deletepdate.putExtra("masks", user.getMask());
                Deletepdate.putExtra("waste", user.getWaste());
                Deletepdate.putExtra("sani", user.getSani());
                Deletepdate.putExtra("caps", user.getCaps());
                Deletepdate.putExtra("details", user.getDetails());
                Deletepdate.putExtra("status", user.getStatus());
                Deletepdate.putExtra("acc_by", user.getAcc_by());
                Deletepdate.putExtra("acc_con", user.getAcc_con());
                Deletepdate.putExtra("gen_at", user.getDate());
                Deletepdate.putExtra("acc_at", user.getAccdate());
                Deletepdate.putExtra("url", user.getUrl());




                startActivity(Deletepdate);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.stopListening();
    }


}
