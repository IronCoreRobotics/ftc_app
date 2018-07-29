package com.ironcorerobotics.ControlClasses;

/**
 * Created by FranklinFamily on 12/26/2017.
 */

public class TheAutonomousREXSuper

{
    private TwoWheelDriveTrain drive;


    public TheAutonomousREXSuper( TwoWheelDriveTrain drive) {
        this.drive = drive;
    }

    public TwoWheelDriveTrain Drive() {
        return drive;
    }

}
