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
import androidx.lifecycle.ViewModelProvider;

import com.example.Final_Project.Adapter_Listener_Dialog.DialogFragment;
import com.example.Final_Project.Database.MyViewModel;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.databinding.FragmentQ3Binding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Q3Fragment extends Fragment {

    private String title;
    private String answerFill;
    String hint;
    private static int duration;
    public static int point,id;
    public static boolean answerChecked = false;  // variable to store the result of the check
    public static boolean Q3answerChecked_never = false;  // variable to store the result of the check
    CountDownTimer timer;
    public static int pointQ3 ;
//    MyViewModel myViewModel = new ViewModelProvider(this).get( MyViewModel.class);


    public Q3Fragment() {
    }

    public static Q3Fragment newInstance( int id , String title , String answerFill , String hint , int duration , int point ) {
        Q3Fragment fragment = new Q3Fragment();
        Bundle args = new Bundle();
        args.putInt( Util.ARG_ID,id );
        args.putString( Util.ARG_TITLE, title);
        args.putString(Util.ARG_Answer, answerFill);
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
            answerFill = getArguments().getString(Util.ARG_Answer);
            hint = getArguments().getString(Util.ARG_HINT);
            duration = getArguments().getInt(Util.ARG_DURATION);
            point = getArguments().getInt(Util.ARG_POINT);
        }
    }
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        FragmentQ3Binding binding = FragmentQ3Binding.inflate(inflater, container, false);
        binding.FillTitle.setText(title);

        int TueFalsePoint= Util.preferences.getInt("TueFalsePoint", Q1Fragment.point);
        int ChoosePoint= Util.preferences.getInt("ChoosePoint", Q2Fragment.point);
//        binding.score.setText(String.valueOf(TueFalsePoint+ChoosePoint));

        timer =new CountDownTimer(duration, 1000) {
            @SuppressLint( "SetTextI18n" )
            @Override
            public void onTick(long l) {

                    NumberFormat f = new DecimalFormat("00");
                long hour = (l / 3600000) % 24;
                long min = (l / 60000) % 60;
                long sec = (l / 1000) % 60;
                binding.timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
//                if( Q3answerChecked_never ) {
//                    binding.CheckBtn.setEnabled(false);
//
//                    binding.FillTitle.setText( title + "\n"+answerFill );
//                    Q3answerChecked_never = true;
//                }else {
                binding.CheckBtn.setOnClickListener( view -> {
                    if ( !answerChecked ) {
                        if ( binding.FillAnswer.getText( ).toString( ).equals( answerFill ) ) {
                            Util.media_win.start( );
                            DialogFragment.newInstance(
                                    "The answer is correct!" ).show
                                    ( getChildFragmentManager( ) , "" );

                            pointQ3=point;
                            Util.ifTrue( point );
                            binding.timer.setText( "00:00:00" );

                        } else {
                            Util.ifFalse();
                            Util.media_fail.start( );
                            DialogFragment.newInstance(
                                            "The answer is incorrect. \n" + "True Answer is: " + hint ).
                                    show( getParentFragmentManager( ) , "" );
                        }
                    answerChecked = true;  // mark the answer as checked
                    binding.CheckBtn.setEnabled( false );  // disable the button

                }else {
                        Toast.makeText( getContext(), " You can Answer just one time, click NEXT to go next Question! " , Toast.LENGTH_SHORT ).show( );

                    } });
                binding.qTvScour2.setText( "Scour : "+(Util.preferences.getInt( Util.ScourLevel,0 )+point) );
            }
        //}


            @Override
            public void onFinish() {
                binding.timer.setText("00:00:00");
//                Toast.makeText( getContext(), " Time Out !  " , Toast.LENGTH_SHORT ).show( );

            }
        };
        return binding.getRoot();
    }


    @Override
    public void onStop( ) {
        super.onStop( );

        Util.editor.putInt( Util.ScourLevel
                ,Q1Fragment.pointQ+Q2Fragment.pointQ2+Q3Fragment.pointQ3 ).apply();
    }

    @Override
    public void onDestroy( ) {
        super.onDestroy( );
            }
}