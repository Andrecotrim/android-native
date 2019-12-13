package com.andrecotrim.neonmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andrecotrim.neonmobile.R;
import com.andrecotrim.neonmobile.components.MoneyMaskWatcher;
import com.andrecotrim.neonmobile.util.ConstantsUtils;
import com.andrecotrim.neonmobile.util.ResponseService;
import com.andrecotrim.neonmobile.util.RestOperation;
import com.andrecotrim.neonmobile.vo.Contact;

public class SendMoneyActivity extends AppCompatActivity implements ResponseService {
    private Button sendMoney;
    private EditText money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            Contact contact = (Contact) params.get("CANDIDATO");

            money = (EditText) findViewById(R.id.moneyId);
            money.addTextChangedListener(new MoneyMaskWatcher(money));


            sendMoney = (Button) findViewById(R.id.sendMoneyId);
            sendMoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // BUILD PARAMETERS
                    Uri.Builder builder = new Uri.Builder()
                            .scheme("http")
                            .encodedAuthority(ConstantsUtils.URL_SERVER)
                            .appendPath(ConstantsUtils.ROUTE_GENERATE_TOKEN)
                            .appendQueryParameter("nome", "Send Money to André")
                            .appendQueryParameter("email", "cotrim.andre@gmail.com");
                    String restResultURL = builder.build().toString();

                    try {
                        RestOperation restOperation = new RestOperation( null, SendMoneyActivity.this);
                        restOperation.execute(restResultURL);
                    } catch (Exception e) {
                        Toast.makeText(SendMoneyActivity.this, "PROBLEMAS AO ACESSAR O SERVIDOR" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    public void responseService(Boolean responseSuccess, String retornoApi) {
        Toast.makeText(SendMoneyActivity.this, "Dinheiro Enviado", Toast.LENGTH_LONG).show();
        finish();
    }
}
