package com.tharindi.hotel_vista_mobile_app.payroll;

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

public class AddPayrollActivity extends AppCompatActivity {

    EditText employeeName,employeeType,employeeNetPay;
    Button add_payroll_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_payroll);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        employeeName=findViewById(R.id.editEmployeeName);
        employeeType=findViewById(R.id.editEmployeeType);
        employeeNetPay=findViewById(R.id.editNetPay);
        add_payroll_button=findViewById(R.id.buttonAddPayroll);

        Database database=new Database(AddPayrollActivity.this);

        add_payroll_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = employeeName.getText().toString();
                String type = employeeType.getText().toString();
                String netPay = employeeNetPay.getText().toString();

                if (!name.isEmpty() && !type.isEmpty() && !netPay.isEmpty()) {
                    database.addPayroll(name, type, netPay);
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();


                    // Clear the fields
                    employeeName.setText("");
                    employeeType.setText("");
                    employeeNetPay.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}