package com.engineering.season.hisabappdroid;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button incomebutton,expensebutton;
    TextView balance;
    String Balance ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.engineering.season.hisabappdroid.R.layout.activity_main);
        incomebutton = (Button)findViewById(com.engineering.season.hisabappdroid.R.id.incomebutton);
        expensebutton =(Button) findViewById(com.engineering.season.hisabappdroid.R.id.expensebutton);

        balance = (TextView)findViewById(com.engineering.season.hisabappdroid.R.id.balance);
        reloadsqldatabase();
        if (Balance!=null){
            balance.setText(Balance);
        }else{
            balance.setText("00");
        }
        incomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(),Add_Income.class));
            }
        });
        expensebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Add_Expenses.class));
            }
        });

    }
    SQLiteDatabase database;
    public void reloadsqldatabase(){
        DbHelper_Balance dbHelper_balance = new DbHelper_Balance(this);
        database = dbHelper_balance.getReadableDatabase();
        Cursor cursor = dbHelper_balance.readFromLocalDatabase(database);
        while (cursor.moveToNext()) {
           Balance =cursor.getString(cursor.getColumnIndex(DbContract.BALANCE));
        }
        cursor.close();
        dbHelper_balance.close();
    }
}
