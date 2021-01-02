package com.sh.nogorcourier.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sh.nogorcourier.Model.Payment_Model;
import com.sh.nogorcourier.R;

public class Notification_User extends AppCompatActivity {


FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference,databaseReference1;

    FloatingActionButton flt;
    SwipeRefreshLayout sp;
    boolean test;
    ProgressDialog pd;

    TextView name,phone,add,area,b_ac_name,b_name,b_branch,b_acc_num,b_rout_number,ro_type,bKash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification__user);
        getSupportActionBar().setTitle("User Profile");

        sp=findViewById(R.id.swipe);

        bKash=findViewById(R.id.bkash1);
        flt=findViewById(R.id.enters);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        add=findViewById(R.id.address);
        area=findViewById(R.id.area);
        b_ac_name=findViewById(R.id.bank_ac_name);
        b_name=findViewById(R.id.bank_name);
        b_branch=findViewById(R.id.bank_branch);
        b_acc_num=findViewById(R.id.account_number);
        b_rout_number=findViewById(R.id.r_num);
        ro_type=findViewById(R.id.r_type);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("User_Profile");
        databaseReference1= firebaseDatabase.getReference("User_Payment");


      pd=new ProgressDialog(this);

        pd.setMessage("Loading...");
        pd.show();



        load();

        sp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
                test=false;




                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sp.setRefreshing(false);
                        if(!test) {
                            Toast.makeText(getApplicationContext(), "Check internet connection & try again", Toast.LENGTH_LONG).show();

                        }

                    }
                }, 12000);
            }
        });



        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final EditText nam,phn,ar,ad;


                LayoutInflater layoutInflater =LayoutInflater.from(Notification_User.this);
                View view=layoutInflater.inflate(R.layout.edit_profile,null);

                nam=view.findViewById(R.id.name);
                phn=view.findViewById(R.id.phone);
                ar=view.findViewById(R.id.area);
                ad=view.findViewById(R.id.address);


                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if(dataSnapshot.exists()) {

                            User_Profile user_profile = dataSnapshot.getValue(User_Profile.class);

                            nam.setText(user_profile.getName());
                            phn.setText(user_profile.getPhone());
                            ar.setText(user_profile.getArea());
                            ad.setText(user_profile.getAddress());
                        }else {
                           // Toast.makeText(getApplicationContext(),"Tap the edit button & create Profile",Toast.LENGTH_LONG).show();


                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





                new AlertDialog.Builder(Notification_User.this).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        User_Profile user_profile =new User_Profile(nam.getText().toString(),phn.getText().toString()

                        ,ar.getText().toString(),ad.getText().toString()
                        );
                        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user_profile);



                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })

                        .setCancelable(false).setView(view).show();











            }
        });














    }

    void load(){


        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists()) {

                    User_Profile user_profile = dataSnapshot.getValue(User_Profile.class);

                    name.setText("Name: "+user_profile.getName());
                    phone.setText("Phone: "+user_profile.getPhone());
                    area.setText("Area: "+user_profile.getArea());
                    add.setText("Address: "+user_profile.getAddress());
                    sp.setRefreshing(false);
                    test=true;
                    pd.dismiss();

                }else {
                    Toast.makeText(getApplicationContext(),"Tap the edit button & create Profile",Toast.LENGTH_LONG).show();

                    name.setText("Tap the edit button & create Profile");

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("acc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {


                    Payment_Model payment_model = dataSnapshot.getValue(Payment_Model.class);

                    b_ac_name.setText("Bank acc. name: " + payment_model.getB_ac_name());
                    b_name.setText("Bank name: " + payment_model.getBank_name());
                    b_branch.setText("Bank branch name: " + payment_model.getBank_branch());
                    b_acc_num.setText("Bank acc. number: " + payment_model.getBank_acc_number());
                    b_rout_number.setText("Bank routing number: " + payment_model.getRouting_num());
                    ro_type.setText("Bank routing type: " + payment_model.getType());
                    sp.setRefreshing(false);

                    test=true;
                    pd.dismiss();

                }else {

                    b_name.setText("No information found.");
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("bk").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists()){

                    String b=dataSnapshot.child("bKash").getValue(String.class);

                    bKash.setText(b);

                    sp.setRefreshing(false);
                    test=true;
                    pd.dismiss();


                }else {

                    bKash.setText("No information found.");
                }








            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


    }
}
