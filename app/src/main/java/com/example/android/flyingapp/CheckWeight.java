package com.example.android.flyingapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CheckWeight extends AppCompatActivity {

    final int FUEL_WEIGHT = 6;
    final int PILOT_ARM = 37;
    final int PASSENGER_ARM = 73;
    final int FUEL_ARM = 48;
    final int BAGGAGE_ARM = 95;
    final int LOWER_CG_LIMIT = 35;
    final double UPPER_CG_LIMIT = 47.4;
    final int LOWER_WEIGHT_LIMIT = 1560;
    final int UPPER_WEIGHT_LIMIT = 2450;
    final int CG_INTERSECTION = 40;
    final int WEIGHT_INTERSECTION = 1950;
    final double UTILITY_CG_LIMIT = 40.5;
    final int UTILITY_WEIGHT_LIMIT = 2100;

    EditText emptyWeight;
    EditText emptyArm;
    EditText emptyMoment;
    EditText pilotWeight;
    EditText frontPassengerWeight;
    EditText rearPassengerOneWeight;
    EditText rearPassengerTwoWeight;
    EditText baggageWeight;
    EditText gasVolume;
    TextView emptyPlaneWeight;
    TextView emptyPlaneArm;
    TextView emptyPlaneMoment;
    TextView emptyPlaneRegion;
    TextView takeoffWeight;
    TextView takeoffArm;
    TextView takeoffMoment;
    TextView takeoffRegion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_weight);

        emptyWeight=findViewById(R.id.empty_weight_input);
        emptyArm=findViewById(R.id.empty_arm_input);
        emptyMoment=findViewById(R.id.empty_moment_input);
        pilotWeight=findViewById(R.id.pilot_weight_input);
        frontPassengerWeight=findViewById(R.id.front_passenger_weight_input);
        rearPassengerOneWeight=findViewById(R.id.rear_passenger_one_weight_input);
        rearPassengerTwoWeight=findViewById(R.id.rear_passenger_two_weight_input);
        baggageWeight=findViewById(R.id.baggage_weight_input);
        gasVolume=findViewById(R.id.gas_volume_input);
        emptyPlaneWeight=findViewById(R.id.empty_plane_weight);
        emptyPlaneArm=findViewById(R.id.empty_plane_arm);
        emptyPlaneMoment=findViewById(R.id.empty_plane_moment);
        emptyPlaneRegion=findViewById(R.id.empty_plane_region);
        takeoffWeight=findViewById(R.id.takeoff_weight);
        takeoffArm=findViewById(R.id.takeoff_arm);
        takeoffMoment=findViewById(R.id.takeoff_moment);
        takeoffRegion=findViewById(R.id.takeoff_region);
    }

    public void calculate(View view){
        double gasWeight = Double.parseDouble(gasVolume.getText().toString()) * FUEL_WEIGHT;
        double weightEmptyPlane = Double.parseDouble(emptyWeight.getText().toString())+
                Double.parseDouble(pilotWeight.getText().toString())+
                Double.parseDouble(frontPassengerWeight.getText().toString())+
                Double.parseDouble(rearPassengerOneWeight.getText().toString())+
                Double.parseDouble(rearPassengerTwoWeight.getText().toString())+
                Double.parseDouble(baggageWeight.getText().toString());
        double momentEmptyPlane = Double.parseDouble(emptyMoment.getText().toString())+
                ((Double.parseDouble(pilotWeight.getText().toString()) + Double.parseDouble(frontPassengerWeight.getText().toString())) * PILOT_ARM) +
                ((Double.parseDouble(rearPassengerOneWeight.getText().toString()) + Double.parseDouble(rearPassengerTwoWeight.getText().toString())) * PASSENGER_ARM) +
                (Double.parseDouble(baggageWeight.getText().toString()) * BAGGAGE_ARM);
        double armEmptyPlane = momentEmptyPlane/weightEmptyPlane;

        double weightTakeoff = gasWeight + weightEmptyPlane;
        double momentTakeoff = momentEmptyPlane + (gasWeight * FUEL_ARM);
        double armTakeoff = momentTakeoff/weightTakeoff;

        emptyPlaneWeight.setText(String.format("%.2f",weightEmptyPlane));
        emptyPlaneArm.setText(String.format("%.2f",armEmptyPlane));
        emptyPlaneMoment.setText(String.format("%.2f",momentEmptyPlane));

        takeoffWeight.setText(String.format("%.2f",weightTakeoff));
        takeoffArm.setText(String.format("%.2f",armTakeoff));
        takeoffMoment.setText(String.format("%.2f",momentTakeoff));

        if(armEmptyPlane<=LOWER_CG_LIMIT||armEmptyPlane>=UPPER_CG_LIMIT||
                weightEmptyPlane<=LOWER_WEIGHT_LIMIT||weightEmptyPlane>=UPPER_WEIGHT_LIMIT||
                (weightEmptyPlane-WEIGHT_INTERSECTION>=
                        ((armEmptyPlane-LOWER_CG_LIMIT)*((UPPER_WEIGHT_LIMIT-WEIGHT_INTERSECTION)/(CG_INTERSECTION-LOWER_CG_LIMIT))))){
            emptyPlaneRegion.setText(R.string.out_of_bounds);
            emptyPlaneRegion.setTextColor(getResources().getColor(R.color.colorOutOfBounds));
        }
        else if(armEmptyPlane<UTILITY_CG_LIMIT&&weightEmptyPlane<UTILITY_WEIGHT_LIMIT){
            emptyPlaneRegion.setText(R.string.utility);
            emptyPlaneRegion.setTextColor(getResources().getColor(R.color.colorUtility));
        }
        else{
            emptyPlaneRegion.setText(R.string.normal);
            emptyPlaneRegion.setTextColor(getResources().getColor(R.color.colorNormal));
        }

        if(armTakeoff<=LOWER_CG_LIMIT||armTakeoff>=UPPER_CG_LIMIT||
                weightTakeoff<=LOWER_WEIGHT_LIMIT||weightTakeoff>=UPPER_WEIGHT_LIMIT||
                (weightTakeoff-WEIGHT_INTERSECTION>=
                        ((armTakeoff-LOWER_CG_LIMIT)*((UPPER_WEIGHT_LIMIT-WEIGHT_INTERSECTION)/(CG_INTERSECTION-LOWER_CG_LIMIT))))){
            takeoffRegion.setText(R.string.out_of_bounds);
            takeoffRegion.setTextColor(getResources().getColor(R.color.colorOutOfBounds));
        }
        else if(armTakeoff<UTILITY_CG_LIMIT&&weightTakeoff<UTILITY_WEIGHT_LIMIT){
            takeoffRegion.setText(R.string.utility);
            takeoffRegion.setTextColor(getResources().getColor(R.color.colorUtility));
        }
        else{
            takeoffRegion.setText(R.string.normal);
            takeoffRegion.setTextColor(getResources().getColor(R.color.colorNormal));
        }


    }

}
