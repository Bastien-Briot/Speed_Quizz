package com.briobas.speed_quizz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME="SpeedQuiz.db";
    static int DB_VERSION=1;

    public SpeedQuizSQLiteOpenHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatatableQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY,question TEXT,reponse INTEGER);";
        db.execSQL(sqlCreateDatatableQuiz);
        db.execSQL("INSERT INTO quiz VALUES(1,\"Ricciardo a gagné un grand prix en 2021\",1)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"Le seuil de la douleur est de 120 db\",1)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"Le personnage principale de One Piece est Luffy\",1)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"Il n'y a pas d'oxygène dans l'espace\",1)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"Une série se compose de plusieurs épisodes\",1)");
        db.execSQL("INSERT INTO quiz VALUES(6,\"L'eau de mer est potable\",0)");
        db.execSQL("INSERT INTO quiz VALUES(7,\"Zoro ne sait pas nager\",0)");
        db.execSQL("INSERT INTO quiz VALUES(8,\"1+1 = 11\",0)");
        db.execSQL("INSERT INTO quiz VALUES(9,\"La terre est plate\",0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
