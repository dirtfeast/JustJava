package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.text.NumberFormat;

import static android.R.attr.duration;
import static android.R.attr.y;
import static android.content.Intent.ACTION_SENDTO;
import static com.example.android.justjava.R.id.name_edit_text;

//App that displays an form to order coffees
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } // Close method onCreate()

    // Global variables
    int quantity = 0;
    int coffeePrice = 4;
    int wcPrice = 1;
    int chocoPrice = 2;
    String status = getResources().getString(R.string.order_pending);

    // Global toast messages
    CharSequence minusText = "Too few coffees!";
    CharSequence plusText = "Too many coffees!";

    // Method called when the order button is clicked
    // calculatePrice() > createOrderSummary() > displayStatus()
    public void submitOrder(View view) {
        // displayStatus(createOrderSummary(calculatePrice()));
        String oSummary = createOrderSummary(calculatePrice());
        displayStatus(oSummary);
        disableWCCheckBox();
        disableChocoCheckBox();
        emailOrder(oSummary);
    } // Close method submitOrder()

    public void emailOrder(String order) {
        // String[] addresses = new String[2];
        // addresses[0] = "dirtfeast@gmail.com";
        // addresses[1] = "dirtfeast@gmail.com";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT, order);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // Method whipped cream checkbox true/false
    private boolean queryWCream() {
        CheckBox wcCheckBox = (CheckBox) findViewById(R.id.whipped_checkbox);
        return wcCheckBox.isChecked();
    } // Close method queryWCream()

    // Method to get contents of Name field
    private String queryName() {
        EditText nameField = (EditText) findViewById(R.id.name_edit_text);
        return nameField.getText().toString();
    } // Close method queryName()

    // Method to clear contents of Name field upon reset
    private void clearName() {
        EditText nameField = (EditText) findViewById(R.id.name_edit_text);
        nameField.setText("");
    } // Close method clearName()

    // Method Chocolate Goo checkbox true/false
    private boolean queryChoco() {
        CheckBox chocoCheckBox = (CheckBox) findViewById(R.id.choco_checkbox);
        return chocoCheckBox.isChecked();
    } //Close method queryChoco()

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
    // Also factor in toppings
    // @return the price
    private int calculatePrice() {
        int basePrice = coffeePrice;
        if (queryWCream() == true) {
            basePrice += wcPrice;
        }

        if (queryChoco() == true) {
            basePrice += chocoPrice;
        }
        return basePrice * quantity;
    } // Close method calculatePrice()

    // Method composes string with Name, Quantity, Total and Thank You
    // @returns string 'summary'
    public String createOrderSummary(int price) {
        String summary = "Name: " + queryName();
        summary += "\nQuantity: " + quantity;
        summary += "\nAdd whipped cream? " + queryWCream();
        summary += "\nAdd chocolate goo? " + queryChoco();
        summary += "\nTotal: $" + price;
        summary += "\nThank You!";
        return summary;
    } // Close createOrderSummary()

    // Method increases global 'quantity' by 1 when '+' button hit
    public void increment(View view) {
        if (quantity < 10) {
            quantity += 1;
        } else {
            Toast.makeText(this, plusText, Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
        displayPrice(calculatePrice());
    }

    // Method decreases 'quantity' by 1 When '-' button hit
    // If 'quantity' == 0 leaves it at 0
    public void decrement(View view) {
        if (quantity >= 1) {
            quantity -= 1;
        } else {
            Toast.makeText(this, minusText, Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
        displayPrice(calculatePrice());
    }

    // Method for 'Reset' button
    // Sets quantity to 0, coffee price to original, uncheck Whipped Cream, and status to pending
    public void reset(View view) {
        quantity = 0;
        status = "pending";
        uncheckWCream();
        uncheckChoco();
        clearName();
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
    } // Close method displayPrice()

    // Method displays order status in TextView status_text_view
    private void displayStatus(String s) {
        TextView statusTextView = (TextView) findViewById(R.id.status_text_view);
        statusTextView.setText(s);
    } // Close method displayStatus()

} // Close class MainActivity