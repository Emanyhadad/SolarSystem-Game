package com.example.Final_Project.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Final_Project.Adapter_Listener_Dialog.DialogFragment;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.databinding.FragmentQ2Binding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Q2Fragment extends Fragment {


    private String title;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String true_answer;
    private String hint;
    private static int duration;
    public static int point;
    public static boolean answerChecked = false;  // variable to store the result of the check
    public static boolean Q2answerChecked_never = false;  // variable to store the result of the check
    CountDownTimer timer;
    public static int pointQ2 ;


    public Q2Fragment() {
    }

    public static Q2Fragment newInstance( int id , String title , String answer1 , String answer2 , String answer3 , String answer4 , String true_answer , String hint
            , int duration , int point ) {
        Q2Fragment fragment = new Q2Fragment();
        Bundle args = new Bundle();
        args.putString( Util.ARG_TITLE , title);
        args.putString(Util.ARG_ANSWER1, answer1);
        args.putString(Util.ARG_ANSWER2, answer2);
        args.putString(Util.ARG_ANSWER3, answer3);
        args.putString(Util.ARG_ANSWER4, answer4);
        args.putString(Util.ARG_Answer, true_answer);
        args.putString(Util.ARG_HINT, hint);
        args.putInt(Util.ARG_DURATION, duration);
        args.putInt(Util.ARG_POINT, point);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString( Util.ARG_TITLE );
            answer1 = getArguments().getString(Util.ARG_ANSWER1);
            answer2 = getArguments().getString(Util.ARG_ANSWER2);
            answer3 = getArguments().getString(Util.ARG_ANSWER3);
            answer4 = getArguments().getString(Util.ARG_ANSWER4);
            true_answer = getArguments().getString(Util.ARG_Answer);
            hint = getArguments().getString(Util.ARG_HINT);
            duration = getArguments().getInt(Util.ARG_DURATION);
            point = getArguments().getInt(Util.ARG_POINT);
        }
    }
    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState ) {
        super.onActivityCreated( savedInstanceState );
        timer.start();
    }
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        FragmentQ2Binding binding = FragmentQ2Binding.inflate(getLayoutInflater());
        binding.ChooseTitle.setText( title );
        binding.ChooseAnswer1.setText(answer1);
        binding.ChooseAnswer2.setText(answer2);
        binding.ChooseAnswer3.setText(answer3);
        binding.ChooseAnswer4.setText(answer4);

        boolean answer1 = true_answer.equals(binding.ChooseAnswer1.getText().toString());
        boolean answer2 = true_answer.equals(binding.ChooseAnswer2.getText().toString());
        boolean answer3 = true_answer.equals(binding.ChooseAnswer3.getText().toString());
        boolean answer4 = true_answer.equals(binding.ChooseAnswer4.getText().toString());

         timer =new CountDownTimer(duration, 1000) {
            @SuppressLint( "SetTextI18n" )
            @Override
            public void onTick(long l) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (l / 3600000) % 24;
                long min = (l / 60000) % 60;
                long sec = (l / 1000) % 60;
                binding.timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
//                if( Q2answerChecked_never ) {
//                    binding.ChooseAnswer1.setEnabled(false);
//                    binding.ChooseAnswer2.setEnabled(false);
//                    binding.ChooseAnswer3.setEnabled(false);
//                    binding.ChooseAnswer4.setEnabled(false);
//
//                    binding.ChooseTitle.setText( title + "\n"+true_answer );
//                    Q2answerChecked_never = true;
//                }
//                else {
                binding.ChooseAnswer1.setOnClickListener( view -> {
                    if ( !answerChecked ) {
                        if ( answer1 ) {
                            Util.media_win.start( );
                            DialogFragment.newInstance(
                                    "The answer is correct!" ).show
                                    ( getChildFragmentManager( ) , "" );
                            pointQ2=point;
                            Util.ifTrue( point );
                            binding.timer.setText( "00:00:00" );

                        } else {
                            Util.ifFalse();
                            Util.media_win.start( );
                            DialogFragment.newInstance(
                                            "The answer is incorrect. \n" + "True Answer is: " + hint ).
                                    show( getChildFragmentManager( ) , "" );
                        }

                    answerChecked = true;  // mark the answer as checked
                    binding.ChooseAnswer1.setEnabled( false );  // disable the button
                    binding.ChooseAnswer2.setEnabled( false );  // disable the button
                    binding.ChooseAnswer3.setEnabled( false );  // disable the button
                    binding.ChooseAnswer4.setEnabled( false );  // disable the button


                }else {
                        Toast.makeText( getActivity() , " You can Answer just one time, click NEXT to go next Question ! " , Toast.LENGTH_SHORT ).show( );
                }} );
                binding.ChooseAnswer2.setOnClickListener( view -> {
                    if ( !answerChecked ) {
                        if (answer2) {
                        Util.media_win.start();
                        DialogFragment.newInstance(
                                "The answer is correct!" ).show
                                ( getChildFragmentManager( ) , "" );

                            pointQ2=point;
                        Util.ifTrue( point );
                            binding.timer.setText( "00:00:00" );

                        } else {
                            Util.ifFalse();
                        Util.media_fail.start();
                        DialogFragment.newInstance(
                                        "The answer is incorrect. \n" + "True Answer is: " + hint ).
                                show( getChildFragmentManager( ) , "" );
                    }
                    answerChecked = true;  // mark the answer as checked
                    binding.ChooseAnswer1.setEnabled( false );  // disable the button
                    binding.ChooseAnswer2.setEnabled( false );  // disable the button
                    binding.ChooseAnswer3.setEnabled( false );  // disable the button
                    binding.ChooseAnswer4.setEnabled( false );  // disable the button

                }else {
                        Toast.makeText( getActivity() , " You can Answer just one time, click NEXT to go next Question ! " , Toast.LENGTH_SHORT ).show( );

                    }} );
                binding.ChooseAnswer3.setOnClickListener( view -> {
                    if ( !answerChecked ) {

                        if (answer3) {
                        Util.media_win.start();
                        Util.media_win.start();
                        DialogFragment.newInstance(
                                "The answer is correct!" ).show
                                ( getChildFragmentManager( ) , "" );

                            pointQ2=point;
                        Util.ifTrue( point );
                            binding.timer.setText( "00:00:00" );


                        } else {
                            Util.ifFalse();
                        Util.media_fail.start();
                        DialogFragment.newInstance(
                                        "The answer is incorrect. \n" + "True Answer is: " + hint ).
                                show( getChildFragmentManager( ) , "" );
                    }
                        answerChecked = true;  // mark the answer as checked
                        binding.ChooseAnswer1.setEnabled( false );  // disable the button
                        binding.ChooseAnswer2.setEnabled( false );  // disable the button
                        binding.ChooseAnswer3.setEnabled( false );  // disable the button
                        binding.ChooseAnswer4.setEnabled( false );  // disable the button

                    }else {
                        Toast.makeText( getActivity() , " You can Answer just one time, click NEXT to go next Question ! " , Toast.LENGTH_SHORT ).show( );
                    }} );
                binding.ChooseAnswer4.setOnClickListener( view -> {
                    if ( !answerChecked ) {
                        if (answer4) {
                        Util.media_win.start();
                        DialogFragment.newInstance(
                                "The answer is correct!" ).show
                                ( getChildFragmentManager( ) , "" );

                            pointQ2=point;
                        Util.ifTrue( point );
                            binding.timer.setText( "00:00:00" );

                        } else {
                            Util.ifFalse();
                        Util.media_fail.start();
                        DialogFragment.newInstance(
                                        "The answer is incorrect. \n" + "True Answer is: " + hint ).
                                show( getChildFragmentManager( ) , "" );
                    }
                    answerChecked = true;  // mark the answer as checked
                    binding.ChooseAnswer1.setEnabled( false );  // disable the button
                    binding.ChooseAnswer2.setEnabled( false );  // disable the button
                    binding.ChooseAnswer3.setEnabled( false );  // disable the button
                    binding.ChooseAnswer4.setEnabled( false );  // disable the button

                }else {
                        Toast.makeText( getActivity() , " You can Answer just one time, click NEXT to go next Question ! " , Toast.LENGTH_SHORT ).show( );

                    }} );
                binding.qTvScour.setText( "Scour : "+ (Util.preferences.getInt( Util.ScourLevel,0 ) +point) );
                Util.editor.putInt( Util.ScourLevel,Util.preferences.getInt( Util.ScourLevel,0 )+point );
            }
        //}

            @Override
            public void onFinish() {
                binding.timer.setText("00:00:00");
//                Toast.makeText( getActivity() , " Time Out ! " , Toast.LENGTH_SHORT ).show( );

            }
        };
        return binding.getRoot();
    }


    @Override
    public void onPause( ) {
        duration = 0;

        super.onPause( );
    }
}