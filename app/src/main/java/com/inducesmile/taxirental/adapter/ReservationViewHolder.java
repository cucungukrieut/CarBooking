package com.inducesmile.taxirental.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inducesmile.taxirental.R;


public class ReservationViewHolder extends RecyclerView.ViewHolder {

    public TextView carName;
    public TextView location;
    public TextView date;
    public TextView time;
    public TextView price;
    public ImageView pathImage;
    public View view;


    public ReservationViewHolder(View itemView) {
        super(itemView);

        carName = (TextView)itemView.findViewById(R.id.car_name);
        location = (TextView)itemView.findViewById(R.id.car_location);
        date = (TextView)itemView.findViewById(R.id.pick_up_date);
        time = (TextView)itemView.findViewById(R.id.pick_up_time);
        price = (TextView)itemView.findViewById(R.id.rental_price);
        pathImage = (ImageView)itemView.findViewById(R.id.image_path);
        view = itemView;

    }
}
