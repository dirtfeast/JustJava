package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.text.NumberFormat;

//App that displays an form to order coffees
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Global variables
    int quantity = 0;
    String status = "pending";

    // Method called when the order button is clicked
    public void submitOrder(View view) {
        status = createOrderSummary(calculatePrice(quantity));
        displayStatus(status);
    }

    // Method calculates the price of the order based on quantity
    // @return the price
    private int calculatePrice(int qnt) {
        return qnt * 5;
    }

    // Composes string with Name, Quantity, Total and Thank You
    // @returns string 'summary'
    public String createOrderSummary(int price) {
        String summary = "Name: Dan W";
        summary += "\nQuantity: " + quantity;
        summary += "\nTotal: $" + price;
        summary += "\nThank You!";
        return summary;
    }

    // Method increases global 'quantity' by 1 when '+' button hit
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(quantity));
    }

    // Method decreases 'quantity' by 1 When '-' button hit
    // If 'quantity' == 0 leaves it at 0
    public void decrement(View view) {
        if (quantity >= 1) {
            quantity = quantity - 1;
        } else {}
        displayQuantity(quantity);
        displayPrice(calculatePrice(quantity));
    }
    // Method for 'Reset' button, sets quantity back to 0 and status to pending
    public void reset(View view){
        quantity = 0;
        status = "pending";
        displayQuantity(quantity);
        displayPrice(quantity);
        displayStatus(status);
    }

    // Method to display quantity (number of cups) in TextView quantity_text_view
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    // Method to display total price in TextView price_text_view
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    // Method to display order status in TextView status_text_view
    private void displayStatus(String s) {
        TextView statusTextView = (TextView) findViewById(R.id.status_text_view);
        statusTextView.setText(s);
    }
}