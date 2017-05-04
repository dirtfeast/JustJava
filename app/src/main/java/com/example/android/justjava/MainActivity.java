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

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
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
        displayQuantity(quantity);
        displayPrice(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}