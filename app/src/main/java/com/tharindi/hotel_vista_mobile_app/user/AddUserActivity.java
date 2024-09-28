package com.tharindi.hotel_vista_mobile_app.user;

//import android.annotation.SuppressLint;
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

public class AddUserActivity extends AppCompatActivity {

    EditText userName,userPhone,userAddress;
    Button add_user_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userName=findViewById(R.id.editUserName);
        userPhone=findViewById(R.id.editPhoneNumber);
        userAddress=findViewById(R.id.editAddress);
        add_user_button=findViewById(R.id.buttonAdd);

        Database database=new Database(AddUserActivity.this);

        add_user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = userName.getText().toString();
                String phone = userPhone.getText().toString();
                String address = userAddress.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {
                    database.addUser(name, phone, address);
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();


                    // Clear the fields
                    userName.setText("");
                    userPhone.setText("");
                    userAddress.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}