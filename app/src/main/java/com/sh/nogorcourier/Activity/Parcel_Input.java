package com.sh.nogorcourier.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sh.nogorcourier.Model.Post_Model;
import com.sh.nogorcourier.R;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class Parcel_Input extends AppCompatActivity {

    EditText r_name,r_phone,name,phone,area,address,d_area,d_address,instruction;
    Spinner weight;
    RadioButton liq,fri;
    Button enter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference1,databaseReference3;
    String uuid;
    Spinner sp;
    TextView bdt;
    String a;
    String b;
    String c;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel__input);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Posts");
        databaseReference1=firebaseDatabase.getReference("Pid");
        databaseReference3= firebaseDatabase.getReference("User_Profile");

        uuid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        weight=findViewById(R.id.spinner);
        bdt=findViewById(R.id.bdt1);
        r_name=findViewById(R.id.nameETr);
        r_phone=findViewById(R.id.mobileETr);
        name=findViewById(R.id.nameET);
        phone=findViewById(R.id.mobileET);
        area=findViewById(R.id.areaET);
        address=findViewById(R.id.addressET);
        d_area=findViewById(R.id.areadET);
        d_address=findViewById(R.id.addressdET);
        weight=findViewById(R.id.spinner);
        fri=findViewById(R.id.rd);
        liq=findViewById(R.id.rd1);
        instruction=findViewById(R.id.instructionET);
        enter=findViewById(R.id.entryBT);
        pd=new ProgressDialog(Parcel_Input.this);
        pd.setMessage("Order Posting");



        getSupportActionBar().setTitle("Parcel Delivery");


        databaseReference3.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists()) {

                    User_Profile user_profile = dataSnapshot.getValue(User_Profile.class);


                    if(name.getText().toString().isEmpty()){

                        name.setText(user_profile.getName());
                    }
                    if(phone.getText().toString().isEmpty()){

                        phone.setText(user_profile.getPhone());
                    }
                    if(area.getText().toString().isEmpty()){

                        area.setText(user_profile.getArea());
                    }
                    if(address.getText().toString().isEmpty()){

                        address.setText(user_profile.getAddress());
                    }

                }else {
                    // Toast.makeText(getApplicationContext(),"Tap the edit button & create Profile",Toast.LENGTH_LONG).show();


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









        weight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                if(position==0){

                    bdt.setText("Cost: 0 BDT");

                }
                if(position==1){

                    bdt.setText("Cost: 80 BDT");

                }
                if(position==2){

                    bdt.setText("Cost: 100 BDT");

                }
                if(position==3){

                    bdt.setText("Cost: 120 BDT");

                }
                if(position==4){

                    bdt.setText("Cost: 140 BDT");

                }

                if(position==5){

                    bdt.setText("Cost: 160 BDT");

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



                if(name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || area.getText().toString().isEmpty()

                        ||  address.getText().toString().isEmpty() || r_name.getText().toString().isEmpty() || r_phone.getText().toString().isEmpty()
                        || d_area.getText().toString().isEmpty() || d_address.getText().toString().isEmpty() ||
                        area.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Fill up all the data properly",Toast.LENGTH_LONG).show();

                }else {


                    if(weight.getSelectedItemPosition() == 0){

                        Toast.makeText(getApplicationContext(),"Select Weight",Toast.LENGTH_LONG).show();


                    }else {


                        pd.show();

                        enter.setVisibility(View.GONE);

                        save_data();
                    }


                }










            }
        });




    }



    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999

        Random rnd = new Random();
        int number1 = rnd.nextInt(99999);
        int number2 = rnd.nextInt(99999);
        int number=number1+number2;


        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }


    public void save_data() {



        final String pids = getRandomNumberString();



        final long time= System.currentTimeMillis();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        final String formattedDate = df.format(time);


        Query query = databaseReference1.orderByChild("pi").equalTo(pids);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {


                   save_data();
                } else {



                    databaseReference1.child(databaseReference.push().getKey()).child("pi").setValue(pids);

                    String u = databaseReference.push().getKey();


                    if(weight.getSelectedItemPosition() ==1){

                        a="80";
                        b="1";
                    }
                    if(weight.getSelectedItemPosition() ==2){

                        a="100";
                        b="2";
                    }
                    if(weight.getSelectedItemPosition() ==3){

                        a="120";
                        b="3";
                    }
                    if(weight.getSelectedItemPosition() ==4){

                        a="140";
                        b="4";
                    }
                    if(weight.getSelectedItemPosition() ==5){

                        a="160";
                        b="5";
                    }

                    if(fri.isChecked()){

                        c="Fraglie";
                    }

                    if(liq.isChecked()){

                        c="Liquid";
                    }








                    Post_Model post_model = new Post_Model(u,"nulls","null","null","Agent will come to pickup",a,r_name.getText().toString(),r_phone.getText().toString(),
                            formattedDate,"nulls",pids,"null","null","Parcel Delivery",uuid,String.valueOf(time),
                            name.getText().toString(),phone.getText().toString(),area.getText().toString(),
                            address.getText().toString(),d_area.getText().toString(),d_address.getText().toString(),
                            instruction.getText().toString(),c,b,"null","null","null","0");

                    databaseReference.child(u).setValue(post_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_LONG).show();
                            enter.setVisibility(View.VISIBLE);
                            pd.dismiss();
                        }
                    });


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
