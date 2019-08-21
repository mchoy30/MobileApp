package com.example.mchoy.finalprojectmobileapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItineraryItem {

    public String name;
    public ArrayList<Boolean> isChecked;
    public ArrayList<String> listItems;


    public ItineraryItem(){

    }

    public ItineraryItem(String name, ArrayList<Boolean> isChecked, ArrayList<String> listItems) {
        this.name = name;
        this.isChecked = isChecked;
        this.listItems = listItems;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("isChecked", isChecked);
        result.put("listItems", listItems);


        return result;
    }



//    // Will be used to add new items to database later
    public void updateDataBase(ArrayList<Boolean> isChecked, DatabaseReference mDatabase){
        //ItineraryItem item = new ItineraryItem(itemName, isChecked);

       // mDatabase.child()
    }

    public ArrayList<Boolean> getIsChecked() {
        return isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ArrayList<Boolean> getChecked() {
//        return isChecked;
//    }

    public void setChecked(ArrayList<Boolean> checked) {
        isChecked = checked;
    }

    public ArrayList<String> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<String> listItems) {
        this.listItems = listItems;
    }
}
