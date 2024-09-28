

package com.tharindi.hotel_vista_mobile_app.user;

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

public class UpdateUserActivity extends AppCompatActivity {

    EditText userName, userPhone, userAddress;
    Button updateButton,deleteButton;

    String id, name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userName = findViewById(R.id.updateUserName);
        userPhone = findViewById(R.id.updatePhoneNumber);
        userAddress = findViewById(R.id.updateAddress);
        updateButton = findViewById(R.id.buttonUpdate);
        deleteButton=findViewById(R.id.buttonDelete);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the updated values from the EditText fields
                name = userName.getText().toString();
                phone = userPhone.getText().toString();
                address = userAddress.getText().toString();

                // Update the user in the database
                Database db = new Database(UpdateUserActivity.this);
                db.updateUser(id, name, phone, address);
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
        if (getIntent().hasExtra("user_id") && getIntent().hasExtra("user_name") &&
                getIntent().hasExtra("user_phone") && getIntent().hasExtra("user_address")) {

            id = getIntent().getStringExtra("user_id");
            name = getIntent().getStringExtra("user_name");
            phone = getIntent().getStringExtra("user_phone");
            address = getIntent().getStringExtra("user_address");

            // Set the data in the EditText fields
            userName.setText(name);
            userPhone.setText(phone);
            userAddress.setText(address);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void clearInputFields() {
        userName.setText("");
        userPhone.setText("");
        userAddress.setText("");
    }

    void conformDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Delete User "+name+" ?");
        builder.setMessage("Are you sure you want to delete use "+name+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database db = new Database(UpdateUserActivity.this);
                db.deleteOneUser(id);
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
