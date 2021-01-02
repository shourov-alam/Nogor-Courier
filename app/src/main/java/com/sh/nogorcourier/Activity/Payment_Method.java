package com.sh.nogorcourier.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sh.nogorcourier.Model.Payment_Model;
import com.sh.nogorcourier.R;

public class Payment_Method extends AppCompatActivity {

    DatabaseReference databaseReference;

    EditText number,b_ac_name,b_name,b_branch,b_ac_number,routing_no;
    Spinner sp1,sp2;
    Button enter;
    String type;
    boolean empty;
    SwipeRefreshLayout sp;
    boolean test;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment__method);

        getSupportActionBar().setTitle("Payment Method");
        sp=findViewById(R.id.swipe);

        databaseReference= FirebaseDatabase.getInstance().getReference("User_Payment");

        number = findViewById(R.id.bkash_number);
        b_ac_name = findViewById(R.id.bank_account_name);
        b_name = findViewById(R.id.bank_name);
        b_branch = findViewById(R.id.bank_branch);
        b_ac_number = findViewById(R.id.bank_accno);
        routing_no = findViewById(R.id.bank_routing);

        sp1=findViewById(R.id.spinner);
        sp2=findViewById(R.id.spinner1);
        enter=findViewById(R.id.enter);
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

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==1){

                    type= "Current";
                }
                if(position==2){

                    type= "Saving";
                }
                if(position==3){

                    type= "AWCDI";
                }
                if(position==4){

                    type= "SND";
                }
                if(position==5){

                    type= "STD";
                }
                if(position==6){

                    type= "AWCA";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                if(position==0){
                    b_ac_name.setVisibility(View.GONE);
                    b_name.setVisibility(View.GONE);
                    b_branch.setVisibility(View.GONE);
                    b_ac_number.setVisibility(View.GONE);
                    routing_no.setVisibility(View.GONE);
                    sp2.setVisibility(View.GONE);
                    number.setVisibility(View.VISIBLE);


                }else {

                    b_ac_name.setVisibility(View.VISIBLE);
                    b_name.setVisibility(View.VISIBLE);
                    b_branch.setVisibility(View.VISIBLE);
                    b_ac_number.setVisibility(View.VISIBLE);
                    routing_no.setVisibility(View.VISIBLE);
                    sp2.setVisibility(View.VISIBLE);
                    number.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




  enter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


          hide();

          if(sp1.getSelectedItemPosition() == 0){

              if(number.getText().toString().isEmpty()){

                  Toast.makeText(getApplicationContext(),"Enter your bKash number",Toast.LENGTH_LONG).show();

              }else {
                  databaseReference.child(FirebaseAuth.getInstance().getUid()).child("bk").child("bKash").setValue(number.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                          Toast.makeText(getApplicationContext(),"Successfully Saved bKash information",Toast.LENGTH_LONG).show();
                      }
                  });

              }

          }
          else {

              if(sp2.getSelectedItemPosition()==0){

                  Toast.makeText(getApplicationContext(),"Select Routing Type",Toast.LENGTH_LONG).show();


              }else {

                  Payment_Model payment_model=new Payment_Model(b_ac_name.getText().toString(),b_name.getText().toString(),
                          b_branch.getText().toString(),b_ac_number.getText().toString(),routing_no.getText().toString(),
                          type
                  );

                  databaseReference.child(FirebaseAuth.getInstance().getUid()).child("acc").setValue(payment_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                          Toast.makeText(getApplicationContext(),"Successfully Saved bank information",Toast.LENGTH_LONG).show();
                      }
                  });
              }


          }


      }
  });



    }

    void load(){


        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("acc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    empty=false;

                    Payment_Model payment_model = dataSnapshot.getValue(Payment_Model.class);

                    b_ac_name.setText(payment_model.getB_ac_name());
                    b_name.setText(payment_model.getBank_name());
                    b_branch.setText(payment_model.getBank_branch());
                    b_ac_number.setText(payment_model.getBank_acc_number());
                    routing_no.setText(payment_model.getRouting_num());

                    String a = payment_model.getType();

                    if (a.equals("Current")) {

                        sp2.setSelection(1);
                    }
                    if (a.equals("Saving")) {

                        sp2.setSelection(2);
                    }
                    if (a.equals("AWCDI")) {

                        sp2.setSelection(3);
                    }
                    if (a.equals("SND")) {

                        sp2.setSelection(4);
                    }
                    if (a.equals("STD")) {

                        sp2.setSelection(5);
                    }
                    if (a.equals("AWCA")) {

                        sp2.setSelection(6);
                    }


                    sp.setRefreshing(false);
                    test=true;
                    pd.dismiss();


                }else {

                    empty=true;

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("bk").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    number.setText(dataSnapshot.child("bKash").getValue(String.class));
                    sp.setRefreshing(false);
                    test=true;
                    pd.dismiss();
                }else {

                    if(empty) {

                        Toast.makeText(getApplicationContext(), "Please Enter a payment method", Toast.LENGTH_LONG).show();
                    }
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    void hide(){

        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
