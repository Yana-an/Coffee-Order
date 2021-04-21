package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {
    private TextView textViewMakeOrder;
    private TextView textViewAdditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;


    private String name;
    private String password;
    private String drink;
    private StringBuilder builderAdditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        password = intent.getStringExtra("password");

        drink = getString(R.string.tea);
        textViewMakeOrder = findViewById(R.id.textViewWelcome);
        String makeOrder = String.format(getString(R.string.makeOrder), name);
        textViewAdditions = findViewById(R.id.textViewAdditions);
        String additions = String.format(getString(R.string.additions), drink);
        textViewAdditions.setText(additions);
        checkBoxMilk = findViewById(R.id.CheckBoxMilk);
        checkBoxSugar = findViewById(R.id.CheckBoxSugar);
        checkBoxLemon = findViewById(R.id.CheckBoxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        builderAdditions = new StringBuilder();


    }

    public void onClickChangeDrink(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        } else if (id == R.id.radioButtonCoffee) {
            drink = getString(R.string.coffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }

        String additions = String.format(getString(R.string.additions), drink);
        textViewAdditions.setText(additions);

    }

    public void onClickSentOrder(View view) {
        builderAdditions.setLength(0);
        if (checkBoxMilk.isChecked()) {
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if (checkBoxSugar.isChecked()) {
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }
        String optionsOfDrink = "";
        if (drink.equals(getString(R.string.tea))) {
            optionsOfDrink = spinnerTea.getSelectedItem().toString();
        } else {
            optionsOfDrink = spinnerCoffee.getSelectedItem().toString();
        }

        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea))) {
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
        String order = String.format(getString(R.string.order), name, password, drink, optionsOfDrink);
        String additions;
        if (builderAdditions.length() > 0) {
            additions = getString(R.string.need_additions) + builderAdditions.toString();
        } else {
            additions = "";
        }
        String fullOrder = order + additions;
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);

    }
}