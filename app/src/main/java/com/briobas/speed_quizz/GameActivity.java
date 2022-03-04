package com.briobas.speed_quizz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.briobas.speed_quizz.Controllers.QuestionManager;
import com.briobas.speed_quizz.Models.Question;
import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {
    private Button BTN_Rejouer, BTN_Menu, BTN_Jouer1, BTN_Jouer2;
    private TextView TX_ScorePlayer1, TX_ScorePlayer2, TX_Name1, TX_Name2, TX_QuestionPlayer1, TX_QuestionPlayer2;

    Handler handler;
    Runnable questionRunnable=null;

    QuestionManager QuestionManager;
    Question questionEnCours;
    ArrayList<Question> ListeQuestion;

    int v = 5;

    public void getId() {
        BTN_Menu = findViewById(R.id.BTN_Menu);
        BTN_Rejouer = findViewById(R.id.BTN_Rejouer);
        BTN_Jouer1 = findViewById(R.id.BTN_Jouer1);
        BTN_Jouer2 = findViewById(R.id.BTN_Jouer2);
        TX_ScorePlayer1 = findViewById(R.id.TX_ScorePlayer2);
        TX_QuestionPlayer1 = findViewById(R.id.TX_QuestionPlayer1);
        TX_QuestionPlayer2 = findViewById(R.id.TX_QuestionPlayer2);
        TX_ScorePlayer2 = findViewById(R.id.TX_ScorePlayer1);
        TX_Name1 = findViewById(R.id.TX_Name1);
        TX_Name2 = findViewById(R.id.TX_Name2);

        HideButton();

        BTN_Rejouer.setVisibility(View.GONE);
        BTN_Menu.setVisibility(View.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        QuestionManager = new QuestionManager(this);
        ListeQuestion = QuestionManager.getQuestionList();

        getId();
        Intent gameActivity = getIntent();
        String Player1 = gameActivity.getStringExtra("Player1");
        String Player2 = gameActivity.getStringExtra("Player2");

        TX_Name1.setText(Player2);
        TX_Name2.setText(Player1);

        compteur();

        BTN_Jouer1.setOnClickListener(view -> AjoutPoint(TX_ScorePlayer1));

        BTN_Jouer2.setOnClickListener(view -> AjoutPoint(TX_ScorePlayer2));

        BTN_Menu.setOnClickListener(view -> {
            Intent mainActivity = new Intent(GameActivity.this, MainActivity.class);
            GameActivity.this.startActivity(mainActivity);
        });

        BTN_Rejouer.setOnClickListener(view -> {
            Intent gameActivity1 = new Intent(GameActivity.this, GameActivity.class);
            gameActivity1.putExtra("Player1", TX_Name1.getText().toString());
            gameActivity1.putExtra("Player2", TX_Name2.getText().toString());
            GameActivity.this.startActivity(gameActivity1);
        });
    }
    /*
     * Fait un décompte de 5 à 0 avant de lancer le jeu.
     */
   void compteur() {
       handler = new Handler();
       questionRunnable = new Runnable() {
           @Override
           public void run() {
               TX_QuestionPlayer1.setText(String.valueOf(v));
               TX_QuestionPlayer2.setText(String.valueOf(v));
               if (v < 1) {
                   handler.removeCallbacks(this);
                   TX_QuestionPlayer1.setText("Go");
                   TX_QuestionPlayer2.setText("Go");
                   question();
               } else {
                   v--;
                   handler.postDelayed(this, 1000);
               }
           }
       };
       handler.postDelayed(questionRunnable, 1000);
   }


    void question() {
        Bundle bundle = getIntent().getExtras();
        float TimerValue = bundle.getFloat("Slider", 3f);
        int nbreQuestion = bundle.getInt("NbreQuestion", 9);
        handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if (QuestionManager.isEmpty(QuestionManager.getQuestionList())) {
                    handler.removeCallbacks(this);
                    BTN_Rejouer.setVisibility(View.VISIBLE);
                    BTN_Menu.setVisibility(View.VISIBLE);

                    String gagnant = joueurGagnant(Integer.parseInt(TX_ScorePlayer1.getText().toString()), Integer.parseInt(TX_ScorePlayer2.getText().toString()));
                    TX_QuestionPlayer1.setText(gagnant);
                    TX_QuestionPlayer2.setText(gagnant);
                } else {
                    Question question = QuestionManager.randomQuestion(ListeQuestion, nbreQuestion);
                    String Liste = question.getQuestion();
                    ShowButton();
                    TX_QuestionPlayer1.setText(Liste);
                    TX_QuestionPlayer2.setText(Liste);

                    questionEnCours = question;

                    handler.postDelayed(this, 3000);
                }
            }
        };
        handler.postDelayed(questionRunnable, 1000);
    }

    String joueurGagnant(int ScoreJoueur1, int ScoreJoueur2) {
        /*
         * Détermine le gagnant
         */
        String vainqueur;
        if(ScoreJoueur1 > ScoreJoueur2) {
            vainqueur = String.format("%s a gagné", TX_Name1.getText().toString());
        } else if(ScoreJoueur2 > ScoreJoueur1) {
            vainqueur = String.format("%s a gagné", TX_Name2.getText().toString());
        } else {
            vainqueur = "égalité";
        }
        return vainqueur;
    }

    void AjoutPoint(TextView TX) {
        /*
         * Rajoute / enlève des points par rapport à la réponse
         */
        int ListeReponse = questionEnCours.isReponse();
        int value = Integer.parseInt(TX.getText().toString());
        if (ListeReponse == 1) {
            value++;
        } else if(ListeReponse == 0 && value != 0) {
            value--;
        }
        TX.setText(String.valueOf(value));
        HideButton();
    }

    void HideButton() {
        BTN_Jouer1.setEnabled(false);
        BTN_Jouer2.setEnabled(false);
    }

    void ShowButton() {
        BTN_Jouer1.setEnabled(true);
        BTN_Jouer2.setEnabled(true);
    }
}
