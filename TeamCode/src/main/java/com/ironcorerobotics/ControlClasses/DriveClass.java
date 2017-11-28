package com.ironcorerobotics.ControlClasses;

/**
 * Created by Fam on 10/30/2017.
 */

public class DriveClass

{
    private float FirstDriveModeButton;
    private float SecondDriveModeButton;
    private String DriveMode1;
    private String DriveMode2;
    private MotorControl Motor1;
    private MotorControl Motor2;

    public DriveClass(float firstDriveModeButton, float secondDriveModeButton, String driveMode1, String driveMode2, MotorControl motor1, MotorControl motor2) {
        FirstDriveModeButton = firstDriveModeButton;
        SecondDriveModeButton = secondDriveModeButton;
        DriveMode1 = driveMode1;
        DriveMode2 = driveMode2;
        Motor1 = motor1;
        Motor2 = motor2;
    }

    private float getFirstDriveModeButton() {
        return FirstDriveModeButton;
    }

    private void setFirstDriveModeButton(float firstDriveModeButton) {
        FirstDriveModeButton = firstDriveModeButton;
    }

    private float getSecondDriveModeButton() {
        return SecondDriveModeButton;
    }

    private void setSecondDriveModeButton(float secondDriveModeButton) {
        SecondDriveModeButton = secondDriveModeButton;
    }

    private String getDriveMode1() {
        return DriveMode1;
    }

    private void setDriveMode1(String driveMode1) {
        DriveMode1 = driveMode1;
    }

    private String getDriveMode2() {
        return DriveMode2;
    }

    private void setDriveMode2(String driveMode2) {
        DriveMode2 = driveMode2;
    }

    private MotorControl getMotor1() {
        return Motor1;
    }

    private void setMotor1(MotorControl motor1) {
        Motor1 = motor1;
    }

    private MotorControl getMotor2() {
        return Motor2;
    }

    private void setMotor2(MotorControl motor2) {
        Motor2 = motor2;
    }

    private void SwerveDrive()
    {

    }

    private void ControllerDrive()
    {

    }

    private void OneEightyDrive()
    {

    }
}
