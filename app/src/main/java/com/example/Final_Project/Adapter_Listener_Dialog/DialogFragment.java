package com.example.Final_Project.Adapter_Listener_Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.Final_Project.databinding.FragmentDialogBinding;

import java.util.Objects;


public class DialogFragment extends androidx.fragment.app.DialogFragment {
    private static final String MASSAGE = "MASSAGE";

    private String MASSAGE1;

    public DialogFragment( ) {
    }

    public static DialogFragment newInstance( String massage ) {
        DialogFragment fragment = new DialogFragment( );
        Bundle args = new Bundle( );
        args.putString( MASSAGE , massage );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if ( getArguments( ) != null ) {
            MASSAGE1 = getArguments( ).getString( MASSAGE );
        }
    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater , ViewGroup container ,
                              Bundle savedInstanceState ) {
        FragmentDialogBinding binding =
                FragmentDialogBinding.inflate
                        ( inflater,container,false);

        binding.exitTv.setText( MASSAGE1 );




        return binding.getRoot();

    }

    @Override
    public void onResume( ) {
        super.onResume( );
        WindowManager.LayoutParams params = Objects.requireNonNull( getDialog( ) ).getWindow( ).getAttributes( );
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes( params );
    }
}