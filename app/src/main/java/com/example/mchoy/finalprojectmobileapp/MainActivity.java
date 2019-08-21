package com.example.mchoy.finalprojectmobileapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public DatabaseReference mDatabaseRoot;
    public DatabaseReference mItineraryRef;
    public  DatabaseReference mItemRef;
    private  RecyclerViewAdapter adapter;
    public Button btnUpdate;
    private TextView txtTest;

    private String iItem;
    // For Recycler View
    private ArrayList<Boolean> mChecked = new ArrayList<>();
    private ArrayList<String> mText = new ArrayList<>();
    private String dataKey;
    private RecyclerView recyclerView;


    // Recycler View DB test

    private ItineraryItem i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        iItem = intent.getStringExtra("iList");
         dataKey = intent.getStringExtra("dataKey");

        //Gets root of Firebase json tree
        mDatabaseRoot = FirebaseDatabase.getInstance().getReference();
        mItineraryRef = mDatabaseRoot.child("Itinerary");
        mItemRef = mItineraryRef.child(iItem);



        btnUpdate = findViewById(R.id.btnUpdateList);


    }


    /// Working now need to refactor later
    private void startRecyclerView()
    {
        mText.clear();
        mChecked.clear();
        for (String item : i.getListItems())
        {

            mText.add(item);

            Log.d("MCHOY30", item.toString());
        }


        for (Boolean item : i.getIsChecked())
        {

            mChecked.add(item.booleanValue());
            Log.d("MCHOY30", ":" + item);

        }



        startRec();
    }

    public void startRec(){

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(mChecked, mText, MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    protected void onStart() {
        super.onStart();
         // mItineraryRef
        mItineraryRef.addValueEventListener(new ValueEventListener() {

            // Fires every time Itneray updates in real time db or when activity starts
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//
// working oN THIS
        /// Fires onDataChanged methode
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  mItineraryRef.setValue("Button test worjjking");

                updateDB(mItineraryRef, mChecked );
                Log.d("tag", "test");
            }
        });
    }


    public void updateDB(DatabaseReference databaseReference, ArrayList<Boolean> updatedBooleanArray )
    {
        Toast.makeText(this, "UPDATE DB", Toast.LENGTH_SHORT).show();
        String test2 = "asdsad";

        String key =  dataKey;
        i.setChecked(updatedBooleanArray);
        Map<String, Object> postValues = i.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, postValues);
        recyclerView.removeAllViews();
        databaseReference.updateChildren(childUpdates);



     //   Log.d("SAd" ,dbIschecked + test2 );
        Log.d("tag", "test");


    }


    private void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds: dataSnapshot.getChildren()){

           String listItem = ds.child("name").getValue().toString();
            if(listItem.equals(iItem)) {
                DataSnapshot dsName;
                DataSnapshot dsIsChecked;
                DataSnapshot dsListItems;
                String test = "asdasd";
                dsName = ds.child("name");
                dsIsChecked = ds.child("isChecked");
                dsListItems = ds.child("listItems");
                ArrayList listArray = new ArrayList();
                ArrayList checkList = new ArrayList();
                for(DataSnapshot dsListArray: dsListItems.getChildren()){

                    listArray.add(dsListArray.getValue()) ;
                }

                for(DataSnapshot dsCheckArray: dsIsChecked.getChildren()){

                    boolean result;
                    String check = dsCheckArray.getValue().toString();
                    if(check.equals("false"))
                    {
                        result = false;
                    }
                    else
                    {
                        result = true;
                    }

                    checkList.add(result) ;
                }



                // breaking
               // ArrayList listArray = convertDataToArrayListString(dsListItems);
               // ArrayList checkList = convertDataToArrayListBoolean(dsIsChecked);
                i = new ItineraryItem();
                i.setListItems(listArray);
                i.setChecked(checkList);
                i.setName(dsName.getValue().toString());

                startRecyclerView();

                Log.d("MCHOY30", "AS" + test+ i.getListItems());
            }

        }
    }
}

