package com.tharindi.hotel_vista_mobile_app.room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tharindi.hotel_vista_mobile_app.Database;
import com.tharindi.hotel_vista_mobile_app.R;
import com.tharindi.hotel_vista_mobile_app.user.AddUserActivity;

public class AddRoomActivity extends AppCompatActivity {

    EditText roomType,roomStatus,floorNumber;
    Button add_room_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_room);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        roomType=findViewById(R.id.editRoomType);
        roomStatus=findViewById(R.id.editRoomStatus);
        floorNumber=findViewById(R.id.editFloorNumber);
        add_room_button=findViewById(R.id.buttonAddRoom);

        Database database=new Database(AddRoomActivity.this);

        add_room_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String type = roomType.getText().toString();
                String status = roomStatus.getText().toString();
                String floor = floorNumber.getText().toString();

                if (!type.isEmpty() && !status.isEmpty() && !floor.isEmpty()) {
                    database.addRoom(type, status, floor);
                    //Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();


                    // Clear the fields
                    roomType.setText("");
                    roomStatus.setText("");
                    floorNumber.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}