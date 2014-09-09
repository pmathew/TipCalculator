package com.yahoo.pmathew.tipcalapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


public class TipCalActivity extends Activity {

    private EditText billAmountEditText;
    private TextView tipAmountTextView;

    private List<Button> buttons = new LinkedList<Button>();

    private Integer tipPercentage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_cal);
        billAmountEditText = (EditText) findViewById(R.id.billAmt);
        billAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calTipAmount();
            }
        });


        tipAmountTextView = (TextView) findViewById(R.id.tvTipAmt);
        buttons.add((Button) findViewById(R.id.b10Percent));
        buttons.add((Button) findViewById(R.id.b15Percent));
        buttons.add((Button) findViewById(R.id.b20Percent));

        if(tipPercentage == null){
            onTipPercentageButtonClick(buttons.get(0));
        }
    }

    public void onTipPercentageButtonClick(View view){
        tipPercentage = Integer.parseInt(view.getTag().toString());
        for(Button button1 : buttons){
            if(view.getId() == button1.getId()){
                button1.setBackgroundColor(Color.parseColor("#58857e"));
            } else {
                button1.setBackgroundColor(Color.parseColor("#8dbab3"));
            }
        }
        calTipAmount();
    }

    private void calTipAmount(){
        String amount = billAmountEditText.getText().toString();
        if(amount == null || amount.trim().length() == 0){
            tipAmountTextView.setText("--");
        } else {
            Float totalAmount = Float.parseFloat(amount);
            Float tipAmount = totalAmount * tipPercentage / 100;
            tipAmountTextView.setText(tipAmount.toString());
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_cal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
