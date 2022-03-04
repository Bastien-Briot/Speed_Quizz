package com.briobas.speed_quizz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.briobas.speed_quizz.Models.Question;
import com.briobas.speed_quizz.Models.SpeedQuizSQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    private ArrayList<Question> questionList = new ArrayList<>();

    public QuestionManager(Context context) { questionList = initQuestionList(context); }

    public ArrayList<Question> getQuestionList() { return questionList; }

    public boolean isEmpty(ArrayList<Question> questionList) { return questionList.isEmpty(); }

    public Question randomQuestion(ArrayList<Question> questionList, int size) {
        Random rand = new Random();
        int i = rand.nextInt(questionList.size());
        Question question = questionList.get(i);

        questionList.remove(i);

        return question;
    }

    private void initQuestion(ArrayList<Question> questionList) {
        questionList.add(new Question("Jule César était le fiancé de Cléopâtre", 1));
        questionList.add(new Question("La terre est ronde",1));
        questionList.add(new Question("On ne peut pas flotté sur de l'eau salé", 0));
        questionList.add(new Question("On peut marcher sur l'eau", 0));
        questionList.add(new Question("Le seuil de la douleur audtitive est de 100db", 1));
        questionList.add(new Question("La chine à une population de plus d'un milliard d'habitant", 1));
        questionList.add(new Question("Nous sommes plus de 7 milliards sur terre", 1));
        questionList.add(new Question("Ricciardo a gagné un grand prix en 2021", 1));
    }

    /**
     * Charge une liste de question depuis la DB.
     * @param context Le contexte de l'application pour passer la query
     * @return Une arraylist charger de Question
     */
    private ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> listQuestion = new ArrayList<Question>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz","question","reponse"},null,null,null,null,"idquiz",null);
        while(cursor.moveToNext()){
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();
        return listQuestion;
    }
}
