package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class gen_req extends AppCompatActivity {
    EditText glooves, mask, waste, caps ,sani, details;



    DatabaseReference reqDB, database;
    String status;
    FirebaseUser user;
    String uid;
    String acc_by = "";
    String acc_uid = "";
    String acc_con = "";
    String acc_date="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_req);

        this.setTitle("Generate Request");

        glooves = (EditText) findViewById(R.id.glooves);
        mask = (EditText) findViewById(R.id.masks);
        waste = (EditText) findViewById(R.id.waste);
        caps = (EditText) findViewById(R.id.head);
        sani = (EditText) findViewById(R.id.sanitizers);
        details = (EditText) findViewById(R.id.extra);

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();


        reqDB= FirebaseDatabase.getInstance().getReference("requests");
        database = FirebaseDatabase.getInstance().getReference().child("hospitals");

        final Button submit2 = (Button) findViewById(R.id.submit_h);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name=dataSnapshot.child(uid).child("name").getValue(String.class);
                        String email=dataSnapshot.child(uid).child("email").getValue(String.class);
                        String phone=dataSnapshot.child(uid).child("phone").getValue(String.class);
                        String  address=dataSnapshot.child(uid).child("address").getValue(String.class);
                        String city=dataSnapshot.child(uid).child("city").getValue(String.class);
                        String url=dataSnapshot.child(uid).child("url").getValue(String.class);


                        String msk = mask.getText().toString().trim();
                        String glv = glooves.getText().toString().trim();
                        String cap = caps.getText().toString().trim();
                        String wst = waste.getText().toString().trim();
                        String san = sani.getText().toString().trim();
                        String det = details.getText().toString().trim();







                        if (msk.isEmpty()) {
                            mask.setError("field can't not be left blank");
                            mask.requestFocus();
                            return;
                        }

                        if (glv.isEmpty()) {
                            glooves.setError("field can't not be left blank");
                            glooves.requestFocus();
                            return;
                        }
                        if (san.isEmpty()) {
                            sani.setError("field can't not be left blank");
                            sani.requestFocus();
                            return;
                        }

                        if (wst.isEmpty()) {
                            waste.setError("field can't not be left blank");
                            waste.requestFocus();
                            return;
                        }

                        if (cap.isEmpty()) {
                            caps.setError("field can't not be left blank");
                            caps.requestFocus();
                            return;
                        }

                        if (det.isEmpty()) {
                            details.setError("field can't not be left blank");
                            details.requestFocus();
                            return;
                        }


                        status="Pending";
                        user= FirebaseAuth.getInstance().getCurrentUser();
                        uid=user.getUid();
                        String reqID = reqDB.push().getKey();
                        reqData placeorder = new reqData( reqID, name, email, phone, address, city, glv, msk, wst, cap, san, det, status,  uid, acc_by, acc_con ,getDateCurrentTimeZone(ServerValue.TIMESTAMP),url, acc_uid,acc_date);
                        reqDB.child(reqID).setValue(placeorder);

                        if(CheckNetworks.isInternetAvailable(gen_req.this)){
                            Toast.makeText(getApplicationContext(), "Request Sumitted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(gen_req.this, hospital.class));
                        }
                        else {
                            Toast.makeText(gen_req.this,"No Internet Connection",3000).show();
                        }


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });





            }



        });
    };
    public  String getDateCurrentTimeZone(Map<String, String> timestamp) {
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getTimeZone("Asia/karachi");
            //   calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
            Date currenTimeZone = (Date) calendar.getTime();
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }
}

class CheckNetworks {
    private static final String TAG = CheckNetwork.class.getSimpleName();

    public static boolean isInternetAvailable(Context context) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            Log.d(TAG,"no internet connection");
            return false;
        }
        else {
            if(info.isConnected()) {
                Log.d(TAG," internet connection available...");
                return true;
            }
            else {
                Log.d(TAG," internet connection");
                return true;
            }

        }
    }
}






