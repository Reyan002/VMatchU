package com.example.vmatchu.Adpaters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vmatchu.Classes.PropertiesDetails;
import com.example.vmatchu.Pojo.CityAreaSubareaSectorDetailsResponse;
import com.example.vmatchu.R;

import java.util.ArrayList;

public class cityAdapter extends RecyclerView.Adapter{
    private ArrayList<CityAreaSubareaSectorDetailsResponse> city;
    private Context context;
    public cityAdapter(ArrayList<CityAreaSubareaSectorDetailsResponse> city, Context context) {
        this.city = city;
        this.context=context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        final View view= (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city,viewGroup,false);


        return new PropertyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderCity viewHolderCity=(ViewHolderCity)viewHolder;
        CityAreaSubareaSectorDetailsResponse cityName=city.get(i);
        viewHolderCity.city_ed.setText(cityName.getName());
    }

    @Override
    public int getItemCount() {
        if(city!=null){
            return city.size();
        }
        else {
            return 0;
        }
    }



    private static class ViewHolderCity extends RecyclerView.ViewHolder {


        View view_city;
        TextView city_ed;





        public ViewHolderCity(@NonNull View itemView) {
            super(itemView);

            this.view_city = itemView;
            city_ed=view_city.findViewById(R.id.City_ed);

        }
    }

    private class ViewHolder {
    }
}
