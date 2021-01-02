package com.sh.nogorcourier.Adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.sh.nogorcourier.R;

public class ViewHolderPost extends RecyclerView.ViewHolder {
    View mview ;
    TextView nows,weights,date2,post_type,area,address,d_area,d_address,status,phone,cod_amount,post_id,instruction,rname,rphone,cost,name;
    ImageButton delete,edit;
    DatabaseReference ref;


    public  ViewHolderPost(@NonNull View itemView) {
        super(itemView);

        date2=itemView.findViewById(R.id.date);
        name=itemView.findViewById(R.id.m_name);
        nows=itemView.findViewById(R.id.now);



        post_id=itemView.findViewById(R.id.post_id);




        rname=itemView.findViewById(R.id.rname);
        rphone=itemView.findViewById(R.id.rphone);
        instruction=itemView.findViewById(R.id.instruction);
        cost=itemView.findViewById(R.id.cost);

        weights=itemView.findViewById(R.id.weight);



        post_type=itemView.findViewById(R.id.post_type);
        area=itemView.findViewById(R.id.from);
        address=itemView.findViewById(R.id.to);
        d_area=itemView.findViewById(R.id.fromarea);
        d_address=itemView.findViewById(R.id.toarea);
        status=itemView.findViewById(R.id.status);
        phone=itemView.findViewById(R.id.phone);
        cod_amount=itemView.findViewById(R.id.condition);





    }
    public  void setDetails(String no,String type,String bdt,String weight,String date,String product_type, String areas,String addresss,String d_areas,String d_addresss,
             String phones, String cod_amounts,String pid,String ins,String nam,String phn,String nm ) {

        // final String r=refa;


        name.setText(nm);
        post_id.setText("PID: "+pid);
        post_type.setText(product_type);
        date2.setText(date);
        area.setText(areas);
        address.setText(addresss);
        d_area.setText(d_areas);
        d_address.setText(d_addresss);
        status.setText(type);
        phone.setText(phones);
        rname.setText(nam);
        rphone.setText(phn);


        weights.setText("Weight: "+weight+"kg");
        nows.setText(no);
        cost.setText("Cost: "+bdt+" BDT");




        if(product_type.equals("Parcel Delivery")){
            cod_amount.setVisibility(View.GONE);





        }
        if(product_type.equals("Cash on Delivery")){
            cod_amount.setVisibility(View.VISIBLE);



            cod_amount.setText("Condition: "+cod_amounts+" BDT");
        }

        if(ins.equals("")){

            instruction.setVisibility(View.GONE);

        }
        if(!ins.equals("")){

            instruction.setVisibility(View.VISIBLE);
            instruction.setText(ins);
        }










    }




}
