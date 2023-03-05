package com.example.Final_Project.Models;

import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class Util {
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    public static final String SP_NAME="preferences";

    public static final String SP_SOUND="SOUND";
    public static final String SP_Notification="Notification";

    public static final String CountQus = "CountQ";
    public static final String CountLevel = "CountLevel";
    public static final String CountTQus = "CountTQ";
    public static final String CountFQus = "CountFQ";
    public static final String Score = "Score";
    public static final String NumLevelFin = "NumLevelFin";
    public static final String LevelScore = "LevelScore";

    public static MediaPlayer media_fail;
    public static MediaPlayer media_win;
    public static String DEFAULT_PLAYER = "PLAYER";
    public static String User_Name = "PLAYER";
    public static final String LevelNo = "LevelNo" ;

    public static final String ARG_TITLE = "title";
    public static final String ARG_Answer = "answer";
    public static final String ARG_ANSWER1 = "answer1";
    public static final String ARG_ANSWER2 = "answer2";
    public static final String ARG_ANSWER3 = "answer3";
    public static final String ARG_ANSWER4 = "answer4";
    public static final String ARG_HINT = "hint";
    public static final String ARG_DURATION = "duration";
    public static final String ARG_POINT = "point";
    public static final String ARG_ID = "QID";

    public static final String FragmentQ = "Q";

    public static void ifTrue( int point ){
        int sore = Util.preferences.getInt( Util.Score, 0);
        Util.editor.putInt( Util.Score, sore+point);
        int TrueAnswer =Util.preferences.getInt( Util.CountTQus, 0);
        Util.editor.putInt( Util.CountTQus, TrueAnswer+1);
        int CountQ =Util.preferences.getInt( Util.CountQus, 0);
        Util.editor.putInt( Util.CountQus, CountQ+1);
        Util.editor.apply();
    }
    public static void ifFalse( ){
        int falseAnswer =Util.preferences.getInt( Util.CountFQus, 0);
        Util.editor.putInt( Util.CountFQus, falseAnswer+1);
        int CountQ =Util.preferences.getInt( Util.CountQus, 0);
        Util.editor.putInt( Util.CountQus, CountQ+1);
        Util.editor.apply();
    }

    public static final String AnswerQ1 = "AnswerQ1";
    public static final String AnswerQ2 = "AnswerQ2";
    public static final String AnswerQ3 = "AnswerQ3";
    public static final String ScourLevel = "ScourLevel";
    public static final String Q1 = "Q1";
    public static final String Q2 = "Q2";
    public static final String Q3 = "Q3";
    public static final String Q4 = "Q4";
    public static final String Q5 = "Q5";
    public static final String Q6 = "Q6";
    public static final String Q7 = "Q7";
    public static final String Q8 = "Q8";
    public static final String Q9 = "Q9";
    public static final String Q10 = "Q10";
    public static final String Q11 = "Q11";
    public static final String Q12 = "Q12";
    public static final String Q13 = "Q13";
    public static final String Q14 = "Q14";
    public static final String Q15 = "Q15";
    public static final String Q16 = "Q16";
    public static final String Q17 = "Q17";
    public static final String Q18 = "Q18";
    public static final String Q19 = "Q19";
    public static final String Q20 = "Q20";
    public static final String Q21 = "Q21";
    public static final String Q22 = "Q22";
    public static final String Q23 = "Q23";
    public static final String Q24 = "Q24";
    public static final String Q25 = "Q25";
    public static final String Q26 = "Q26";
    public static final String Q27 = "Q27";
    public static final String Q28 = "Q28";
    public static final String Q29 = "Q29";


}
