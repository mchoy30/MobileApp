package com.example.mchoy.finalprojectmobileapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<Boolean> mCheckbox = new ArrayList<>();
    private ArrayList<String> mText = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<Boolean> mCheckbox, ArrayList<String> mText, Context mContext) {
        this.mCheckbox = mCheckbox;
        this.mText = mText;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d("mchoy30", "onBindHolder: Called") ;
        viewHolder.chkBox.setText(mText.get(position));

       // viewHolder.chkBox.isChecked(mCheckbox.get(position));

     //   Boolean te = mCheckbox.get(position);
        Boolean test =  viewHolder.chkBox.isChecked();
        Log.d("tag", "s" + test);
        viewHolder.chkBox.setChecked(mCheckbox.get(position));

        viewHolder.chkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCheckbox.set(position,isChecked);

            }
        });




    }

    @Override
    public int getItemCount() {
        return mText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox chkBox;
        TextView txtVeiw;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //txtVeiw = itemView.findViewById(R.id.textVeiw);
            chkBox = itemView.findViewById(R.id.checkbox);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
