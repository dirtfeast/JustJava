package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.text.NumberFormat;

import static android.R.attr.y;

//App that displays an form to order coffees
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } // Close method onCreate()

    // Global variables
    int quantity = 0;
    int origCoffeePrice = 4;
    int coffeePrice = 4;
    int wcPrice = 1;
    String status = "pending";

    // Method called when the order button is clicked
    // calculatePrice() > createOrderSummary() > displayStatus()
    public void submitOrder(View view) {
        // status = createOrderSummary(calculatePrice());
        displayStatus(createOrderSummary(calculatePrice()));
        disableWCCheckBox();
        disableChocoCheckBox();
    }

    // Method whipped cream checkbox true/false
    private boolean queryWCream() {
        CheckBox wcCheckBox = (CheckBox) findViewById(R.id.whipped_checkbox);
        return wcCheckBox.isChecked();
    }

    // Method Chocolate Goo checkbox true/false
    private boolean queryChoco() {
        CheckBox chocoCheckBox = (CheckBox) findViewById(R.id.choco_checkbox);
        return chocoCheckBox.isChecked();
    }

    // Method to force uncheck Whipped Cream checkbox
    private void uncheckWCream() {
        CheckBox wcCheckBox = (CheckBox) findViewById(R.id.whipped_checkbox);
        wcCheckBox.setChecked(false);
        wcCheckBox.setEnabled(true);
    }

    // Method to force uncheck Chocolate Goo checkbox
    private void uncheckChoco() {
        CheckBox chocoCheckBox = (CheckBox) findViewById(R.id.choco_checkbox);
        chocoCheckBox.setChecked(false);
        chocoCheckBox.setEnabled(true);
    }

    // Method to disable (grey out) Whipped Cream checkbox once order finalized
    private void disableWCCheckBox() {
        CheckBox wcCheckBox = (CheckBox) findViewById(R.id.whipped_checkbox);
        wcCheckBox.setEnabled(false);
    }

    // Method to disable (grey out) Chocolate Goo checkbox once order finalized
    private void disableChocoCheckBox() {
        CheckBox chocoCheckBox = (CheckBox) findViewById(R.id.choco_checkbox);
        chocoCheckBox.setEnabled(false);
    }

    // Method calculates the price of the order based on quantity
    // @return the price
    private int calculatePrice() {
        if (queryWCream() == true) {
            coffeePrice += wcPrice;
        } else {
            coffeePrice = origCoffeePrice;
        }
        return quantity * coffeePrice;
    } // Close method calculatePrice()

    // Method composes string with Name, Quantity, Total and Thank You
    // @returns string 'summary'
    public String createOrderSummary(int price) {
        String summary = "Name: Dan W";
        summary += "\nQuantity: " + quantity;
        summary += "\nAdd whipped cream? " + queryWCream();
        summary += "Add cholate goo? " + queryChoco();
        summary += "\nTotal: $" + price;
        summary += "\nThank You!";
        return summary;
    }

    // Method increases global 'quantity' by 1 when '+' button hit
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice());
    }

    // Method decreases 'quantity' by 1 When '-' button hit
    // If 'quantity' == 0 leaves it at 0
    public void decrement(View view) {
        if (quantity >= 1) {
            quantity = quantity - 1;
        } else {
        }
        displayQuantity(quantity);
        displayPrice(calculatePrice());
    }

    // Method for 'Reset' button
    // Sets quantity to 0, coffee price to original, uncheck Whipped Cream, and status to pending
    public void reset(View view) {
        quantity = 0;
        status = "pending";
        coffeePrice = origCoffeePrice;
        uncheckWCream();
        uncheckChoco();
        displayQuantity(quantity);
        displayPrice(quantity);
        displayStatus(status);
    }

    // Method displays quantity (number of cups) in TextView quantity_text_view
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    // Method displays total price in TextView price_text_view
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    // Method displays order status in TextView status_text_view
    private void displayStatus(String s) {
        TextView statusTextView = (TextView) findViewById(R.id.status_text_view);
        statusTextView.setText(s);
    }
}