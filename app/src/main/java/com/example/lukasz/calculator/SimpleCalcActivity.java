package com.example.lukasz.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SimpleCalcActivity extends AppCompatActivity {

    TextView text;
    Double memory = 0.0;
    Double operationNumber;
    boolean isCTapped = false;
    boolean isInserting = true;
    boolean isDotUsed = false;

    public enum OperationStatus {
        CLEAR, ADDITIONING, DIVIDING, SUBSTRUCTING, MULTIPLYING
    }

    OperationStatus status = OperationStatus.CLEAR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);
         text = findViewById(R.id.screen);
    }

    // C/CE
    public void BackspaceClicked(View view) {

        text.setText("");
        if (isCTapped)
        {
            memory = 0.0;
            status = OperationStatus.CLEAR;
            isCTapped = false;
            return;
        }
        isCTapped = true;
    }

    public void ACClicked(View view){
        text.setText("");
        memory = 0.0;
        status = OperationStatus.CLEAR;
    }

    //SimpleOperations OnClick
    public void Addition(View view)
    {
        if(status == OperationStatus.ADDITIONING) return; // causing that spamming with plus don't affect result
        memory += Double.parseDouble(text.getText().toString());
        status = OperationStatus.ADDITIONING;
        isInserting = false;
    }

    public void Substructing(View view)
    {
        if(status == OperationStatus.SUBSTRUCTING) return; // causing that spamming with plus don't affect result
        memory += Double.parseDouble(text.getText().toString());
        status = OperationStatus.SUBSTRUCTING;
        isInserting = false;
    }

 public void Dividing(View view)
    {
        if(status == OperationStatus.DIVIDING) return; // causing that spamming with plus don't affect result
        memory += Double.parseDouble(text.getText().toString());
        status = OperationStatus.DIVIDING;
        isInserting = false;
    }

 public void Multiplying(View view)
    {
        if(status == OperationStatus.MULTIPLYING) return; // causing that spamming with plus don't affect result
        memory += Double.parseDouble(text.getText().toString());
        status = OperationStatus.MULTIPLYING;
        isInserting = false;
    }

    public void EqualsButton (View view){

        if(isInserting)
        {
            operationNumber = Double.parseDouble(text.getText().toString());
        }
        isInserting = false;
        switch (status){
            case ADDITIONING:  memory += operationNumber; break;
            case SUBSTRUCTING: memory -= operationNumber; break;
            case DIVIDING:     memory /= operationNumber; break;
            case MULTIPLYING:  memory *= operationNumber; break;
            case CLEAR: memory = operationNumber; break;
        }
        showResult();

    }

    private void showResult() {
        //DecimalFormat df = new DecimalFormat("0.#");

        String s = memory.longValue() == memory ? "" + memory.longValue() : "" + memory;
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
