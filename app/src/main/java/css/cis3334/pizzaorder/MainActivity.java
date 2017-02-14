package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//Modified by Malvern Madondo 2/13/2017

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall, rbMedium, rbLarge;
    CheckBox chkbxCheese, chkbxDelivery;
    TextView txtTotal, txtStatus;
    PizzaOrderInterface pizzaOrderSystem;
    Spinner spnrToppings ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);

        // Spinner element
        spnrToppings = (Spinner) findViewById(R.id.spinnerToppings);

        pizzaOrderSystem = new PizzaOrder(this);

    }

    @Override
    public void updateView(String orderStatus) {
        txtStatus.setText("Order Status: " + orderStatus);
    }

    public void onClickOrder(View view) {
        pizzaOrderSystem.setDelivery(chkbxDelivery.isChecked());
        String orderDescription, topping, pizzaSize = "";
        topping = spnrToppings.getSelectedItem().toString();

        //String orderDescription = pizzaOrderSystem.OrderPizza("Pepperoni","Large", false); //default

        if(rbSmall.isChecked()){
            pizzaSize = "Small";
        }
        else if(rbMedium.isChecked()){
            pizzaSize = "medium";
        }
        else if(rbLarge.isChecked()){
            pizzaSize = "Large";
        }

        boolean extraCheese = false;

        if(chkbxCheese.isChecked()){
            extraCheese = true;
        }

        orderDescription = pizzaOrderSystem.OrderPizza(topping, pizzaSize, extraCheese);

        if(chkbxDelivery.isChecked()){
            orderDescription += " for delivery.";
        } else {
            orderDescription += " for pickup.";
        }

        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+ orderDescription , Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
    }

}
