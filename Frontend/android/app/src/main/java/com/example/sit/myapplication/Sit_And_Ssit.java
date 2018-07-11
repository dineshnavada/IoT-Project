package com.example.sit.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.file.Paths;
import java.util.ArrayList;



public class Sit_And_Ssit extends AppCompatActivity {
   // public String paths;

    DatabaseReference databaseReference;
    Button sit1;
    private ListView userlist;
    //DataSnapshot dataSnapshot;
    private ArrayList<String> paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit__and__ssit);
        sit1=findViewById(R.id.sit1);
        sit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ssit.class));
            }
        });


    }
}
