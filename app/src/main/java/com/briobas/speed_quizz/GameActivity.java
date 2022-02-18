package com.briobas.speed_quizz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.briobas.speed_quizz.Controllers.QuestionManager;
import com.briobas.speed_quizz.Models.Question;


public class GameActivity extends AppCompatActivity {
    Button BTN_Rejouer, BTN_Menu;
    TextView TX_ScorePlayer1, TX_ScorePlayer2, TX_Name1, TX_Name2;
    Runnable questionRunnable=null;
    Handler handler;
    int v = 5;

    Question maQuestion = new Question();
    QuestionManager QuestionManager = new QuestionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        BTN_Menu = findViewById(R.id.BTN_Menu);
        BTN_Rejouer = findViewById(R.id.BTN_Rejouer);
        TX_ScorePlayer1 = findViewById(R.id.TX_ScorePlayer1);
        TX_ScorePlayer2 = findViewById(R.id.TX_ScorePlayer2);
        TX_Name1 = findViewById(R.id.TX_Name1);
        TX_Name2 = findViewById(R.id.TX_Name2);

        Intent gameActivity = getIntent();
        String Player1 = gameActivity.getStringExtra("Player1");
        String Player2 = gameActivity.getStringExtra("Player2");
        TX_Name1.setText(Player1);
        TX_Name2.setText(Player2);

        Compteur();
        HideButton();
    }

    public void Compteur() {
        handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                TX_ScorePlayer1.setText(String.valueOf(v));
                TX_ScorePlayer2.setText(String.valueOf(v));
                if (v < 1) {
                    handler.removeCallbacks(this);
                    TX_ScorePlayer1.setText("Go");
                    TX_ScorePlayer2.setText("Go");
                    Question();
                } else {
                    v--;
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(questionRunnable, 1000);
    }

    public void HideButton() {
        BTN_Rejouer.setVisibility(View.GONE);
        BTN_Menu.setVisibility(View.GONE);
    }

    public void Question() {
        handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if(QuestionManager.isEmpty(QuestionManager.getQuestionList())){
                    handler.removeCallbacks(this);
                    // Joueur Gagnant
                }else{
                    TX_ScorePlayer1.setText(String.valueOf(QuestionManager.randomQuestion(QuestionManager.getQuestionList())));
                    TX_ScorePlayer2.setText(String.valueOf(QuestionManager.randomQuestion(QuestionManager.getQuestionList())));
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(questionRunnable, 1000);
    }
}
