package com.example.punna.calci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newnum;
    private TextView textView;
    //variables to hold operands and type of calculations
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingoperation = "=";
    private final String textcontents="TEXT_CONTENTS";
    private final String operand="OPERAND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newnum = (EditText) findViewById(R.id.newnum);
        textView = (TextView) findViewById(R.id.textView);
        Button button0 = (Button) findViewById(R.id.num0);
        Button button1 = (Button) findViewById(R.id.num1);
        Button button2 = (Button) findViewById(R.id.num2);
        Button button3 = (Button) findViewById(R.id.num3);
        Button button4 = (Button) findViewById(R.id.num4);
        Button button5 = (Button) findViewById(R.id.num5);
        Button button6 = (Button) findViewById(R.id.num6);
        Button button7 = (Button) findViewById(R.id.num7);
        Button button8 = (Button) findViewById(R.id.num8);
        Button button9 = (Button) findViewById(R.id.num9);
        Button buttondot = (Button) findViewById(R.id.dot);

        Button buttonequals = (Button) findViewById(R.id.equalto);
        Button buttonmultiply = (Button) findViewById(R.id.multi);
        Button buttonadd = (Button) findViewById(R.id.plus);
        Button buttonminus = (Button) findViewById(R.id.minus);
        Button buttondivide = (Button) findViewById(R.id.divide);
        Button clear=(Button) findViewById(R.id.clear1);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newnum.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x=newnum.getText().toString();
                if(x.length()!=0) {
                    x = x.substring(0, x.length() - 1);
                }
                newnum.setText(x);
            }
        });


        View.OnClickListener oplistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newnum.getText().toString();
                try {
                    Double doublevalur=Double.valueOf(value);
                    performop(doublevalur,op);
                }catch (NumberFormatException e){
                    newnum.setText("");
                }
                pendingoperation = op;
                textView.setText(pendingoperation);
            }
        };
        buttonadd.setOnClickListener(oplistener);
        buttonminus.setOnClickListener(oplistener);
        buttondivide.setOnClickListener(oplistener);
        buttonmultiply.setOnClickListener(oplistener);
        buttonequals.setOnClickListener(oplistener);


        Button buttonneg= (Button)findViewById(R.id.buttonNeg);
        buttonneg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String value=newnum.getText().toString();
                if(value.length()==0)
                    newnum.setText("-");
                else {
                    try {
                        Double doublevalue = Double.valueOf(value);
                        doublevalue*=-1;
                        newnum.setText(doublevalue.toString());
                    }catch(NumberFormatException e){
                        newnum.setText("");
                    }
                }
            }
        });
        Button allclear=(Button) findViewById(R.id.allclear);
        allclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand1=null;
                operand2=null;
                pendingoperation="=";
                newnum.setText("");
                result.setText("");
            }
        });



    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingoperation=savedInstanceState.getString(textcontents);
        textView.setText(pendingoperation);
        operand1=savedInstanceState.getDouble(operand);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(textcontents,textView.getText().toString());
        if(operand1!=null) {
            outState.putDouble(operand, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    private void performop(Double value, String operation) {
        if (null == operand1) {
            operand1 = value;
        } else {
            operand2 = value;
            if (pendingoperation.equals("=")) {
                pendingoperation = operation;
            }
            switch (pendingoperation) {
                case "=": {
                    operand1 = operand2;
                    break;
                }
                case "+": {
                    operand1 += operand2;
                    break;
                }
                case "-": {
                    operand1 -= operand2;
                    break;
                }
                case "*": {
                    operand1 *= operand2;
                    break;
                }
                case "/": {
                    if (operand2 == 0.0)
                        operand1 = 0.0;
                    else {
                        operand1 /= operand2;
                    }
                    break;
                }

            }

        }
        result.setText(operand1.toString());
        newnum.setText("");

    }
}
