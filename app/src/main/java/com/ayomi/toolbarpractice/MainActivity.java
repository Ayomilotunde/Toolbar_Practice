package com.ayomi.toolbarpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper peopleDB;
    Button btn_submit;
    EditText new_name;
    EditText new_date;
    EditText new_email;
    EditText username;

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

        AddData();

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
                    Toast.makeText(MainActivity.this, "Something went Wrong!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
