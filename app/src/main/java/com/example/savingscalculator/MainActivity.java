package com.example.savingscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle;
    EditText edtYearIncome;
    TextView txtWeeklySavings;
    SeekBar seekBar;
    Button btnReset;
    TextView txtTotalSavings;
    final  int weeksInOneYear=52;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle=findViewById(R.id.txtTitle);
        edtYearIncome=findViewById(R.id.editTextNumberDecimal);
        txtWeeklySavings=findViewById(R.id.txtWeeklySavings);
        seekBar =findViewById(R.id.seekBar);
        btnReset=findViewById(R.id.btnReset);
        txtTotalSavings=findViewById(R.id.txtTotalSavings);

        //seekBar setup

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtWeeklySavings.setText("Weekly saving:\n$" + i);
                //display the yearly saving
                int totalSavingPerYear=i * 52;

                txtTotalSavings.setText(totalSavingPerYear + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /* seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtWeeklySavings.setText("Weekly saving:\n$" + i );

                //display the yearly saving
                int totalSavingPerYear=i * 52;

                txtWeeklySavings.setText(totalSavingPerYear + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });*/


        //setup Edit txt
        edtYearIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
        //calculate max saving
                String yearlyIncome= edtYearIncome.getText().toString() ;
                double yearlyIncomeAsNumber =0;
                if(!yearlyIncome.isEmpty()){
                    yearlyIncomeAsNumber=Double.parseDouble(yearlyIncome);
                }
                double weeklyIncome = yearlyIncomeAsNumber/weeksInOneYear;
                int maxSavingAllowed = (int) (weeklyIncome/2);

                //setuponSlider
                seekBar.setMax(maxSavingAllowed);

            }
        });

        //reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editTxtReset
                edtYearIncome.setText(null);
                edtYearIncome.dispatchDisplayHint(View.VISIBLE);

                //resetSeekBar
                seekBar.setProgress(0);
                seekBar.setMax(100);

                //TotalYEarlySavingReset
                txtTotalSavings.setText("Total");
            }
        });




    }


}