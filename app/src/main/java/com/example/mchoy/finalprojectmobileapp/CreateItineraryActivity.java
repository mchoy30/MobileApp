package com.example.mchoy.finalprojectmobileapp;

import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateItineraryActivity extends AppCompatActivity {

    private ArrayList<String> createItems;
    private ArrayList<Boolean> itemState;
    private Button btnAdd, btnCreate, btnUndo;
    private EditText editText, edittextName;
    private TextView tvView;
    public DatabaseReference mDatabaseRoot;
    public DatabaseReference mItineraryRef;
    public DatabaseReference mNewItineraryRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_itinerary);
        mDatabaseRoot = FirebaseDatabase.getInstance().getReference();
        mItineraryRef = mDatabaseRoot.child("Itinerary");



        edittextName = findViewById(R.id.editTextName);
        btnAdd = findViewById(R.id.btnAdd);
        tvView = findViewById(R.id.tvList);
        editText = findViewById(R.id.etItineraryItem);
        btnCreate = findViewById(R.id.btnCreate);
        btnUndo = findViewById(R.id.btnUndo);



        createItems = new ArrayList<String>();
        itemState = new ArrayList<Boolean>();

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(createItems.size() > 0)
                {
                    createItems.remove(createItems.size()-1);
                    if(createItems.size() > 0) {
                        tvView.setText(createItems.toString());
                    }
                    else
                    {
                        tvView.setText("");
                    }

                    Toast.makeText(CreateItineraryActivity.this, "as: " , Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et = editText.getText().toString();

                if(!et.isEmpty() ){
                    createItems.add(et);
                    itemState.add(false);
                    tvView.setText(createItems.toString());

                    Toast.makeText(CreateItineraryActivity.this, "as: " + et, Toast.LENGTH_SHORT).show();
                    editText.setText("");
                }
            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etName = edittextName.getText().toString();
                if(!createItems.isEmpty() && !etName.isEmpty())
                {
                    createItineraryList(createItems, itemState, etName, mNewItineraryRef);
                }
            }
        });


    }

    public void createItineraryList(ArrayList<String> items, ArrayList<Boolean> itemState, String iName, DatabaseReference mNewItineraryRef)
    {

            //ItineraryItem child = new ItineraryItem(iName, itemState, items);
            mNewItineraryRef = mItineraryRef.push();
            mNewItineraryRef.setValue(new ItineraryItem(iName, itemState, items));



    }
}
