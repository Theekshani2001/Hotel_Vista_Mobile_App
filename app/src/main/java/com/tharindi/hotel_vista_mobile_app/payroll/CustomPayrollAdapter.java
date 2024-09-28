package com.tharindi.hotel_vista_mobile_app.payroll;

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

public class CustomPayrollAdapter extends RecyclerView.Adapter<CustomPayrollAdapter.MyViewHolder>{


    Context context;
    ArrayList<String> payroll_id,employee_name,employee_netPay,employee_type;
    Activity activity;

    CustomPayrollAdapter(Activity activity,Context context,ArrayList<String> payroll_id,ArrayList<String> employee_name,ArrayList<String> employee_netPay,ArrayList<String> employee_type){
        this.activity=activity;
        this.context=context;
        this.payroll_id=payroll_id;
        this.employee_name=employee_name;
        this.employee_netPay=employee_netPay;
        this.employee_type=employee_type;
    }


    @NonNull
    @Override
    public CustomPayrollAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.user_row,parent,false);
        return new CustomPayrollAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomPayrollAdapter.MyViewHolder holder, int position) {


        holder.payroll_id.setText(String.valueOf(payroll_id.get(position)));
        holder.employee_name.setText(String.valueOf(employee_name.get(position)));
        holder.employee_netPay.setText(String.valueOf(employee_netPay.get(position)));
        holder.employee_type.setText(String.valueOf(employee_type.get(position)));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition(); // Get the current position
                if (currentPosition != RecyclerView.NO_POSITION) { // Ensure the position is valid
                    Intent intent = new Intent(context, UpdatePayrollActivity.class);
                    intent.putExtra("payroll_id", payroll_id.get(currentPosition));
                    intent.putExtra("employee_name", employee_name.get(currentPosition));
                    intent.putExtra("employee_netPay", employee_netPay.get(currentPosition));
                    intent.putExtra("employee_type", employee_type.get(currentPosition));
                    activity.startActivityForResult(intent,1);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return payroll_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView payroll_id,employee_name,employee_netPay,employee_type;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            payroll_id=itemView.findViewById(R.id.payroll_id);
            employee_name=itemView.findViewById(R.id.employee_name);
            employee_netPay=itemView.findViewById(R.id.employee_netPay);
            employee_type=itemView.findViewById(R.id.employee_type);
            layout=itemView.findViewById(R.id.payrollMainLayout);
        }
    }
}
