package com.example.sit.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import static android.graphics.Color.*;

public class ssit extends AppCompatActivity {
    DatabaseReference databaseReference;
   // Spinner spinner;
    String db;
   // String db;
    private ListView userlist;
    private Spinner spinner;
    DataSnapshot dataSnapshot;
    private ArrayList<String> paths;//=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssit);
        databaseReference= FirebaseDatabase.getInstance().getReference();
       // spinner=findViewById(R.id.spinner);
      // db=FirebaseDatabase.getInstance().getReference().child("SSIT").child("Slots").child("Status").getKey();
        userlist=findViewById(R.id.user_list);
        paths=new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,paths);
        userlist.setAdapter(arrayAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    String vs= ds.getKey();
                    Log.i("This is ", vs);
                    if(vs.equals("History_of_Vehicles"))
                    {
                        paths.add("                       AVAILABLE PATHS");
                    }
                    else {
                        //String db=FirebaseDatabase.getInstance().getReference("SIT_CS_Block").getKey();
                        paths.add(vs);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

     userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Intent start=new Intent(getApplicationContext(),design.class);
             String author = userlist.getItemAtPosition(i).toString();
             if (author.equals("Available Paths"))
             {
             }
             else {
                 start.putExtra("EXTRA_SESSION_ID", author);
                 startActivity(start);
             }

         }
     });



    }
}



















