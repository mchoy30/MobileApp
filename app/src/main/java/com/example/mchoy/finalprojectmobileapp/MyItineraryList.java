package com.example.mchoy.finalprojectmobileapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyItineraryList extends AppCompatActivity {
    public DatabaseReference mDatabaseRoots;
    public DatabaseReference mItineraryRefs;
    private ListView lstMyItneraryList;
    public  ArrayAdapter adapter;
    public ArrayList<String> itineraryArray;
    public String dataKey;
    private ArrayList<String> keyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_itinerary_list);

        mDatabaseRoots = FirebaseDatabase.getInstance().getReference();
        mItineraryRefs = mDatabaseRoots.child("Itinerary");
        lstMyItneraryList = findViewById(R.id.lstMyItineraryList);


        lstMyItneraryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Object test = parent.getItemIdAtPosition(position);

                ListView test2 = (ListView) test;
                String listItem = itineraryArray.get(position);


                Intent intent = new Intent(MyItineraryList.this, MainActivity.class);
                intent.putExtra("iList", listItem);

                String key = keyList.get(position);

                intent.putExtra("dataKey", key);
                startActivity(intent);


                //Toast.makeText(MyItineraryList.this, "TEST" + s, Toast.LENGTH_SHORT).show();
            }
        });





    }


    @Override
    protected void onStart() {
        super.onStart();

        mItineraryRefs.addValueEventListener(new ValueEventListener() {
            // Fires every time Itneray updates in real time db or when activity starts
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GetItneraryList(dataSnapshot);
                Log.d("Mchoy30", "FFF");

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


        public void GetItneraryList(DataSnapshot ds)
        {
            itineraryArray = new ArrayList<String>();
            keyList = new ArrayList<String>();
            for(DataSnapshot data : ds.getChildren()){
               Log.d("MCHOY", "a");


               String test2 = data.child("name").getValue().toString();
               String dataKey = data.getKey();
               Log.d("A", test2);
                itineraryArray.add(test2);
                keyList.add(dataKey);
            }

            ItineraryItemAdapter adapter = new ItineraryItemAdapter(this, R.layout.my_adapter_layout, itineraryArray);
            lstMyItneraryList.setAdapter(adapter);

           // adapter.add(itineraryArray);
        //    lstMyItneraryList.setAdapter(adapter);

        }
    }




