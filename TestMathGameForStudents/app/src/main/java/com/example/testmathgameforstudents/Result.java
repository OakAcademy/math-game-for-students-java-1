package com.example.testmathgameforstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView txtResultScore;
    Button btnPlayAgain;
    Button btnExit;
    Intent intent;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtResultScore = findViewById(R.id.txtScoreResult);
        btnExit = findViewById(R.id.btnExitGame);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        //accessing value that we sent from Game Activity
        intent = getIntent();
        score = intent.getIntExtra("Score",0);
        //set text with score
        //convert int to string first
        String strScore= String.valueOf(score);
        txtResultScore.setText("Your Score: "+ strScore);
        System.out.println("score ="+strScore);
        Log.d("result :","score:"+score);


        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Result.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}