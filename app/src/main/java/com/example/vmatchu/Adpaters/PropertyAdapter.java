package com.example.vmatchu.Adpaters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vmatchu.Classes.PropertiesDetails;
import com.example.vmatchu.R;

import java.util.ArrayList;

public class PropertyAdapter extends RecyclerView.Adapter {
    private ArrayList<PropertiesDetails> propertiesDetails;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.property,viewGroup,false);


        return new ViewHolder(view);
    }

    public PropertyAdapter(ArrayList<PropertiesDetails> propertiesDetails) {
        this.propertiesDetails = propertiesDetails;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 =(ViewHolder)viewHolder;
        PropertiesDetails propertiesDetails1=propertiesDetails.get(i);
        //viewHolder1.id.setText(propertiesDetails1.getId());
        viewHolder1.Title.setText(propertiesDetails1.getTitle());
        viewHolder1.status.setText(propertiesDetails1.getStatus());
        viewHolder1.city.setText(propertiesDetails1.getCity());
        viewHolder1.type.setText(propertiesDetails1.getType());
        viewHolder1.date.setText(propertiesDetails1.getDate());



    }
    @Override
    public int getItemCount() {
        if(propertiesDetails!=null){
            return propertiesDetails.size();
        }
        else {
            return 0;
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {


        View view;
        TextView id;
        TextView Title;
        TextView status;
        TextView city;
        TextView type;
        TextView date;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            id=view.findViewById(R.id.id);
            Title=view.findViewById(R.id.title_myProp_2);
            status=view.findViewById(R.id.active_2);
            city=view.findViewById(R.id.location_2);
            type=view.findViewById(R.id.type_2);
            date=view.findViewById(R.id.calenxder_2);

        }



    }

}
