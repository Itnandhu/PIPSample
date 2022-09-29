package com.example.pictureinpicture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ToggleActivity extends AppCompatActivity {

    private RadioGroup myRadioGroup;
    private RadioButton btnStarDoctor;
    private RadioButton btnDoctorPartner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle);
        myRadioGroup=findViewById(R.id.myRadioGroup);
        btnDoctorPartner=findViewById(R.id.btnDoctorPartner);
        btnStarDoctor=findViewById(R.id.btnStarDoctor);

        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

              /*  if(i == R.id.btnStarDoctor){
                    btnDoctorPartner.setChecked(false);
                }else {
                    btnStarDoctor.setChecked(false);
                }*/

            }
        });


        }

    }

