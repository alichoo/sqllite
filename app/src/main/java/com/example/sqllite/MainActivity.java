package com.example.sqllite;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private UserCursorAdapter userCursorAdapter;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
             databaseHelper = new DatabaseHelper(this);
        }catch (Exception ex){
            Log.e("Exceptio",ex.getLocalizedMessage()) ;
        }
            EditText name=(EditText) findViewById(R.id.nom);

            EditText age =(EditText) findViewById(R.id.age);
            Button bt= (Button) findViewById(R.id.add);
        recyclerView = findViewById(R.id.userlist);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));

         // Charger les données
         Cursor cursor = databaseHelper.getAllUsers();
         userCursorAdapter = new UserCursorAdapter(cursor);
         recyclerView.setAdapter(userCursorAdapter);
         bt.setOnClickListener(
                    view -> {
                        int aget= Integer.parseInt(age.getText().toString());

                        String   namet=name.getText().toString();
                        boolean isInserted=    databaseHelper.insertUser(namet, aget);
                        loadData();
                    }
            );

         loadData();

         // Charger les données


    }
    private void loadData() {
        Cursor cursor = databaseHelper.getAllUsers();
        userCursorAdapter=new UserCursorAdapter(cursor);
        recyclerView.setAdapter(userCursorAdapter);
    }
}