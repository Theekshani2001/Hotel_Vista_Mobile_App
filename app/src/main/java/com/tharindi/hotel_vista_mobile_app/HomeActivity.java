package com.tharindi.hotel_vista_mobile_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tharindi.hotel_vista_mobile_app.buffet.BuffetTicketActivity;
import com.tharindi.hotel_vista_mobile_app.cleaning.CleaningActivity;
import com.tharindi.hotel_vista_mobile_app.payroll.PayrollActivity;
import com.tharindi.hotel_vista_mobile_app.reservation.ReservationActivity;
import com.tharindi.hotel_vista_mobile_app.room.RoomActivity;
import com.tharindi.hotel_vista_mobile_app.user.UserActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String employeeName = sharedPreferences.getString("employeeName","").toString();
        Toast.makeText(getApplicationContext(),"welcome"+employeeName,Toast.LENGTH_SHORT).show();

        CardView userView=findViewById(R.id.cardUser);
        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, UserActivity.class));
            }
        });

        CardView roomView=findViewById(R.id.cardRoom);
        roomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, RoomActivity.class));
            }
        });

        CardView buffetView=findViewById(R.id.cardBuffetTicket);
        buffetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BuffetTicketActivity.class));
            }
        });

        CardView cleaningView=findViewById(R.id.cardRoomCleaning);
        cleaningView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CleaningActivity.class));
            }
        });

        CardView payrollView=findViewById(R.id.cardPayroll);
        payrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PayrollActivity.class));
            }
        });

        CardView reservationView=findViewById(R.id.cardReservation);
        reservationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ReservationActivity.class));
            }
        });

    }
}