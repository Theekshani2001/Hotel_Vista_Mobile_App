package com.tharindi.hotel_vista_mobile_app.room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tharindi.hotel_vista_mobile_app.Database;
import com.tharindi.hotel_vista_mobile_app.R;
import com.tharindi.hotel_vista_mobile_app.user.UpdateUserActivity;

public class UpdateRoomActivity extends AppCompatActivity {

    EditText roomType,roomStatus,floorNumber;
    Button updateButton,deleteButton;

    String id, type,status,floor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_room);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        roomType = findViewById(R.id.updateRoomType);
        roomStatus = findViewById(R.id.updateRoomStatus);
        floorNumber = findViewById(R.id.updateFloorNumber);
        updateButton = findViewById(R.id.buttonUpdateRoom);
        deleteButton=findViewById(R.id.buttonDeleteRoom);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the updated values from the EditText fields
                type = roomType.getText().toString();
                status = roomStatus.getText().toString();
                floor = floorNumber.getText().toString();

                // Update the user in the database
                Database db = new Database(UpdateRoomActivity.this);
                db.updateRoom(id, type, status, floor);
                clearInputFields();


            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conformDialog();
                clearInputFields();
            }
        });


    }


    void getAndSetIntentData() {
        if (getIntent().hasExtra("room_id") && getIntent().hasExtra("room_type") &&
                getIntent().hasExtra("room_status") && getIntent().hasExtra("floor_number")) {

            id = getIntent().getStringExtra("room_id");
            type = getIntent().getStringExtra("room_type");
            status = getIntent().getStringExtra("room_status");
            floor = getIntent().getStringExtra("floor_number");

            // Set the data in the EditText fields
            roomType.setText(type);
            roomStatus.setText(status);
            floorNumber.setText(floor);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void clearInputFields() {
        roomType.setText("");
        roomStatus.setText("");
        floorNumber.setText("");
    }

    void conformDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Delete Room "+id+" ?");
        builder.setMessage("Are you sure you want to delete room "+id+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database db = new Database(UpdateRoomActivity.this);
                db.deleteOneRoom(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }




}