package com.tharindi.hotel_vista_mobile_app.user;

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

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    Database database;
    ArrayList<String> user_id,user_name,user_phone,user_address;

    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView=findViewById(R.id.recyclerView);
        addButton=findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        database=new Database(UserActivity.this);

        user_id=new ArrayList<>();
        user_name=new ArrayList<>();
        user_phone=new ArrayList<>();
        user_address=new ArrayList<>();

        storeUsersInArrayList();
        customAdapter=new CustomAdapter(UserActivity.this,this,user_id,user_name,user_phone,user_address);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0){
            recreate();
        }
    }

    void storeUsersInArrayList(){
        Cursor cursor= database.readAllUser();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Users", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_phone.add(cursor.getString(2));
                user_address.add(cursor.getString(3));
            }
        }
    }
}