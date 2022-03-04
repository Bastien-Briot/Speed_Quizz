package com.briobas.speed_quizz.Models;

import android.database.Cursor;

public class Question {

    private String question;
    private int reponse;

    public Question() {};

    public Question(String question, int reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    // Curseur = tableau
    public Question(Cursor cursor){
        question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    public String getQuestion() { return question; }

    public int isReponse() {
        return reponse;
    }
}
