package com.tharindi.hotel_vista_mobile_app.payroll;

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

public class PayrollActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    Database database;
    ArrayList<String> payroll_id,employee_name,employee_type,employee_netPay;

    CustomPayrollAdapter customPayrollAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payroll);
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
                Intent intent=new Intent(PayrollActivity.this, AddPayrollActivity.class);
                startActivity(intent);
            }
        });

        database=new Database(PayrollActivity.this);

        payroll_id=new ArrayList<>();
        employee_name=new ArrayList<>();
        employee_type=new ArrayList<>();
        employee_netPay=new ArrayList<>();

        storePayrollsInArrayList();
        customPayrollAdapter=new CustomPayrollAdapter(PayrollActivity.this,this,payroll_id,employee_name,employee_type,employee_netPay);
        recyclerView.setAdapter(customPayrollAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PayrollActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0){
            recreate();
        }
    }

    void storePayrollsInArrayList(){
        Cursor cursor= database.readAllPayroll();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Payrolls", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                payroll_id.add(cursor.getString(0));
                employee_name.add(cursor.getString(1));
                employee_type.add(cursor.getString(2));
                employee_netPay.add(cursor.getString(3));
            }
        }
    }
}