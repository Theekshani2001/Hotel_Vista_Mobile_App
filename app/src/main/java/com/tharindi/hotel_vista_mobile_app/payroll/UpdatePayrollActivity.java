package com.tharindi.hotel_vista_mobile_app.payroll;

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

public class UpdatePayrollActivity extends AppCompatActivity {

    EditText employeeName,employeeType,employeeNetPay;
    Button updateButton,deleteButton;

    String id, name, type, netPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_payroll);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        employeeName = findViewById(R.id.updateEmployeeName);
        employeeType = findViewById(R.id.updateEmployeeType);
        employeeNetPay = findViewById(R.id.updateNetPay);
        updateButton = findViewById(R.id.buttonUpdatePayroll);
        deleteButton=findViewById(R.id.buttonDeletePayroll);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = employeeName.getText().toString();
                type = employeeType.getText().toString();
                netPay = employeeNetPay.getText().toString();


                Database db = new Database(UpdatePayrollActivity.this);
                db.updatePayroll(id, name, type, netPay);
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
        if (getIntent().hasExtra("payroll_id") && getIntent().hasExtra("employee_name") &&
                getIntent().hasExtra("employee_type") && getIntent().hasExtra("employee_netPay")) {

            id = getIntent().getStringExtra("payroll_id");
            name = getIntent().getStringExtra("employee_name");
            type = getIntent().getStringExtra("employee_type");
            netPay = getIntent().getStringExtra("employee_netPay");

            // Set the data in the EditText fields
            employeeName.setText(name);
            employeeType.setText(type);
            employeeNetPay.setText(netPay);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void clearInputFields() {
        employeeName.setText("");
        employeeType.setText("");
        employeeNetPay.setText("");
    }

    void conformDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Delete Payroll "+id+" ?");
        builder.setMessage("Are you sure you want to delete payroll "+id+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database db = new Database(UpdatePayrollActivity.this);
                db.deleteOnePayroll(id);
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