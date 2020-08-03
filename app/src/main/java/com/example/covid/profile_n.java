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
import android.widget.TextView;
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

public class profile_n extends AppCompatActivity {
    EditText name, email, city, address,  phone, glooves, mask, waste, caps ,sani, details, url;



    DatabaseReference ngoDB;

    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_n);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.contact);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        details = (EditText) findViewById(R.id.extra);
        glooves = (EditText) findViewById(R.id.glooves);
        mask = (EditText) findViewById(R.id.masks);
        waste = (EditText) findViewById(R.id.waste);
        caps = (EditText) findViewById(R.id.head);
        sani = (EditText) findViewById(R.id.sanitizers);
        url = (EditText) findViewById(R.id.url);

        this.setTitle("Update Profile");

        final Spinner spnweight = findViewById(R.id.weight_select_bmi);

        final ArrayAdapter<CharSequence> adapterweight = ArrayAdapter.createFromResource(this, R.array.ngounit, android.R.layout.simple_spinner_item);
        adapterweight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnweight.setAdapter(adapterweight);

        ngoDB= FirebaseDatabase.getInstance().getReference("Ngo");

        final Button submit2 = (Button) findViewById(R.id.submit_h);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString().trim();
                String em = email.getText().toString().trim();
                String ph = phone.getText().toString().trim();
                String add = address.getText().toString().trim();
                String ci = city.getText().toString().trim();
                String det = details.getText().toString().trim();
                String msk = mask.getText().toString().trim();
                String glv = glooves.getText().toString().trim();
                String cap = caps.getText().toString().trim();
                String wst = waste.getText().toString().trim();
                String san = sani.getText().toString().trim();
                String ur = url.getText().toString().trim();






                if (n.isEmpty()) {
                    name.setError("field can't not be left blank");
                    name.requestFocus();
                    return;
                }

                if (add.isEmpty()) {
                    address.setError("field can't not be left blank");
                    address.requestFocus();
                    return;
                }

                if (ci.isEmpty()) {
                    city.setError("field can't not be left blank");
                    city.requestFocus();
                    return;
                }


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


                if (ur.isEmpty()) {
                    url.setError("field can't not be left blank");
                    url.requestFocus();
                    return;
                }


                if (em.isEmpty()) {
                    email.setError("Please enter your email");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
                    email.setError("Please enter a valid email");
                    email.requestFocus();
                    return;
                }

                if (ph.isEmpty()) {
                    phone.setError("Please enter your phone number");
                    phone.requestFocus();
                    return;
                }

                if (ph.length() < 7) {
                    phone.setError("Please enter a valid phone number");
                    phone.requestFocus();
                    return;
                }



           /* count++;

        }
*/
                user=FirebaseAuth.getInstance().getCurrentUser();
                uid=user.getUid();
                final String type =spnweight.getSelectedItem().toString();

                ngoData placeorder = new ngoData( n, em, ph, add, ci, type, glv, msk, wst, cap, san, det, ur, uid);
                ngoDB.child(uid).setValue(placeorder);


                if(CheckNetworkss.isInternetAvailable(profile_n.this)){
                    Toast.makeText(getApplicationContext(), "Information Updated Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(profile_n.this, NGO.class));
                }
                else {
                    Toast.makeText(profile_n.this,"No Internet Connection",3000).show();
                }

            }



        });
    };




}

class CheckNetworkss {
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



