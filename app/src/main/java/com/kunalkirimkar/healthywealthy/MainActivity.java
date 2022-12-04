package com.kunalkirimkar.healthywealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText ageET;
    private RadioButton selectedGenderBtn;
    private RadioGroup genderGroup;
    private Button resultButton;
    private final String selectedGender = "Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ageET = findViewById(R.id.editTextAge);
        genderGroup = findViewById(R.id.genderGroup);
        resultButton = findViewById(R.id.checkResult);

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedGenderBtn = findViewById( checkedId);
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                i.putExtra("gender", selectedGender);
                i.putExtra("age", ageET.getText().toString());
                startActivity(i);
            }
        });
    }
}