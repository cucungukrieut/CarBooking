package com.inducesmile.taxirental;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = CheckoutActivity.class.getSimpleName();

    private ImageView carImage;

    private TextView carName, pickUpAddress, rentalDate, rentalTime, rentalCost, orderNumber,
            contactNumber, rentalStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Payment Confirmation");

        carImage = (ImageView)findViewById(R.id.image_path);

        carName = (TextView)findViewById(R.id.car_name);
        pickUpAddress = (TextView)findViewById(R.id.car_location);
        rentalDate = (TextView)findViewById(R.id.pick_up_date);
        rentalTime = (TextView)findViewById(R.id.pick_up_time);
        rentalCost = (TextView)findViewById(R.id.rental_price);

        orderNumber = (TextView)findViewById(R.id.order_number);
        contactNumber = (TextView)findViewById(R.id.contact_number);
        rentalStatus = (TextView)findViewById(R.id.status);

        Button backToMenuButton = (Button)findViewById(R.id.back_to_menu);
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(CheckoutActivity.this, HomeActivity.class);
                startActivity(menuIntent);
            }
        });

    }

}
