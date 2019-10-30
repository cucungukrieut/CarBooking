package com.inducesmile.taxirental;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.inducesmile.taxirental.customviews.DateBlock;

public class BookingActivity extends AppCompatActivity {

    private static final String TAG = BookingActivity.class.getSimpleName();

    private ImageView carImage;

    private TextView pickUpLocation;

    private DateBlock startDateBlock, endDateBlock;

    private String startDay, startMonth, startTime, endDay, endMonth, endTime;

    private CheckBox skiRack, carSeat, navigation;

    private TextView dailyPrice, extraHour, tax, totalAmount;

    private TextView name, address, email, phone;

    private RadioButton payPal, creditCard, payNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carImage = (ImageView)findViewById(R.id.car_image);

        pickUpLocation = (TextView)findViewById(R.id.pick_up_address);

        startDateBlock = (DateBlock)findViewById(R.id.pick_up_date);
        startDateBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        endDateBlock = (DateBlock)findViewById(R.id.destination_date);
        endDateBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        skiRack = (CheckBox)findViewById(R.id.ski_rack);
        carSeat = (CheckBox)findViewById(R.id.child_car_seat);
        navigation = (CheckBox)findViewById(R.id.navigation_system);

        dailyPrice = (TextView)findViewById(R.id.daily_price);
        extraHour = (TextView)findViewById(R.id.extra_hour);
        tax = (TextView)findViewById(R.id.tax);
        totalAmount = (TextView)findViewById(R.id.total_amount);

        name = (TextView) findViewById(R.id.customer_name);
        address = (TextView)findViewById(R.id.customer_address);
        email = (TextView)findViewById(R.id.customer_email);
        phone = (TextView)findViewById(R.id.customer_phone);

        payPal = (RadioButton)findViewById(R.id.pay_with_pay_pal);
        creditCard = (RadioButton)findViewById(R.id.pay_with_credit_card);
        payNow = (RadioButton)findViewById(R.id.pay_later);

        Button payNowButton = (Button)findViewById(R.id.pay_now);
        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkoutIntent = new Intent(BookingActivity.this, CheckoutActivity.class);
                startActivity(checkoutIntent);
            }
        });


    }

}
