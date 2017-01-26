package com.android.ad.mycart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ad.mycart.R;

/**
 * Created by rajeev on 24/1/2017.
 */

public class OrderBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderbooks);
    }

    int quantity = 1;

    public void increment(View view) {
        if(quantity == 100) {
            Toast.makeText(this, "Cannot order more then 100", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(this, "Cannot order less then 1", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
        EditText nameTextField =(EditText)findViewById(R.id.name_textfield);
        String name = nameTextField.getText().toString();
        String messageSummary = createorderSummary(name);

        //intent for sending email notification
       /* Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary for:"+name);
        intent.putExtra(Intent.EXTRA_TEXT,messageSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/

        displayQuantity(quantity);

        displayMessage(messageSummary);

    }

    /**

     calculate price
     @return total price
     @param
     */


    /**
     * Create order summary
     * @Param boolean tells topping added or not
     */
    public String createorderSummary(String nameOfPerson) {
        String message = ("Name:" + nameOfPerson);
        message+= "Thank You";
        return message;
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
    /** private void displayPrice(int number) {
     TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
     priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
     }

     /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
