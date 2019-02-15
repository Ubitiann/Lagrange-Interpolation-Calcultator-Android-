package com.example.abc.lagrangeinterpolation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.design.widget.Snackbar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Double> vx = new ArrayList<>();
    ArrayList<Double> vy = new ArrayList<>();
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText valX = (EditText) findViewById(R.id.valX);
        final EditText valY = (EditText) findViewById(R.id.valY);
        final EditText value = (EditText) findViewById(R.id.value);
        final TextView textoValor = (TextView) findViewById(R.id.resultText);
        Button addCoord = findViewById(R.id.btnAdd);
        Button clearCoord = findViewById(R.id.btnDel);
        Button calculeX = findViewById(R.id.btnCal);
        addCoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valX.getText().toString().isEmpty() || valY.getText().toString().isEmpty()) {
                    snackbar=Snackbar.make(view, "Value of X or Y can't be empty", Snackbar.LENGTH_LONG).setAction("Action", null);
               View view1=snackbar.getView();
                    TextView textView= (TextView) view1.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    snackbar.show();
                } else {
                    double x = Double.parseDouble(valX.getText().toString());
                    double y = Double.parseDouble(valY.getText().toString());
                    vx.add(x);
                    vy.add(y);
                    snackbar=Snackbar.make(view, ("coordinates (" + Double.toString(x) + "," + Double.toString(y) + ") added"), Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    View view1=snackbar.getView();
                    TextView textView= (TextView) view1.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    snackbar.show();
                }
            }
        });

        clearCoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vx.clear();
                vy.clear();
                snackbar=Snackbar.make(view, "All coordinates were eliminated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null);
                View view1=snackbar.getView();
                TextView textView= (TextView) view1.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                snackbar.show();
            }
        });

        calculeX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value.getText().toString().isEmpty()) {
                    snackbar=Snackbar.make(view, "Please enter a value to evaluate", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    View view1=snackbar.getView();
                    TextView textView= (TextView) view1.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    snackbar.show();
                } else if (vx.isEmpty() || vy.isEmpty()) {
                    snackbar=Snackbar.make(view, "No coordinates entered", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    View view1=snackbar.getView();
                    TextView textView= (TextView) view1.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    snackbar.show();
                } else {
                    double val = Double.parseDouble(value.getText().toString());
                    double result = -1;
                    double[] x = new double[vx.size()];
                    double[] y = new double[vy.size()];
                    for (int i = 0; i < vx.size(); i++) {
                        x[i] = vx.get(i);
                        y[i] = vy.get(i);
                    }
                    result = LagrangeI(x, y, val);
                    textoValor.setText("The result of evaluating the value is" + '\n' + Double.toString(result));
                }

            }
        });
    }

    public double LagrangeI(double [] coordX, double[] coordY, double val){
        int n = coordX.length;
        double prod;
        double[] aux = new double[n];
        double acum = 0;

        for(int i = 0; i < n; i++){

            prod = 1;

            for(int j = 0; j < n; j++){

                if(j != i){
                    prod *= (val- coordX[j])/(coordX[i]- coordX[j]);
                }
            }
            aux[i] = prod;
            acum += (aux[i]*coordY[i]);
        }
        return acum;
    }

}

