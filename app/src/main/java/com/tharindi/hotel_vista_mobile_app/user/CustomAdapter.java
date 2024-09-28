package com.tharindi.hotel_vista_mobile_app.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tharindi.hotel_vista_mobile_app.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


     Context context;
     ArrayList<String> user_id,user_name,user_phone,user_address;
     Activity activity;

    CustomAdapter(Activity activity,Context context,ArrayList<String> user_id,ArrayList<String> user_name,ArrayList<String> user_phone,ArrayList<String> user_address){
        this.activity=activity;
        this.context=context;
        this.user_id=user_id;
        this.user_name=user_name;
        this.user_phone=user_phone;
        this.user_address=user_address;
    }



    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.user_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {


        holder.user_id.setText(String.valueOf(user_id.get(position)));
        holder.user_name.setText(String.valueOf(user_name.get(position)));
        holder.user_phone.setText(String.valueOf(user_phone.get(position)));
        holder.user_address.setText(String.valueOf(user_address.get(position)));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition(); // Get the current position
                if (currentPosition != RecyclerView.NO_POSITION) { // Ensure the position is valid
                    Intent intent = new Intent(context, UpdateUserActivity.class);
                    intent.putExtra("user_id", user_id.get(currentPosition));
                    intent.putExtra("user_name", user_name.get(currentPosition));
                    intent.putExtra("user_phone", user_phone.get(currentPosition));
                    intent.putExtra("user_address", user_address.get(currentPosition));
                    activity.startActivityForResult(intent,1);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id,user_name,user_phone,user_address;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id=itemView.findViewById(R.id.user_id);
            user_name=itemView.findViewById(R.id.user_name);
            user_phone=itemView.findViewById(R.id.user_phone);
            user_address=itemView.findViewById(R.id.user_address);
            layout=itemView.findViewById(R.id.userMainLayout);
        }
    }
}
