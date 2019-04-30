package com.engineering.season.hisabappdroid;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Expenses  extends Activity {
    EditText edittext;
    Button savebutton;
    public static String Balance;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.engineering.season.hisabappdroid.R.layout.addexpenseslayout);
        edittext = (EditText)findViewById(com.engineering.season.hisabappdroid.R.id.edittextlocationname);
        savebutton =(Button)findViewById(com.engineering.season.hisabappdroid.R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittext.getText().length()!=0){
                    Editable income = edittext.getText();
                    String expenses = income.toString();
                    //save the balance in sqlite
                    //store_in_sqlitedb(Balance);
                    reloadsqldatabase(expenses);

                }
            }
        });

    }


    SQLiteDatabase database;
    public void reloadsqldatabase(String expense){
        DbHelper_Balance dbHelper_balance = new DbHelper_Balance(this);
        database = dbHelper_balance.getReadableDatabase();
        Cursor cursor = dbHelper_balance.readFromLocalDatabase(database);
        while (cursor.moveToNext()) {
            String sqlitereceivedamount =cursor.getString(cursor.getColumnIndex(DbContract.BALANCE));
            Double amount = Double.parseDouble(sqlitereceivedamount);
            Double expenses = Double.parseDouble(expense);
            Double bal = amount - expenses;
            Balance = bal.toString();
        }
        cursor.close();
        dbHelper_balance.close();
        store_in_sqlitedb(Balance);
    }
    //save the data
    public void store_in_sqlitedb(String balance){
        DbHelper_Balance dbHelper_geofence = new DbHelper_Balance(this);
        database = dbHelper_geofence.getWritableDatabase();
        dbHelper_geofence.saveToLocalDatabase(balance, database);
        dbHelper_geofence.close();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}
