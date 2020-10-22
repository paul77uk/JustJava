package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        displayPrice(numberOfCoffees * 5);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText editText = findViewById(R.id.editText);

        int totalPrice = numberOfCoffees * 5;
        if (hasWhippedCream) totalPrice += numberOfCoffees * 1;
        if (hasChocolate) totalPrice += numberOfCoffees * 2;

        String priceMessage =
                "Name: " + editText.getText() +
                        "\nAdd whipped cream? " + hasWhippedCream +
                        "\nAdd chocolate? " + hasChocolate +
                        "\nQuantity: " + numberOfCoffees +
                        "\nTotal: £" + totalPrice
                        + "\nThank you!";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + editText.getText());
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(priceMessage);
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increment(View view) {
        if (numberOfCoffees >= 0) {
            numberOfCoffees++;
            display(numberOfCoffees);
//            displayPrice(numberOfCoffees * 5);
        }
    }

    public void decrement(View view) {
        if (numberOfCoffees > 0) {
            numberOfCoffees--;
            display(numberOfCoffees);
//            displayPrice(numberOfCoffees * 5);
        } else {
            Toast.makeText(getApplicationContext(), "No negative numbers", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}