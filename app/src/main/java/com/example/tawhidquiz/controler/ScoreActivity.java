package com.example.tawhidquiz.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tawhidquiz.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ScoreActivity extends AppCompatActivity {


    int scoreFinal;
    int totalQuestions;
    TextView finalScoreView;
    Button restartFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        restartFab = findViewById(R.id.restartFab);

        restartFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        scoreFinal = intent.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
        totalQuestions = intent.getIntExtra(QuizActivity.EXTRA_TOTALQUESTIONS,0);

        finalScoreView = findViewById(R.id.FinalScoreView);
        finalScoreView.setText("Vous avez obtenu un score de "  + scoreFinal+"/"+totalQuestions);




    }








}