package com.ironcorerobotics.ControlClasses;

/**
 * Created by FranklinFamily on 12/26/2017.
 */

public class TheAutonomousREXSuper

{
    boolean Opmodecondition;
    TwoWheelDriveTrain drive;

    public TheAutonomousREXSuper(boolean opmodecondition, TwoWheelDriveTrain drive) {
        Opmodecondition = opmodecondition;
        this.drive = drive;
    }

    public TwoWheelDriveTrain Drive() {
        return drive;
    }
}
