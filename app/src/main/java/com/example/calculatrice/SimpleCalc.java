package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleCalc extends AppCompatActivity {

    private TextView resultat;
    private int firstNumber =0;
    private int secondNumber =0;
    private Operations operator=null;
    private boolean isOp1=true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultat=findViewById(R.id.resultat);
        Button btnEquals=findViewById(R.id.btnequals);
        btnEquals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compute();
            }
        });

        Button btnCancel=findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clear();
            }
        });




    }









    public void compute() {
        if(!isOp1){
            switch(operator) {
                case PLUS : firstNumber = firstNumber + secondNumber; break;
                case MOINS : firstNumber = firstNumber - secondNumber; break;
                case FOIS : firstNumber = firstNumber * secondNumber; break;
                case DIV : firstNumber = firstNumber / secondNumber; break;
                default : return; // do nothing if no operator
            }
            secondNumber = 0;
            isOp1 = true;
            updateDisplay();
        }
    }


    public void setOperator(View v) {
        switch (v.getId()) {
            case R.id.btnAdd  : operator=Operations.PLUS;  break;
            case R.id.btnMoins : operator=Operations.MOINS; break;
            case R.id.btnMultiply  : operator=Operations.FOIS;  break;
            case R.id.btnDivide   : operator=Operations.DIV;   break;
            default :
                Toast.makeText(this, "Opérateur non reconnu",Toast.LENGTH_LONG);
                return; // do nothing if no operator
        }
        isOp1=false;
        updateDisplay();
    }



    public void addNumber(View v){
        try {
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOp1) {
                firstNumber = firstNumber * 10 + val;
                updateDisplay();
            } else {
                secondNumber = secondNumber * 10 + val;
                updateDisplay();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }

    private void updateDisplay() {
        int v= firstNumber;
        if(!isOp1) {
            v= secondNumber;
        }
        resultat.setText(String.format("%9d",v));
    }

    private void clear() {
        firstNumber = 0;
        secondNumber = 0;
        operator = null;
        isOp1 = true;
        updateDisplay();
    }

}
