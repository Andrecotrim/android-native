package com.andrecotrim.neonmobile.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class RestOperation extends AsyncTask<String, Void, Void> {

    private Context context;
    private ProgressDialog progressDialog;
    private String contentJsonResponse;
    private String error;
    private String data;

    public RestOperation(String jsonPostParameters, Context context) {
        this.context = context;
        this.data = jsonPostParameters == null || jsonPostParameters.trim().isEmpty() ? "" : jsonPostParameters.trim();
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setTitle("Aguarde enquanto buscamos seus dados...");
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        BufferedReader br = null;
        URL url;
        try {
            url = new URL(params[0]);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            OutputStreamWriter outputStreamWr = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWr.write(data);
            outputStreamWr.flush();

            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            contentJsonResponse = sb.toString();

        } catch (MalformedURLException e) {
            error = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            error = e.getMessage();
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        try {
            // Utilities.exibirMensagens(context, "Teste", "RETORNO DO SERVIDOR: " + contentJsonResponse).show();
            ResponseService response = (ResponseService) context;
            response.responseService(true, contentJsonResponse);
        } catch (Exception e) {
            ((ResponseService) context).responseService(false, null);
            Toast.makeText(context, "PROBLEMAS AO ACESSAR O SERVIDOR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }
}
