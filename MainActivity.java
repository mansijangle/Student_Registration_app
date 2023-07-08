package com.example.example_database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b1, b2, b3, b4;
    database g;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);

        b1 = findViewById(R.id.insert);
        b2 = findViewById(R.id.delete);
        b3 = findViewById(R.id.update);
        b4 = findViewById(R.id.submit);

         g = new database(this);
        // g.getReadableDatabase();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString();
                String username = ed2.getText().toString();
                String password = ed3.getText().toString();
                if(name.equals("") || username.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter the value", Toast.LENGTH_SHORT).show();
                }
                else{
                       boolean i=  g.insert_date(name, username, password);
                        if(i==true){
                            Toast.makeText(MainActivity.this, "Data inserted Succefully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data not inserted Succefully", Toast.LENGTH_SHORT).show();
                        }
                }
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t = g.getInfo();
                if(t.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Data found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while(t.moveToNext()){
                    buffer.append("Name :"+t.getString(1)+"\n");
                    buffer.append("Username :"+t.getString(2)+"\n");
                    buffer.append("Password :"+t.getString(3)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Sinup data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = ed2.getText().toString();
                Boolean e = g.delete_data(username1);
                if(e==true){
                    Toast.makeText(MainActivity.this, "user is deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "user cannot deleted ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString();
                String username = ed2.getText().toString();
                String password = ed3.getText().toString();
                boolean e  = g.update_data(name, username,password);
                if(e==true){
                    Toast.makeText(MainActivity.this, "Data is updataed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}