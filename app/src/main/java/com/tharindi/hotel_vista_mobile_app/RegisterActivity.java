package com.tharindi.hotel_vista_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLException;

public class RegisterActivity extends AppCompatActivity {

    EditText edUserName, edUserEmail,edPhone,edPassword;
    Button btnRegister;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edUserEmail = findViewById(R.id.editTextEmail);
        edUserName= findViewById(R.id.editUserName);
        edPassword=findViewById(R.id.editTextPassword);
        edPhone= findViewById(R.id.editPhoneNumber);
        btnRegister= findViewById(R.id.buttonRegister);
        textView=findViewById(R.id.textViewExistingUser);


        Database database=new Database(getApplicationContext());
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail= edUserEmail.getText().toString();
                String password= edPassword.getText().toString();
                String phone= edPhone.getText().toString();
                String name=edUserName.getText().toString();
                if (userEmail.isEmpty() || password.isEmpty()||phone.isEmpty()||name.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
                }else {
                    database.register(name,userEmail,phone,password);
                    Toast.makeText(getApplicationContext(),"Registration Success",Toast.LENGTH_SHORT).show();
                    edUserEmail.setText("");
                    edUserName.setText("");
                    edPassword.setText("");
                    edPhone.setText("");
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}