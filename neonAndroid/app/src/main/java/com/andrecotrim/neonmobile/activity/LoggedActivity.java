package com.andrecotrim.neonmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andrecotrim.neonmobile.R;

public class LoggedActivity extends AppCompatActivity {
    private Button btnSendMoney;
    private Button btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        setup();
    }

    private void setup() {
        btnSendMoney = (Button) findViewById(R.id.btnSendMoney);
        btnHistory = (Button) findViewById(R.id.btnHistory);

        btnSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, PaymentCandidatesActivity.class);
                startActivity(intent);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, PayListActivity.class);
                startActivity(intent);
            }
        });


    }
}
