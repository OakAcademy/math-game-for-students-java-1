package com.example.testmathgameforstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameSubtraction extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 60000;
    TextView txtScore;
    TextView txtLife;
    TextView txtTime;
    Button btnOk;
    Button btnNext;
    EditText editAnswer;
    TextView txtQuestion;
    Random random = new Random();
    int number1;
    int number2;
    int userAnswer;
    int realAnswer;
    int score = 0;
    int userLife = 3;

    //VARIABLES FOR TIMER I.E 60 SECONDS FOR EACH QUESTiON
    //60 SECONDS = 60000 MILLISECONDS
    CountDownTimer countDownTime;
    boolean timer_running;
    long time_left_in_milis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_subtraction);

        txtScore = findViewById(R.id.txtScore_0);
        txtLife = findViewById(R.id.txtLife_0);
        txtTime = findViewById(R.id.txtTime_0);

        btnOk = findViewById(R.id.btnOk);
        btnNext = findViewById(R.id.btnNextQuestion);

        editAnswer = findViewById(R.id.editTextAnswer);
        editAnswer.setText("");

        txtQuestion = findViewById(R.id.txtQuestion);

        //calling function gameCONTINUE;
        gameContinue();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pause timer when user clicks OK
                pauseTimer();

                //checking if user input is empty then app should not crash
                if (!editAnswer.getText().toString().equals("")) {

                    //user can click the Ok button just once time
                    btnOk.setClickable(false);

                    //getting user's answer
                    userAnswer = Integer.parseInt(editAnswer.getText().toString());

                    //calculating result on runtime.
                    realAnswer = number1 - number2;

                    //check if user answer is equal to actual answer
                    if (userAnswer == realAnswer) {
                        //increase score by 10 and clear edit text
                        //print message for congrats
                        txtQuestion.setText(R.string.correctAnsMsg);
                        score = score + 10;
                        txtScore.setText(String.valueOf(score));


                    }
                    else {
                        //print sorry message
                        txtQuestion.setText(R.string.wrongAnsMsg);
                        //decrease life by 1
                        userLife = userLife - 1;
                        txtLife.setText(String.valueOf(userLife));
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),R.string.enterAnswer,Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnOk.setClickable(true);

                editAnswer.setText("");
                pauseTimer();
                resetTimer();


                if (userLife <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.gameOver, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Result.class);
                    intent.putExtra("Score", score);
                    startActivity(intent);
                    finish();

                }
                else{

                    gameContinue();
                }
            }
        });
    }

    public void gameContinue() {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        txtQuestion.setText(number1 + " - " + number2);

        startTimer();
    }

    //function for timer
    public void startTimer() {

        countDownTime = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife - 1;
                txtQuestion.setText(R.string.timeIsUp);
            }
        }.start();

        timer_running = true;

    }

    public void updateText() {
        int second = (int) (time_left_in_milis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        txtTime.setText(time_left);
    }

    public void pauseTimer() {

        countDownTime.cancel();
        timer_running = false;

    }

    public void resetTimer() {
        time_left_in_milis = START_TIME_IN_MILLIS;
        updateText();

    }
}