package com.inducesmile.taxirental.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inducesmile.taxirental.R;

public class SearchViewHolder extends RecyclerView.ViewHolder{

    public ImageView carImage;

    public TextView carName;
    public TextView carPrice;
    public TextView carFeature;
    public TextView carDuration;

    public View view;


    public SearchViewHolder(View itemView) {
        super(itemView);

        carImage = (ImageView)itemView.findViewById(R.id.car_rental_image);

        carName = (TextView)itemView.findViewById(R.id.car_rental_name);
        carPrice = (TextView)itemView.findViewById(R.id.car_rental_price);
        carFeature = (TextView)itemView.findViewById(R.id.car_rental_options);
        carDuration = (TextView)itemView.findViewById(R.id.car_rental_duration);

        view = itemView;
    }
}
