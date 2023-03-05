package com.example.Final_Project.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.Final_Project.Adapter_Listener_Dialog.FragmentAdapter;
import com.example.Final_Project.Database.MyViewModel;
import com.example.Final_Project.Fragments.Q1Fragment;
import com.example.Final_Project.Fragments.Q2Fragment;
import com.example.Final_Project.Fragments.Q3Fragment;
import com.example.Final_Project.Json.Read_Pars_Insert;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.R;
import com.example.Final_Project.databinding.ActivityQuestionsBinding;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    public ActivityQuestionsBinding binding;
    public static ArrayList<Fragment> fragments;
    FragmentAdapter fragmentAdapter;
    int pos,Q1,Level1;

//    @Override
//    protected void onStart( ) {
//        super.onStart( );
//        int Q1 = Util.preferences.getInt( Util.FragmentQ,-1 );
//        int Level1 = Util.preferences.getInt( "FragmentL", -1 );
//        int Q= binding.LevelPager.getCurrentItem();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        pos = intent.getIntExtra(Util.LevelNo, 0);
        binding.LevelTV.setText( "Level : "+pos );

        Util.media_fail = MediaPlayer.create(this, R.raw.fail_sound);
        Util.media_win = MediaPlayer.create(this, R.raw.win_sound);


        fragments = new ArrayList<>();
        String language = getApplicationContext().getResources().getConfiguration().locale.getLanguage();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && language.equals("en")) {
            // Language is English
            String assets = Read_Pars_Insert.readFromAssets(getApplicationContext(), "json/jsonEnglish.json" );
            Read_Pars_Insert p = new Read_Pars_Insert(this);
            p.readJson(assets);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && language.equals("ar")) {
            // Language is Arabic
            String assets = Read_Pars_Insert.readFromAssets(getApplicationContext(), "json/jsonArabic.json" );
            Read_Pars_Insert p = new Read_Pars_Insert(this);
            p.readJson(assets);
        }

//        if(!(Util.preferences.getInt( Util.FragmentQ,-1 )<0)){
//            int Q = Util.preferences.getInt( Util.FragmentQ,0 );
//            binding.LevelPager.setCurrentItem( Q, true) ;
//            pos = Util.preferences.getInt( "FragmentL" ,0);
//        }

        MyViewModel myViewModel = new ViewModelProvider(this).get( MyViewModel.class);

        myViewModel.getQuestion( pos ).observe( this , questions -> {
            for (int i = 0; i < questions.size(); i++) {
                int level = questions.get(i).getLevel_no();

                if (level == pos) {
                    String title = questions.get( i ).getId()+"- "+questions.get(i).getTitle()  ;
                    String true_answer = questions.get(i).getTrue_answer();
                    String answer1 = questions.get(i).getAnswer_1();
                    String answer2 = questions.get(i).getAnswer_2();
                    String answer3 = questions.get(i).getAnswer_3();
                    String answer4 = questions.get(i).getAnswer_4();
                    String hint = questions.get(i).getHint();
                    int patternId = questions.get(i).getPatternId();
                    int duration = questions.get(i).getDuration();
                    int point = questions.get(i).getPoints();

                    switch (patternId) {
                        case 1:
                            Q1Fragment.answerChecked = false;
                            fragments.add( Q1Fragment.newInstance(questions.get( i ).getId(),title, true_answer, hint, duration, point));
                            break;

                        case 2:
                            Q2Fragment.answerChecked = false;
                            fragments.add( Q2Fragment.newInstance(questions.get( i ).getId(),title, answer1, answer2, answer3, answer4, true_answer, hint, duration, point));
                            break;

                        case 3:
                            Q3Fragment.answerChecked = false;
                            fragments.add( Q3Fragment.newInstance(questions.get( i ).getId(),title, true_answer, hint, duration, point));

                            break;
                    }

                    fragmentAdapter = new FragmentAdapter( QuestionsActivity.this, fragments);
                    binding.LevelPager.setAdapter( fragmentAdapter );
                    binding.LevelPager.setUserInputEnabled(false);

                }
        }} );


        binding.LevelNext.setOnClickListener( view -> {
           int Log1= binding.LevelPager.getCurrentItem();
               switch ( Log1 ){
                   case 0:
//                       if (Q1Fragment.answerChecked || Q1Fragment.Q1answerChecked_never ){
                       if (Q1Fragment.answerChecked ){

                           binding.LevelPager.setCurrentItem(Log1+1, true);
//                           finish();
                       }else {
                           Toast.makeText( this , "Must Answer!" , Toast.LENGTH_SHORT ).show( );
                       }
                       //fragmentAdapter.notifyItemChanged(1);
                       break;
                   case 1:
//                       if (Q2Fragment.answerChecked||Q2Fragment.Q2answerChecked_never){
                       if (Q2Fragment.answerChecked){

                           binding.LevelPager.setCurrentItem(Log1+1, true);
//                           finish();
                       }else {
                           Toast.makeText( this , "Must Answer!" , Toast.LENGTH_SHORT ).show( );
                       }
//                       fragmentAdapter.notifyItemChanged(1);
                       break;
                   case 2:
//               if(Q3Fragment.answerChecked||Q3Fragment.Q3answerChecked_never){
                       if(Q3Fragment.answerChecked){

                           startActivity(new Intent( QuestionsActivity.this, LevelsActivity.class));
               Util.editor.putInt( Util.CountLevel,Util.preferences.getInt( Util.CountLevel,0 )+1 ).apply();
               Util.editor.putString( Util.NumLevelFin,Util.preferences.getString( Util.NumLevelFin,"" ) +" - " + pos ).apply();
               finish();
               }else {
                   Toast.makeText( this , "Must Answer!" , Toast.LENGTH_SHORT ).show( );
               }
               break;
               }
        } );
        binding.LevelSkip.setOnClickListener( v -> {
            Toast.makeText( this , "\"Unfortunately, Score will mince 3 point" , Toast.LENGTH_SHORT ).show( );
//                            DialogFragment.newInstance(
//                                "Unfortunately, Score will mince 3 point ").
//                        show( getSupportFragmentManager() , "" );
            int Log1= binding.LevelPager.getCurrentItem();
            if (Log1 != 2){
                binding.LevelPager.setCurrentItem(Log1+1, true);
                fragmentAdapter.notifyItemChanged(1);
//                finish();
                }else {
                startActivity(new Intent( QuestionsActivity.this, LevelsActivity.class));
                finish();
            }
            Util.editor.putInt( Util.Score,Util.preferences.getInt( Util.Score,0 )-3 );
            Util.editor.apply();
        } );

    //TODO: How we can refresh shared.

    }

    @Override
    protected void onDestroy( ) {
        super.onDestroy( );
       int Q= binding.LevelPager.getCurrentItem();
       Util.editor.putInt( Util.FragmentQ,Q );
        int level = pos;
        Util.editor.putInt( "FragmentL",level );
        Util.editor.apply();
    }


//    @Override
//    protected void onPause( ) {
//        super.onPause( );
//        Intent intent = new Intent(getBaseContext(), SoundService.class);
//        stopService(intent);    }
}