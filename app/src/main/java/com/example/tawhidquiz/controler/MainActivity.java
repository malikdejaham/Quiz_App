package com.example.tawhidquiz.controler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tawhidquiz.R;

public class MainActivity extends AppCompatActivity {

    Button letsGoFab;

    public int scoreFinal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        letsGoFab = findViewById(R.id.letsGoFab);


        letsGoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent QuizActivityIntent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(QuizActivityIntent);
            }
        });


        Intent intent = getIntent();
        scoreFinal = intent.getIntExtra("EXTRA_SCORE", scoreFinal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contact_item:
                Intent contactIntent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contactIntent);
                return true;
            case R.id.aboutUs_item:
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case R.id.donate_item:
                Intent donateIntent = new Intent(MainActivity.this, DonateActivity.class);
                startActivity(donateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}