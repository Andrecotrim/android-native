package com.andrecotrim.neonmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andrecotrim.neonmobile.activity.LoggedActivity;
import com.andrecotrim.neonmobile.activity.PaymentCandidatesActivity;
import com.andrecotrim.neonmobile.activity.SendMoneyActivity;
import com.andrecotrim.neonmobile.util.ResponseService;
import com.andrecotrim.neonmobile.util.ConstantsUtils;
import com.andrecotrim.neonmobile.util.RestOperation;

public class MainActivity extends AppCompatActivity implements ResponseService {

    private TextView textToUser;
    private EditText ip;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToUser = (TextView) findViewById(R.id.splash_text_id) ;
        ip = (EditText) findViewById(R.id.ip) ;

        btnStart = (Button) findViewById(R.id.btnStart) ;
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ENDEREÇO DO SERVIDOR
                ConstantsUtils.URL_SERVER = ip.getText() == null || ip.getText().toString().trim().isEmpty() ? ConstantsUtils.URL_SERVER : ip.getText().toString().trim();
                Toast.makeText(MainActivity.this, ConstantsUtils.URL_SERVER, Toast.LENGTH_LONG).show();

                // BUILD PARAMETERS
                Uri.Builder builder = new Uri.Builder()
                        .scheme("http")
                        .encodedAuthority(ConstantsUtils.URL_SERVER)
                        .appendPath(ConstantsUtils.ROUTE_GENERATE_TOKEN)
                        .appendQueryParameter("nome", "André Cotrim")
                        .appendQueryParameter("email", "cotrim.andre@gmail.com");
                String restResultURL = builder.build().toString();

                try {
                    RestOperation restOperation = new RestOperation( null, MainActivity.this);
                    restOperation.execute(restResultURL);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "PROBLEMAS AO ACESSAR O SERVIDOR" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void responseService(Boolean responseSuccess, String retornoApi) {
        if (retornoApi != null) {
            // TODO: VALIDAR TOKEN RETORNO
            ConstantsUtils.TOKEN = retornoApi;

            Intent intent = new Intent (this, LoggedActivity.class);
            startActivity(intent);

        } else {
            textToUser.setText("Desculpe, nossos servidores estão temporariamente indisponíveis. Tente novamente daqui a pouco!");
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }).start();*/
        }

    }
}
