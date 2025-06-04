package com.example.dividentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etAmount, etRate, etMonths;
    TextView tvResult;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the top app bar
        setSupportActionBar(findViewById(R.id.topAppBar));
        getSupportActionBar().setTitle("DividentApplication");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize UI components
        etAmount = findViewById(R.id.etAmount);
        etRate = findViewById(R.id.etRate);
        etMonths = findViewById(R.id.etMonths);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(v -> {
            try {
                double amount = Double.parseDouble(etAmount.getText().toString());
                double rate = Double.parseDouble(etRate.getText().toString()) / 100;
                int months = Integer.parseInt(etMonths.getText().toString());

                if (months < 1 || months > 12) {
                    tvResult.setText("Months must be between 1 and 12");
                    return;
                }

                double monthlyDividend = (rate / 12) * amount;
                double totalDividend = monthlyDividend * months;

                tvResult.setText(String.format("Total Dividend: RM %.2f", totalDividend));
            } catch (Exception e) {
                tvResult.setText("Please enter valid numbers.");
            }
        });
    }

    // Inflate the top menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    // Handle menu and back icon actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish(); // Back button in toolbar
        } else if (id == R.id.menu_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else if (id == R.id.menu_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
