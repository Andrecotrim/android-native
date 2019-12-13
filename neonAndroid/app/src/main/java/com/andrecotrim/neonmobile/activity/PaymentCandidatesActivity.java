package com.andrecotrim.neonmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.andrecotrim.neonmobile.R;
import com.andrecotrim.neonmobile.adapter.PayItemAdapter;
import com.andrecotrim.neonmobile.vo.Contact;

import java.util.ArrayList;
import java.util.List;

public class PaymentCandidatesActivity extends AppCompatActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_candidates);

        TextView toobarTitle = (TextView) findViewById(R.id.toobarTitle);
        toobarTitle.setText("Enviar Dinheiro");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.list);

        listarServicos();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        listarServicos();
    }

    public void listarServicos() {
        List<Contact> contacts = new ArrayList<Contact>();

        for (int i = 0; i < 10; i++) {
            Contact contact = new Contact(i, "Nome 1", "43999999999");
            contacts.add(contact);
        }

        PayItemAdapter adapter = new PayItemAdapter(this, contacts);
        listview.setAdapter(adapter);
    }
}
