package com.briobas.speed_quizz.Controllers;

import com.briobas.speed_quizz.Models.Question;
import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    private ArrayList<String> questionList;

    public QuestionManager() {}

    public QuestionManager(String question) {
        initQuestion(questionList);
    }

    public ArrayList<String> getQuestionList() { return questionList; }

    public boolean isEmpty(ArrayList<String> questionList) { return questionList.isEmpty(); }

    public String randomQuestion(ArrayList<String> questionList) {
        Random random = new Random();
        int randomIndex = random.nextInt(questionList.size());

        questionList.remove(randomIndex);
        return questionList.get(randomIndex);
    }

    private void initQuestion(ArrayList<String> questionList) {
//        questionList.add(new Question("Jule César était le fiancé de Cléopâtre", 1));
//        questionList.add(new Question("La terre est ronde",1));
//        questionList.add(new Question("On ne peut pas flotté sur de l'eau salé", 0));
//        questionList.add(new Question("On peut marcher sur l'eau", 0));
//        questionList.add(new Question("Le seuil de la douleur audtitive est de 100db", 1));
//        questionList.add(new Question("La chine à une population de plus d'un milliard d'habitant", 1));
//        questionList.add(new Question("Nous sommes plus de 7 milliards sur terre", 1));
//        questionList.add(new Question("Ricciardo a gagné un grand prix en 2021", 1));
        questionList.add("Jule César était le fiancé de Cléopâtre");
        questionList.add("La terre est ronde");
        questionList.add("On ne peut pas flotté sur de l'eau salé");
        questionList.add("On peut marcher sur l'eau");
        questionList.add("Le seuil de la douleur audtitive est de 100db");
        questionList.add("La chine à une population de plus d'un milliard d'habitant");
        questionList.add("Nous sommes plus de 7 milliards sur terre");
        questionList.add("Ricciardo a gagné un grand prix en 2021");
    }
}
