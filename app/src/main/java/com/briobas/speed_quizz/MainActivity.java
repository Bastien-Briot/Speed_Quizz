package com.briobas.speed_quizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button BTN_Ajouter, BTN_Commencer;
    EditText Player1, Player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BTN_Ajouter = findViewById(R.id.BTN_Add);
        BTN_Commencer = findViewById(R.id.BTN_Commencer);
        Player1 = findViewById(R.id.main_text_player1);
        Player2 = findViewById(R.id.main_text_player2);
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        HideButton();

        BTN_Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player1.setVisibility(View.VISIBLE);
            }
        });
        Player1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Player2.setVisibility(View.VISIBLE);
            }
        });
        Player2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                BTN_Commencer.setVisibility(View.VISIBLE);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_favorite:
                break;
            case R.id.action_settings:
                break;
            case R.id.action_question:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    public void HideButton() {
        BTN_Commencer.setVisibility(View.GONE);
        Player1.setVisibility(View.GONE);
        Player2.setVisibility(View.GONE);
    }
}
