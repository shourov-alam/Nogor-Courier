package com.sh.nogorcourier.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sh.nogorcourier.Model.Post_Model;
import com.sh.nogorcourier.R;
import com.sh.nogorcourier.Adapter.ViewHolderPost;

public class User_Posts  extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager llm;
    public FirebaseRecyclerAdapter<Post_Model, ViewHolderPost> firebaseRecyclerAdapter;
    public FirebaseRecyclerOptions<Post_Model> options; // seraching in the profile ;
    DatabaseReference ref;
    Context context;
    TextView name;

    DatabaseReference mref;
    String uid;

    String a;
    ProgressDialog pd;
    boolean test;
    SwipeRefreshLayout sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__posts);
        sp=findViewById(R.id.swipe);



        pd=new ProgressDialog(User_Posts.this);
        pd.setMessage("Loading data...");


        pd.show();


        getSupportActionBar().setTitle("Parcel Details");
        recyclerView = findViewById(R.id.myList);

        ref = FirebaseDatabase.getInstance().getReference("Posts");




        llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
       // DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),llm.getOrientation());
      //  recyclerView.addItemDecoration(mDividerItemDecoration);



        if (FirebaseAuth.getInstance().getCurrentUser() !=null) {

         loadData();

        }else {


            Toast.makeText(getApplicationContext(),"User Not Found",Toast.LENGTH_LONG).show();
        }

        sp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
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




    }


    @Override
    public void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            firebaseRecyclerAdapter.startListening();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            firebaseRecyclerAdapter.startListening();
        }
    }


    public void loadData() {

      //  String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query fireBaseQusery  = ref.orderByChild("user_uid").equalTo(FirebaseAuth.getInstance().getUid()) ;
        fireBaseQusery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){

                    Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    test=true;
                    sp.setRefreshing(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        options = new FirebaseRecyclerOptions.Builder<Post_Model>().setQuery(fireBaseQusery , Post_Model.class).build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Post_Model, ViewHolderPost>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ViewHolderPost viewHolderPost, final  int i, @NonNull final Post_Model post_model) {



                // setThe data to the row
                //        String imageLink , String itemdes ,String name  ,String quatitys  ,String  category ;


                //     String name , String quantity , String mail , String returnDate , String stats ;
                viewHolderPost.setDetails(post_model.getCon_now(),post_model.getType(),post_model.getBdt(),post_model.getWeight(),post_model.getDate2(),
                        post_model.getPost_type() ,
                        post_model.getArea(),post_model.getAddress(),post_model.getD_area(),

                        post_model.getD_address(),post_model.getPhone(),post_model.getCondition_amount(),post_model.getPid(),
                        post_model.getInstruction(),post_model.getR_name(),post_model.getR_phone(),post_model.getName()






                );

                sp.setRefreshing(false);
                test=true;

                pd.dismiss();


               /* viewHolderPost.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        ref.child(getRef(i).getKey()).removeValue();

                    }
                });

                viewholderForItemList.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        LayoutInflater layoutInflater = LayoutInflater.from(User_Posts.this);

                        final View view = layoutInflater.inflate(R.layout.add_post, null);
                        final EditText title=view.findViewById(R.id.title);
                        final EditText des=view.findViewById(R.id.des);

                        title.setText(post_model.getTitle());
                        des.setText(post_model.getDes());
                        new AlertDialog.Builder(User_Posts.this)


                                .setCancelable(true)
                                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, int which) {







                                        ref.child(getRef(i).getKey()).child("title").setValue(title.getText().toString());
                                        ref.child(getRef(i).getKey()).child("des").setValue(des.getText().toString());


                                    }
                                }).setView(view).show();




                    }
                });
*/











            }





            @NonNull
            @Override
            public ViewHolderPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_post_item, parent, false);
                ViewHolderPost viewHolder = new ViewHolderPost(itemView);

                return viewHolder;
            }
        } ;

        recyclerView.setLayoutManager(llm);

        firebaseRecyclerAdapter.startListening();

        //setting adapter

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


}

