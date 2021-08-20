package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {
    Context context;

    public myAdapter(Context context, ArrayList<Charities> charitiesArrayList) {
        this.context = context;
        this.charitiesArrayList = charitiesArrayList;
    }

    ArrayList<Charities> charitiesArrayList;
    @NonNull

    @Override
    public myAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listcharities,parent,false);




        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  myAdapter.MyViewHolder holder, int position) {

        Charities charity= charitiesArrayList.get(position);

        holder.name.setText(charity.Name);
        holder.description.setText(charity.Description);
        holder.email.setText(charity.Email);
        holder.type.setText(charity.Type);
        holder.payment.setText(charity.Payment);

    }

    @Override
    public int getItemCount() {
        return charitiesArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,description,type,payment,email;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Nameslot);
            description=itemView.findViewById(R.id.Descriptionslot);
            type=itemView.findViewById(R.id.typeslot);
            payment=itemView.findViewById(R.id.paymentslot);
            email=itemView.findViewById(R.id.emailslot);
        }
    }
}
