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

    public void negationOfNumber(View view){

        if(getValueFromView().compareTo(BigDecimal.ZERO) >= 1)
        {
           String negation = "-" + text.getText();
           text.setText(negation);
        }
        else
        {
            text.setText(text.getText().subSequence(1,text.length()));
        }

    }

    //SimpleOperations OnClick
    public void addition(View view)
    {
        OperationOnClick(operationStatus.ADDITIONING);

    }

    private void OperationOnClick(operationStatus operationStatus) {
        if(status == operationStatus) {
            if (isInserting){
                computeResult();
                status = operationStatus;
                return;
            }
            else
                {
                return;// causing that spamming with plus don't affect result
                }
        }
        computeResult(); // in case operation was different
        status = operationStatus;
    }

    public void substructing(View view)
    {
        OperationOnClick(operationStatus.SUBSTRUCTING);
    }

 public void dividing(View view)
    {
        OperationOnClick(operationStatus.DIVIDING);
    }

 public void multiplying(View view)
    {
        OperationOnClick(operationStatus.MULTIPLYING);

    }

    private BigDecimal getValueFromView() {
        try
        {
            return BigDecimal.valueOf(Double.parseDouble(text.getText().toString()));
        }
        catch (NullPointerException e)
        {
           return BigDecimal.valueOf(0);
        }
    }

    public void EqualsButton (View view){

      computeResult();
    }

    private void computeResult() {
        try {
            if (isInserting) {
                operationNumber = getValueFromView();
            }
            isDotUsed = false;
            switch (status) {
                case ADDITIONING:
                    memory = memory.add(operationNumber);
                    showResult();
                    break;
                case SUBSTRUCTING:
                    memory = memory.subtract(operationNumber);
                    showResult();
                    break;
                case DIVIDING:
                    memory =  new BigDecimal(memory.doubleValue(), MathContext.DECIMAL32).divide(operationNumber,8, RoundingMode.HALF_UP);
                    showResult();
                    break;
                case MULTIPLYING:
                    memory = memory.multiply(operationNumber);
                    showResult();
                    break;
                case CLEAR:
                    memory = operationNumber;
                    break;
            }

        }
        catch (Exception e)
        {

            text.setText("Wrong number");
        }
        finally {
            isInserting = false;
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
        clearView();
        text.append("1");
    }

    private void clearView() {
        if(!isInserting)
        {
            text.setText("");
            isInserting = true;
        }
    }

    public void NumberTwoClicked(View view)
    {
        clearView();
        text.append("2");
    }

    public void NumberThreeClicked(View view)
    {
        clearView();
        text.append("3");
    }
    public void NumberFourClicked(View view)
    {
        clearView();
        text.append("4");
    }
    public void NumberFiveClicked(View view)
    {
        clearView();
        text.append("5");
    }
    public void NumberSixClicked(View view)
    {
        clearView();
        text.append("6");
    }
    public void NumberSevenClicked(View view)
    {
        clearView();
        text.append("7");
    }
    public void NumberEightClicked(View view)
    {
        clearView();
        text.append("8");
    }
    public void NumberNineClicked(View view)
    {
        clearView();
        text.append("9");
    }
    public void NumberZeroClicked(View view)
    {
        clearView();
        text.append("0");
    }

    public void dotClicked(View view){
        if (!isDotUsed)
        {
            clearView();
            text.append(".");
            isDotUsed = true;
        }

    }




}
