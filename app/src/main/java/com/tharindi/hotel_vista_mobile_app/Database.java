package com.tharindi.hotel_vista_mobile_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="hotel.db";
    private static final int DATABASE_VERSION=1;


    public Database(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qur1 = "CREATE TABLE employee (employeeName TEXT, employeeEmail TEXT, employeePhone TEXT, password TEXT)";
        String qur2 = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, address TEXT)";
        String qur3 = "CREATE TABLE room (id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, status TEXT, floor TEXT)";
        String qur4 = "CREATE TABLE payroll (id INTEGER PRIMARY KEY AUTOINCREMENT, employeeName TEXT, employeeType TEXT, employeeNetPay TEXT)";

        sqLiteDatabase.execSQL(qur1);
        sqLiteDatabase.execSQL(qur2);
        sqLiteDatabase.execSQL(qur3);
        sqLiteDatabase.execSQL(qur4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Drop the old tables if they exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS employee");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS room");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS payroll");

        // Recreate the tables with the new schema
        onCreate(sqLiteDatabase);

    }

    //---------- Employee---------------------------------------------

    public void register(String name,String email,String phone,String password){
        ContentValues contentValues=new ContentValues();
        contentValues.put("employeeName",name);
        contentValues.put("employeeEmail",email);
        contentValues.put("employeePhone",phone);
        contentValues.put("password",password);

        SQLiteDatabase database=getWritableDatabase();
        long result = database.insert("employee", null, contentValues);
        if (result==-1){
            Toast.makeText(context,"Failed ....",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Registration Success",Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    public int login(String email,String password){
        int result=0;
        String[] str =new String[2];
        str[0]=email;
        str[1]=password;
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from employee where employeeEmail=? and password=?",str);
        if (cursor.moveToFirst()){
            result=1;
        }
        return result;
    }

    //---------- Employee---------------------------------------------

    //---------- User---------------------------------------------

    public void addUser(String name,String phone,String address){
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("address",address);

        SQLiteDatabase database=this.getWritableDatabase();
        long result = database.insert("user", null, contentValues);
        if (result==-1){
            Toast.makeText(context,"Failed to add data",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    public Cursor readAllUser(){
        String query="select * from user";
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if (db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }


    public void updateUser(String id,String name,String phone,String address){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("address",address);

        long result= db.update("user",cv,"id=?",new String[]{id});

        if (result==-1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneUser(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result= db.delete("user","id=?",new String[]{id});
        if (result==-1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    //---------- User---------------------------------------------
    //----------- Room--------------------------------------------

    public void addRoom(String type,String status,String floor){
        ContentValues contentValues=new ContentValues();
        contentValues.put("type",type);
        contentValues.put("status",status);
        contentValues.put("floor",floor);

        SQLiteDatabase database=this.getWritableDatabase();
        long result = database.insert("room", null, contentValues);
        if (result==-1){
            Toast.makeText(context,"Failed to add data",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added.. successfully",Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    public Cursor readAllRoom(){
        String query="select * from room";
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if (db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }


    public void updateRoom(String id,String type,String status,String floor){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("type",type);
        contentValues.put("status",status);
        contentValues.put("floor",floor);

        long result= db.update("room",contentValues,"id=?",new String[]{id});

        if (result==-1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRoom(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result= db.delete("room","id=?",new String[]{id});
        if (result==-1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //----------- Room--------------------------------------------
    //----------- Payroll--------------------------------------------

//    public void addPayroll(String employeeName,String employeeType,String employeeNetPay){
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("employeeName",employeeName);
//        contentValues.put("employeeType",employeeType);
//        contentValues.put("employeeNetPay",employeeNetPay);
//
//        SQLiteDatabase database=this.getWritableDatabase();
//        long result = database.insert("payroll", null, contentValues);
//        if (result==-1){
//            Toast.makeText(context,"Failed to add data",Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
//        }
//        database.close();
//    }
//
//    public Cursor readAllPayroll(){
//        String query="select * from payroll";
//        SQLiteDatabase db=this.getReadableDatabase();
//
//        Cursor cursor=null;
//        if (db!=null){
//            cursor=db.rawQuery(query,null);
//        }
//        return cursor;
//    }
//
//
//    public void updatePayroll(String id,String employeeName,String employeeType,String employeeNetPay){
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues cv=new ContentValues();
//        cv.put("employeeName",employeeName);
//        cv.put("employeeType",employeeType);
//        cv.put("employeeNetPay",employeeNetPay);
//
//        long result= db.update("payroll",cv,"id=?",new String[]{id});
//
//        if (result==-1){
//            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void deleteOnePayroll(String id){
//        SQLiteDatabase db=this.getWritableDatabase();
//        long result= db.delete("payroll","id=?",new String[]{id});
//        if (result==-1){
//            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void addPayroll(String employeeName, String employeeType, String employeeNetPay) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("employeeName", employeeName);
        contentValues.put("employeeType", employeeType);
        contentValues.put("employeeNetPay", employeeNetPay);

        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.insert("payroll", null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Failed to Add Payroll", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Payroll Added Successfully", Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    public Cursor readAllPayroll() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM payroll", null);
    }

    public void updatePayroll(String id, String employeeName, String employeeType, String employeeNetPay) {
        ContentValues cv = new ContentValues();
        cv.put("employeeName", employeeName);
        cv.put("employeeType", employeeType);
        cv.put("employeeNetPay", employeeNetPay);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.update("payroll", cv, "id = ?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Update Payroll", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Payroll Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOnePayroll(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("payroll", "id = ?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete Payroll", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Payroll Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //----------- Payroll--------------------------------------------
}
