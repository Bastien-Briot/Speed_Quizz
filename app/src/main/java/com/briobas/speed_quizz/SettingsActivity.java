package com.briobas.speed_quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.briobas.speed_quizz.Controllers.QuestionManager;
import com.briobas.speed_quizz.Models.Question;
import com.google.android.material.slider.Slider;

import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    Slider Slider_Time;
    Button Save;
    EditText NbreQuestion;
    QuestionManager questionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Slider_Time = findViewById(R.id.slider);
        Save = findViewById(R.id.BTN_Enregistrer);
        NbreQuestion = findViewById(R.id.NbreQuestion);


        questionManager = new QuestionManager(this);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float SliderValue;
                SliderValue = Slider_Time.getValue();

                 if(Integer.parseInt(NbreQuestion.getText().toString()) > questionManager.getQuestionList().size()) {
                    NbreQuestion.setText("0");
                 }

                int value = Integer.parseInt(NbreQuestion.getText().toString());

                Intent settingsActivity = new Intent(SettingsActivity.this, MainActivity.class);
                Intent gameSettingsActivity = new Intent(SettingsActivity.this, GameActivity.class);
                gameSettingsActivity.putExtra("Slider", SliderValue);
                gameSettingsActivity.putExtra("NbreQuestion", value);
                SettingsActivity.this.startActivity(gameSettingsActivity);

            }
        });
    }
}
