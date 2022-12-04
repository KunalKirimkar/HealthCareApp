package com.kunalkirimkar.healthywealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    private SeekBar seekBarHeight, seekBarWeight;
    private Integer currentHeight, currentWeight;
    private TextView resultText, resultHeight, resultWeight;
    private Button calculateBmi, calculateBmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        seekBarHeight = findViewById(R.id.seekBar1);
        seekBarWeight = findViewById(R.id.seekBar2);
        resultText = findViewById(R.id.textResult);
        calculateBmi = findViewById(R.id.calculateBmiBtn);
        calculateBmr = findViewById(R.id.calculateBmrBtn);
        resultHeight = findViewById(R.id.resultHeight);
        resultWeight = findViewById(R.id.resultWeight);
        currentHeight = seekBarHeight.getProgress();
        currentWeight = seekBarWeight.getProgress();

        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setMax(300);
                currentHeight = progress;
                resultHeight.setText(progress + "/" + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                textView.setText(progress + "/" + seekBar.getMax());
            }
        });

        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setMax(150);
                currentWeight = progress;
                resultWeight.setText(progress + "/" + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                textView.setText(progress + "/" + seekBar.getMax());
            }
        });

        calculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });

        calculateBmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmr();
            }
        });
    }

    public void calculateBmi() {
        String inputHeight = currentHeight.toString();
        String inputWeight = currentWeight.toString();

        float weight = Float.parseFloat(inputWeight);
        float height = Float.parseFloat(inputHeight)/100;

        float bmiValue = calculateBMI(weight, height);

        String bmiInterpretation = interpretBMI(bmiValue);

        resultText.setText(bmiValue + " kg/m2 - " + bmiInterpretation);
    }

    private float calculateBMI (float weight, float height) {
        return weight / (height * height);
    }

    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return "Severely underweight";
        } else if (bmiValue < 18.5) {
            return "Underweight";
        } else if (bmiValue < 25) {
            return "Normal";
        } else if (bmiValue < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public void calculateBmr() {
        String inputHeight = currentHeight.toString();
        String inputWeight = currentWeight.toString();
        String getAge = getIntent().getStringExtra("age");
        String getGender = getIntent().getStringExtra("gender");

        double result;
        double weight = Double.parseDouble(inputWeight);
        double height = Double.parseDouble(inputHeight);
        double age = Double.parseDouble(getAge);

//        DecimalFormat format = new DecimalFormat("0.00");
        if (getGender.equals("Male")) {
            result =66.4730 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * age);
        } else {
            result = 655.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * age);
        }
        resultText.setText(result+" kcal");
    }
}