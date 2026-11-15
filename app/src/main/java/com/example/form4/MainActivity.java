package com.example.form4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etWeight, etHeight;
    private RadioGroup rgGender;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dane();
        oblicz();

    }
    private void dane(){
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        rgGender = findViewById(R.id.rgGender);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
    }

    private void oblicz(){
    btnCalculate.setOnClickListener(v -> {
        if(valizadacja()){
            kalkulatorBMI();
        }
    });}

    private boolean valizadacja(){
        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();

        if (weight.isEmpty()) {
            etWeight.setError(getString(R.string.error_weight));
            etWeight.requestFocus();
            return false;
        }

        if (height.isEmpty()) {
            etHeight.setError(getString(R.string.error_height_empty));
            etHeight.requestFocus();
            return false;
        }

        double dWeight = Double.parseDouble(weight);
        if (dWeight <= 0) {
            etWeight.setError(getString(R.string.error_weight));
            etWeight.requestFocus();
            return false;
        }

        double dHeight = Double.parseDouble(height);
        if (dHeight <= 0 || dHeight > 300) {
            etHeight.setError(getString(R.string.error_height_zero));
            etHeight.requestFocus();
            return false;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, getString(R.string.error_gender), Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
        };

        private void kalkulatorBMI(){
            double weigth = Double.parseDouble(etWeight.getText().toString());
            double height = Double.parseDouble(etHeight.getText().toString());
            double heightM = height / 100;

            double BMI = weigth / (heightM*heightM);

            String category;
            if (BMI < 18.5) {
                category = getString(R.string.category_underweight);
            } else if (BMI < 25.0) {
                category = getString(R.string.category_normal);
            } else if (BMI < 30.0) {
                category = getString(R.string.category_overweight);
            } else {
                category = getString(R.string.category_obese);
            }


            String resultText = String.format(getString(R.string.bmi_result_format), BMI, category);
            tvResult.setText(resultText);
        }


}