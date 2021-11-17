package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    TextView tvCalculator;

    String operand1 = "";
    String operand2 = "";
    String operator = "";
    boolean isOperatorActive = false;
    String operatorSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCalculator = findViewById(R.id.tvCalculator);
    }

    public void cleanup(String ans) {
        isOperatorActive = false;
        operand1 = String.valueOf(ans);
        operand2 = "";
        operator = "";
    }


    public void buttonClicked(View view) {
        // Type check for safety
        if (view instanceof Button) {
            Button button = (Button) view;
            String text = button.getText().toString();
            if (text.compareTo("AC") == 0) {
                tvCalculator.setText("");
                cleanup("");
            } else {
                // operators detection
                if (text.compareTo("*") == 0 || text.compareTo("+") == 0 || text.compareTo("-") == 0 || text.compareTo("%") == 0) {
                    if (operand1.isEmpty())
                        return;
                    operatorSelected = text;
                    isOperatorActive = true;
                }
                // input for numbers and decimal
                else if (text.matches("[0-9]+") || text.compareTo(".") == 0) {
                    if (isOperatorActive) {
                        operand2 = operand2 + text;
                        tvCalculator.setText(operand2);
                    } else {
                        operand1 = operand1 + text;
                        tvCalculator.setText(operand1);
                    }
                }
                // calculation
                else if (text.compareTo("=") == 0) {
                    if (operand1.isEmpty() || operand2.isEmpty())
                        return;
                    double ans = 0;
                    try {
                        switch (operatorSelected) {
                            case "-":
                                ans = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                                if (ans % 1 == 0)
                                    tvCalculator.setText(String.valueOf((int) ans));
                                else
                                    tvCalculator.setText(String.valueOf(ans));
                                break;
                            case "+":
                                ans = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                                if (ans % 1 == 0)
                                    tvCalculator.setText(String.valueOf((int) ans));
                                else
                                    tvCalculator.setText(String.valueOf(ans));
                                break;
                            case "%":
                                ans = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                                if (ans % 1 == 0)
                                    tvCalculator.setText(String.valueOf((int) ans));
                                else
                                    tvCalculator.setText(String.valueOf(ans));
                                break;

                            case "*":
                                ans = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                                if (ans % 1 == 0)
                                    tvCalculator.setText(String.valueOf((int) ans));
                                else
                                    tvCalculator.setText(String.valueOf(ans));
                                break;
                        }
                    } catch (Exception ex) {
                        // unknown error
                        tvCalculator.setText("NAN");
                    }
                    // clear inputs and constants
                    cleanup(String.valueOf(ans));

                }
            }

        }
    }
}