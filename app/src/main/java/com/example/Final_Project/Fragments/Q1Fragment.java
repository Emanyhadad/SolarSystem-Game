package com.example.Final_Project.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Final_Project.Adapter_Listener_Dialog.DialogFragment;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.databinding.FragmentQ1Binding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Q1Fragment extends Fragment {
    public  static FragmentQ1Binding binding;

    private String title,answer,hint;
    private static int  duration;
    public static int point,id;
    public static boolean answerChecked = false;  // variable to store the result of the check
    public static boolean Q1answerChecked_never = false;  // variable to store the result of the check
    CountDownTimer timer;
    public static int pointQ ;

    public Q1Fragment() {
    }

//    void ifTrue(int point){
//        int sore = Util.preferences.getInt( Util.Score, 0);
//        Util.editor.putInt( Util.Score, sore+point);
//        int TrueAnswer =Util.preferences.getInt( Util.CountTQus, 0);
//        Util.editor.putInt( Util.CountTQus, TrueAnswer+1);
//        int CountQ =Util.preferences.getInt( Util.CountQus, 0);
//        Util.editor.putInt( Util.CountQus, CountQ+1);
//    }

    public static Q1Fragment newInstance( int id , String title , String answer , String hint , int duration , int point ) {
        Q1Fragment fragment = new Q1Fragment();
        Bundle args = new Bundle();
        args.putInt( Util.ARG_ID,id );
        args.putString( Util.ARG_TITLE, title);
        args.putString(Util.ARG_Answer, answer);
        args.putString(Util.ARG_HINT, hint);
        args.putInt(Util.ARG_DURATION, duration);
        args.putInt(Util.ARG_POINT, point);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState ) {
        super.onActivityCreated( savedInstanceState );
        timer.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt( Util.ARG_ID );
            title = getArguments().getString(Util.ARG_TITLE);
            answer = getArguments().getString(Util.ARG_Answer);
            hint = getArguments().getString(Util.ARG_HINT);
            duration = getArguments().getInt(Util.ARG_DURATION);
            point = getArguments().getInt(Util.ARG_POINT);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding = FragmentQ1Binding.inflate(inflater, container, false);
        binding.FragTitle.setText(title);

     //   if( Util.preferences.getBoolean( Util.AnswerQ1 , false ) ) {
          timer=  new CountDownTimer( duration , 1000 ) {
                @SuppressLint( "SetTextI18n" )
                @Override
                public void onTick( long l ) {
                    NumberFormat f = new DecimalFormat( "00" );
                    long hour = ( l / 3600000 ) % 24;
                    long min = ( l / 60000 ) % 60;
                    long sec = ( l / 1000 ) % 60;
                    binding.timer.setText( f.format( hour ) + ":" + f.format( min ) + ":" + f.format( sec ) );
//                    if( Q1answerChecked_never ){
//                        binding.RadioTrue.setEnabled(false);
//                        binding.RadioFalse.setEnabled(false);
//                        binding.FragTitle.setText( title + "\n"+answer );
//                        Q1answerChecked_never = true;
//                    }
//                    else {
                    binding.RadioFalse.setOnClickListener( view -> {
                        if ( !answerChecked ) {
                            if ( "false".equals( answer ) ) {
                                Q1answerChecked_never = true;
                                Util.media_win.start( );
                                DialogFragment.newInstance(
                                        "The answer is correct!" ).show
                                        ( getParentFragmentManager( ) , "" );
                                Util.editor.putInt( Util.ScourLevel,0 ).apply();
                                pointQ = point;
                                binding.QTvScour.setText("Scour :"+ point );
                                Util.ifTrue( point );

//                                binding.timer.setText( "00:00:00" );

                            } else {
                                Q1answerChecked_never = false;
                                Util.ifFalse( );
                                Util.media_fail.start( );
                                DialogFragment.newInstance(
                                                "The answer is incorrect. \n" + "True Answer is: " + hint ).
                                        show( getChildFragmentManager( ) , "" );
                            }

                            // check if the answer has not been checked yet
                            // Perform the check here and store the result in the answerChecked variable
                            answerChecked = true;  // mark the answer as checked
                            binding.RadioTrue.setEnabled( false );  // disable the button
                            binding.RadioFalse.setEnabled( false );  // disable the button

                        } else {
                            Toast.makeText( getActivity() , " You can Answer just one time, click NEXT to go next Question! " , Toast.LENGTH_SHORT ).show( );

                        }


                    } );
                    binding.RadioTrue.setOnClickListener( view -> {
                        if ( !answerChecked ) {
                            if ( "true".equals( answer ) ) {
                                Q1answerChecked_never = false;
                                Util.media_win.start( );
                                DialogFragment.newInstance(
                                        "The answer is correct!" ).show
                                        ( getParentFragmentManager( ) , "" );
                                pointQ = point;
                                Util.ifTrue( point );
                                binding.timer.setText( "00:00:00" );


                            } else {

                                Util.ifFalse( );

                                Util.media_fail.start( );
                                DialogFragment.newInstance(
                                                "The answer is incorrect. \n" + "True Answer is: " + hint ).
                                        show( getParentFragmentManager( ) , "" );
                            }
                            // check if the answer has not been checked yet
                            // Perform the check here and store the result in the answerChecked variable
                            answerChecked = true;  // mark the answer as checked
                            binding.RadioTrue.setEnabled( false );  // disable the button
                            binding.RadioFalse.setEnabled( false );  // disable the button

                        } else {

                            Toast.makeText( getActivity() , " You can Answer just one time, click NEXT to go next Question! " , Toast.LENGTH_SHORT ).show( );

                        }
                    } );
            //    }

                }


                //TODO : How can click for one tim}

                @Override
                public void onFinish( ) {
                    binding.timer.setText( "00:00:00" );
//                    Toast.makeText( getActivity() , " Time Out ! " , Toast.LENGTH_SHORT ).show( );


                    //Todo: show dialog

                }
            }.start() ;

        Util.editor.putBoolean( Util.AnswerQ1,answerChecked ).apply();
        return binding.getRoot();
    }




    @Override
    public void onPause( ) {
        duration = 0;

        super.onPause( );
    }
}
