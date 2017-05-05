/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;
    String status = "pending";

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice(quantity);
        status = createOrderSummary(price);
        displayStatus(status);
    }

    // Calculates the price of the order based on quantity
    // @return the price
    private int calculatePrice(int qnt) {
        return qnt * 5;
    }

    public String createOrderSummary(int price) {
        String summary = "Name: Dan W\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank You!";
        return summary;
    }

    // When '+' button hit, increase quantity by 1
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
        displayPrice(quantity * 5);
    }

    // When '-' button hit, decrease quantity by 1
    public void decrement(View view) {
        if (quantity >= 1) {
            quantity = quantity - 1;
        } else {}
        displayQuantity(quantity);
        displayPrice(quantity * 5);
    }
    // 'Reset' button, sets quantity back to 0
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