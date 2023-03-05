package com.example.Final_Project.Json;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.example.Final_Project.Models.Level;
import com.example.Final_Project.Models.Question;
import com.example.Final_Project.Database.MyViewModel;

public class Read_Pars_Insert {
    Context context;

    public Read_Pars_Insert( Context context) {
        this.context = context;
    }

    public void readJson(String assets) {
        MyViewModel myViewModel = new ViewModelProvider((ViewModelStoreOwner)context).get( MyViewModel.class);

        Question question;
        Level level;
        try {
            JSONArray jsonArray = new JSONArray(assets);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject levelJsonObject = jsonArray.getJSONObject(i);
                //For Level
                int level_no = levelJsonObject.getInt("level_no");
                int unlock_points = levelJsonObject.getInt("unlock_points");

                level = new Level(level_no, unlock_points);
                myViewModel.InsertLevel(level);

                //For Questions
                JSONArray mysteryJsonArray = levelJsonObject.getJSONArray("questions");
                for (int j = 0; j < mysteryJsonArray.length(); j++) {
                    JSONObject mysteryJsonObject = mysteryJsonArray.getJSONObject(j);
                    int id = mysteryJsonObject.getInt("id");
                    String title = mysteryJsonObject.getString("title");
                    String answer_1 = mysteryJsonObject.getString("answer_1");
                    String answer_2 = mysteryJsonObject.getString("answer_2");
                    String answer_3 = mysteryJsonObject.getString("answer_3");
                    String answer_4 = mysteryJsonObject.getString("answer_4");
                    String true_answer = mysteryJsonObject.getString("true_answer");
                    int points = mysteryJsonObject.getInt("points");
                    int duration = mysteryJsonObject.getInt("duration");
                    String hint = mysteryJsonObject.getString("hint");
                    JSONObject patternJsonObject = mysteryJsonObject.getJSONObject("pattern");
                    int pattern_id = patternJsonObject.getInt("pattern_id");
                    String pattern_name = patternJsonObject.getString("pattern_name");

                    question = new Question(id,title, answer_1, answer_2, answer_3, answer_4, true_answer
                            , points, level_no, duration,pattern_id,pattern_name, hint);
                    myViewModel.InsertQuestion(question);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static String readFromAssets(Context context, String fileName) {
        String string = "";
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] byteObject = new byte[size];
            inputStream.read(byteObject);
            inputStream.close();
            string = new String(byteObject, StandardCharsets.UTF_8 );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }
}








