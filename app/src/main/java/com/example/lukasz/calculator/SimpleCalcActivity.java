package com.example.lukasz.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SimpleCalcActivity extends AppCompatActivity {

    TextView text;
    BigDecimal memory = new BigDecimal(0.0).setScale(2, BigDecimal.ROUND_HALF_UP);
    BigDecimal operationNumber = new BigDecimal(0.0).setScale(2, BigDecimal.ROUND_HALF_UP);
    boolean isCTapped = false;
    boolean isInserting = true;
    boolean isDotUsed = false;

    public enum operationStatus {
        CLEAR, ADDITIONING, DIVIDING, SUBSTRUCTING, MULTIPLYING
    }

    operationStatus status = operationStatus.CLEAR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);
         text = findViewById(R.id.screen);
    }

    // C/CE
    public void backspaceClicked(View view) {

        text.setText("");
        if (isCTapped)
        {
            memory = BigDecimal.valueOf(0.0);
            status = operationStatus.CLEAR;
            isCTapped = false;
            return;
        }
        isCTapped = true;
        isDotUsed = false;
    }

    public void ACClicked(View view){
        text.setText("");
        memory = BigDecimal.valueOf(0);
        status = operationStatus.CLEAR;
        isDotUsed = false;

    }

    //SimpleOperations OnClick
    public void addition(View view)
    {
        if(status == operationStatus.ADDITIONING) return; // causing that spamming with plus don't affect result
        status = operationStatus.ADDITIONING;
        operatorClicked();
    }

    public void substructing(View view)
    {
        if(status == operationStatus.SUBSTRUCTING) return; // causing that spamming with minus don't affect result
        status = operationStatus.SUBSTRUCTING;
        operatorClicked();
    }

 public void dividing(View view)
    {
        if(status == operationStatus.DIVIDING) return; // causing that spamming with slash don't affect result
        status = operationStatus.DIVIDING;
        operatorClicked();
    }

 public void multiplying(View view)
    {
        if(status == operationStatus.MULTIPLYING) return; // causing that spamming with star don't affect result
        status = operationStatus.MULTIPLYING;
        operatorClicked();

    }

    private void operatorClicked()
    {
        try {
            computeMemoryValue();
            isInserting = false;
            isDotUsed = false;
        }
        catch (NumberFormatException e)
        {
            text.setText("Wrong number");
            isInserting = false;
        }
    }

    private void computeMemoryValue() {
        try {
           memory = BigDecimal.valueOf(Double.parseDouble(text.getText().toString()));

        }
        catch (NullPointerException e)
        {
            memory = BigDecimal.valueOf(0);
        }
    }

    public void EqualsButton (View view){
        try {
            if (isInserting) {
                computeOperationValue();
            }
            isInserting = false;
            isDotUsed = false;
            switch (status) {
                case ADDITIONING:
                    memory = memory.add(operationNumber);
                    break;
                case SUBSTRUCTING:
                    memory = memory.subtract(operationNumber);
                    break;
                case DIVIDING:
                    memory =  new BigDecimal(memory.doubleValue(),MathContext.DECIMAL32).divide(operationNumber,8, RoundingMode.HALF_UP);
                    break;
                case MULTIPLYING:
                    memory = memory.multiply(operationNumber);
                    break;
                case CLEAR:
                    memory = operationNumber;
                    break;
            }
            showResult();
        }
        catch (NumberFormatException e)
        {

            text.setText("Wrong number");
            isInserting = false;
        }

    }

    private void computeOperationValue() {
        try {
            operationNumber = BigDecimal.valueOf(Double.parseDouble(text.getText().toString()));
        }
        catch (NullPointerException e)
        {
            operationNumber = BigDecimal.valueOf(0.0);
        }
    }

    private void showResult() {
        //DecimalFormat df = new DecimalFormat("0.#");

        String s = memory.doubleValue() == memory.longValue() ? "" + memory.longValue() : "" + memory;
        text.setText(s);
    }

    // Number buttons onClick methods
    public void NumberOneClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("1");
    }

    public void NumberTwoClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("2");
    }

    public void NumberThreeClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("3");
    }
    public void NumberFourClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("4");
    }
    public void NumberFiveClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("5");
    }
    public void NumberSixClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("6");
    }
    public void NumberSevenClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("7");
    }
    public void NumberEightClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("8");
    }
    public void NumberNineClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("9");
    }
    public void NumberZeroClicked(View view)
    {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
        text.append("0");
    }

    public void dotClicked(View view){
        if (!isDotUsed)
        {
            if(!isInserting)
            {
                text.setText("");
                isInserting = true;
            }
            text.append(".");
            isDotUsed = true;
        }

    }




}
