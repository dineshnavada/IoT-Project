package com.example.sit.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class user extends AppCompatActivity {
    EditText name,number;
    Button btn,btn2;
    TextView slot_no;
    Place p;
    //History history;
    private DatabaseReference databaseReference;
   // private FirebaseAuth mAuth; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
       // String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
         final String s = getIntent().getStringExtra("BUTTON_SLOT");
         int slot=Integer.parseInt(s)-1;
         String slots=Integer.toString(slot);
        final String a=getIntent().getStringExtra("NAME");
         databaseReference=FirebaseDatabase.getInstance().getReference().child(a).child("Slots").child("Slots").child(slots).child("vehicle");
        number = findViewById(R.id.vehicle);
        btn = findViewById(R.id.upload);
        btn2=findViewById(R.id.back);

     //   name = findViewById(R.id.name);
        slot_no=findViewById(R.id.Slot_no);
        slot_no.setText("SLOT "+s);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
               // databaseReference.push().child(s);

                String p="[A-Z][A-Z]-\\d\\d-[A-Z][A-Z]-\\d\\d\\d\\d";
                Pattern pat=Pattern.compile(p);
                Matcher match=pat.matcher(number.getText().toString());
                Boolean b;
                b = match.find();
                if (b==true)
                {
                    Toast.makeText(getApplicationContext(),"SLOT"+number.getText().toString()+" is booked successfully",Toast.LENGTH_LONG).show();
                    databaseReference.setValue(number.getText().toString());
                    number.getText().clear();


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter a valid Vehicle number!",Toast.LENGTH_LONG).show();
                    number.getText().clear();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

    }
    }

