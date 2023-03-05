package com.example.Final_Project.Activities;

import android.annotation.SuppressLint;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.animation.AnimationUtils;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.Final_Project.Database.MyViewModel;
import com.example.Final_Project.Models.User;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.R;
import com.example.Final_Project.databinding.ActivityAccountBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Objects;

public class AccountActivity extends AppCompatActivity {
    ActivityAccountBinding binding;
    DatePickerDialog pickerDialog;
    String age;
    @SuppressLint( "SetTextI18n" )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Util.preferences = getSharedPreferences(Util.SP_NAME, MODE_PRIVATE);
        Util.editor = Util.preferences.edit();
        MyViewModel myViewModel = new ViewModelProvider(this).get( MyViewModel.class);
        binding.AAUserBD.setOnClickListener( view -> {
            Calendar now;
            if ( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N ) {
                now = Calendar.getInstance();
                Calendar finalNow = now;
                pickerDialog = DatePickerDialog.newInstance(
                        ( view1 , year , monthOfYear , dayOfMonth ) -> {
                            binding.AAUserBD.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            age = String.valueOf( finalNow.get(Calendar.YEAR) - year);
                        } ,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
            }

            pickerDialog.show(getSupportFragmentManager(),"Datepickerdialog");
        } );
        myViewModel.AllUser().observe( AccountActivity.this , users -> {
            if( users.get( 0 ).email != null ){
                binding.AAUserName.setText( users.get( 0 ).UserName );
                binding.AAEmail.setText( users.get( 0 ).email );
                binding.AAUserBD.setText( users.get( 0 ).birthday );
                String itemToSelect = users.get( 0 ).country;
                int index = 0;
                for (int i = 0; i < binding.AAUserCountry.getCount(); i++) {
                    if (binding.AAUserCountry.getItemAtPosition(i).equals(itemToSelect)) {
                        index = i;
                        break;
                    }
                }
                binding.AAUserCountry.setSelection(index);

                if (users.get( 0 ).Gender .equals( "Male" )){
                    binding.ProfileMale.setChecked( true );
                }else {
                    binding.ProfileFemale.setChecked( true );
                }
            }   } );


        binding.AABtnOK.setOnClickListener( view -> {
//            binding.AABtnOK.startAnimation(  AnimationUtils.loadAnimation( getBaseContext(), R.anim.button ));

            String UserName,Email,Country,Birthday,Gender;
            UserName = Objects.requireNonNull( binding.AAUserName.getText( ) ).toString().trim();
            Util.editor.putString( Util.User_Name,UserName );
            Util.editor.apply();
            Email = Objects.requireNonNull( binding.AAEmail.getText( ) ).toString();
            Birthday = Objects.requireNonNull( binding.AAUserBD.getText( ) ).toString();
            Country = binding.AAUserCountry.getSelectedItem().toString();

            if (binding.ProfileMale.isChecked()){
                Gender = "Male";
            }else{
                Gender = "Female";
            }
            String finalGender = Gender;
            myViewModel.AllUser().observe( AccountActivity.this, users ->
                    myViewModel.UpdateUser(new User(1,UserName,Email,Birthday, finalGender ,Country)) );
            finish();
        } );


    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Intent intent = new Intent(getBaseContext(), SoundService.class);
//        stopService(intent);
//    }

//    @Override
//    protected void onPause( ) {
//        super.onPause( );
//        Intent intent = new Intent(getBaseContext(), SoundService.class);
//        stopService(intent);    }

}