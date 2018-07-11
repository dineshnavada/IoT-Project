package com.example.sit.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class design extends AppCompatActivity {
    Button btn[]=new Button[21];
    DatabaseReference databaseReference;
    private Place place;
    String btnTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        final String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
        final String a=getIntent().getStringExtra("EXTRA_SESSION_ID");
       final AlertDialog  b=addDialogBox();
        databaseReference= FirebaseDatabase.getInstance().getReference().child(s);
            btn[1] = findViewById(R.id.button1);
            btn[2] = findViewById(R.id.button2);
            btn[3] = findViewById(R.id.button3);
            btn[4] = findViewById(R.id.button4);
            btn[5] = findViewById(R.id.button5);
            btn[6] = findViewById(R.id.button6);
            btn[7] = findViewById(R.id.button7);
            btn[8] = findViewById(R.id.button8);
            btn[9] = findViewById(R.id.button9);
            btn[10] = findViewById(R.id.button10);
            btn[11] = findViewById(R.id.button11);
            btn[12] = findViewById(R.id.button12);
            btn[13] = findViewById(R.id.button13);
            btn[14] = findViewById(R.id.button14);
            btn[15] = findViewById(R.id.button15);
            btn[16] = findViewById(R.id.button16);
            btn[17] = findViewById(R.id.button17);
            btn[18] = findViewById(R.id.button18);
            btn[19] = findViewById(R.id.button19);
            btn[20] = findViewById(R.id.button20);
        final Context context=this;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("abc","abc");
                place = dataSnapshot.getValue(Place.class);
                Log.i("no_of_cols",Integer.toString(place.getNo_of_cols()));
                List<Slot> lv = new ArrayList<Slot>(place.getNo_of_slots());
                // pd.dismiss();

                lv = place.Slots.get("Slots");

                int slots = place.getNo_of_slots();
                Log.i("no_of_slots",Integer.toString(slots));

                for (int i = slots + 1; i <= 20; i++) {
                    btn[i].setBackgroundColor(Color.parseColor("#808080"));
                    btn[i].setText("UNAVAILABLE");
                    btn[i].setEnabled(false);
                }

                for (int i=1;i<=slots;i++) {
                    if (lv.get(i - 1).getStatus() == 1 || !lv.get(i-1).getVehicle().equals("No")) {
                        btn[i].setBackgroundColor(Color.parseColor("#ff0000"));
                        //btn[i].setText("UNAVAILABLE");
                        btn[i].setEnabled(false);
                    }
                    else {
                        btn[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button btn=findViewById(view.getId());
                                btnTxt=btn.getText().toString();
                                Log.d("This ",btnTxt);

                                Intent intent=new Intent(getApplicationContext(), user.class);
                                // String s= btnTxt;
                                // String author = btnTxt.getItemAtPosition(i).toString();
                                intent.putExtra("BUTTON_SLOT",btnTxt);
                                intent.putExtra("NAME",a);
                               // intent.putExtra("NAME",s);
                                startActivity(intent);
                               // Intent intent1=new Intent(getApplicationContext(),user.class);

                               // startActivity(intent1);


                            }
                        });
                    }
                }
                b.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }
    public AlertDialog addDialogBox()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.sleep,null);
        dialog.setView(dialogView);
        dialog.setTitle("Mine");
        dialog.setCancelable(false);
        final AlertDialog b = dialog.create();
        b.show();
        return b;
    }


//    @Override
//    public void onClick(View v) {
//        Log.d("This is","aaa");
//
//    }







}
