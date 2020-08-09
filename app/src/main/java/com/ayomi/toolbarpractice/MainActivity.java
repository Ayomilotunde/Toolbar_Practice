package com.ayomi.toolbarpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper peopleDB;
    Button btn_submit, btn_view, btn_update, btn_delete;
    EditText new_name;
    EditText new_date;
    EditText new_email;
    EditText username;
    EditText edt_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleDB = new DataBaseHelper(this);

        new_name = findViewById(R.id.new_name);
        new_date = findViewById(R.id.new_date);
        new_email = findViewById(R.id.new_email);
        username = findViewById(R.id.edt_username);
        btn_submit = findViewById(R.id.btn_submit);
        btn_view = findViewById(R.id.btn_view);
        btn_update = findViewById(R.id.btn_update);
        edt_update = findViewById(R.id.edt_update);
        btn_delete = findViewById(R.id.btn_delete);

        AddData();
        ViewData();
        UpdateData();
        DeleteData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {
            Toast.makeText(getApplicationContext(), "You click on Share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.commit) {
            Toast.makeText(getApplicationContext(), "You click on commit", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.settings) {
            Toast.makeText(getApplicationContext(), "You click on settings", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "You click on About", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.search) {
            Toast.makeText(getApplicationContext(), "You click on Search", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void AddData(){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = new_name.getText().toString();
                String email = new_email.getText().toString();
                String date = new_date.getText().toString();
                //String username = edt_username.getText().toString();

                //String username;

                boolean insertData = peopleDB.addData(name,email,date,username);

                if (insertData == true){
                    Toast.makeText(MainActivity.this, "Data Successfully Inserted!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Something went Wrong :(.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // to view data

    public void ViewData(){
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = peopleDB.showData();


                if(data.getCount() == 0) {

                    dispplay("Error", "No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(data.moveToNext()) {
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("Name: " + data.getString(1) + "\n");
                    buffer.append("E-mail: " + data.getString(2) + "\n");
                    buffer.append("Date Of Birth: " + data.getString(3) + "\n");
                    dispplay("All Stored Data", buffer.toString());
                }

            }
        });
    }

    public void dispplay(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = edt_update.getText().toString().length();
                if (temp > 0){
                    boolean update = peopleDB.updateData(edt_update.getText().toString(),new_name.getText().toString(),new_email.getText().toString(),new_date.getText().toString());
                    if (update == true) {
                        Toast.makeText(MainActivity.this, "Successfully Updated Data!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Something went wrong :(.",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "You must enter an ID to Update",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
public void DeleteData(){
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = edt_update.getText().toString().length();

                if (temp > 0) {
                    Integer deleteRow = peopleDB.deleteData(edt_update.getText().toString());
                    if (deleteRow > 0) {
                        Toast.makeText(MainActivity.this, "Successfully Deleted Data",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Something went wrong.",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "You must enter an ID to Delete.",Toast.LENGTH_LONG).show();
                }
            }
        });
}
}
