package com.tharindi.hotel_vista_mobile_app.room;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tharindi.hotel_vista_mobile_app.Database;
import com.tharindi.hotel_vista_mobile_app.R;
import com.tharindi.hotel_vista_mobile_app.user.AddUserActivity;
import com.tharindi.hotel_vista_mobile_app.user.CustomAdapter;
import com.tharindi.hotel_vista_mobile_app.user.UserActivity;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    Database database;
    ArrayList<String> room_id,room_type,room_status,floor_number;

    CustomRoomAdapter customRoomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView=findViewById(R.id.recyclerRoomView);
        addButton=findViewById(R.id.add_room_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RoomActivity.this, AddRoomActivity.class);
                startActivity(intent);
            }
        });

        database=new Database(RoomActivity.this);

        room_id=new ArrayList<>();
        room_status=new ArrayList<>();
        room_type=new ArrayList<>();
        floor_number=new ArrayList<>();

        storeRoomsInArrayList();
        customRoomAdapter=new CustomRoomAdapter(RoomActivity.this,this,room_id,room_type,room_status,floor_number);
        recyclerView.setAdapter(customRoomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RoomActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0){
            recreate();
        }
    }

    void storeRoomsInArrayList(){
        Cursor cursor= database.readAllRoom();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Rooms", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                room_id.add(cursor.getString(0));
                room_type.add(cursor.getString(1));
                room_status.add(cursor.getString(2));
                floor_number.add(cursor.getString(3));
            }
        }
    }
}