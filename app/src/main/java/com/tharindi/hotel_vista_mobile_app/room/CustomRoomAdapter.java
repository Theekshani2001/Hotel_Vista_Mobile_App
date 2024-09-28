package com.tharindi.hotel_vista_mobile_app.room;

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
import com.tharindi.hotel_vista_mobile_app.user.UpdateUserActivity;

import java.util.ArrayList;

public class CustomRoomAdapter extends RecyclerView.Adapter<CustomRoomAdapter.MyViewHolder> {



    Context context;
    ArrayList<String> room_id,room_type,room_status,floor_number;
    Activity activity;

    CustomRoomAdapter(Activity activity,Context context,ArrayList<String> room_id,ArrayList<String> room_type,ArrayList<String> room_status,ArrayList<String> floor_number){
        this.activity=activity;
        this.context=context;
        this.room_id=room_id;
        this.room_type=room_type;
        this.room_status=room_status;
        this.floor_number=floor_number;
    }



    @NonNull
    @Override
    public CustomRoomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.room_row,parent,false);
        return new CustomRoomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRoomAdapter.MyViewHolder holder, int position) {


        holder.room_id.setText(String.valueOf(room_id.get(position)));
        holder.room_type.setText(String.valueOf(room_type.get(position)));
        holder.room_status.setText(String.valueOf(room_status.get(position)));
        holder.floor_number.setText(String.valueOf(floor_number.get(position)));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition(); // Get the current position
                if (currentPosition != RecyclerView.NO_POSITION) { // Ensure the position is valid
                    Intent intent = new Intent(context, UpdateRoomActivity.class);
                    intent.putExtra("room_id", room_id.get(currentPosition));
                    intent.putExtra("room_type", room_type.get(currentPosition));
                    intent.putExtra("room_status", room_status.get(currentPosition));
                    intent.putExtra("floor_number", floor_number.get(currentPosition));
                    activity.startActivityForResult(intent,1);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return room_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView room_id,room_type,room_status,floor_number;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            room_id=itemView.findViewById(R.id.room_id);
            room_type=itemView.findViewById(R.id.room_type);
            room_status=itemView.findViewById(R.id.room_status);
            floor_number=itemView.findViewById(R.id.floor_number);
            layout=itemView.findViewById(R.id.roomMainLayout);
        }
    }
}
