package com.ironcorerobotics.ControlClasses;

/**
 * Created by Fam on 9/23/2017.
 */

public class MotorControl

{
    private double Speed;
    private boolean FirstButton;
    private boolean SecondButton;
    private double ControlledSpeed;
    private int FirstDivisionOfSpeed;
    private int SecondDivisionOfSpeed;



    public MotorControl(double Speed, boolean FirstButton, boolean SecondButton, int FirstDivisionOfSpeed, int SecondDivisionOfSpeed)

    {
        this.Speed = Speed;
        this.SecondButton = SecondButton;
        this.FirstButton = FirstButton;
        this.FirstDivisionOfSpeed = FirstDivisionOfSpeed;
        this.SecondDivisionOfSpeed = SecondDivisionOfSpeed;
    }

    public MotorControl(double speed)

    {
        Speed = speed;
    }

    public double getControlledSpeed()

    {
        return ControlMotorSpeed();
    }

    public double getSpeed() {
        return Speed;
    }

    private void setControlledSpeed(double ControlledSpeed)

    {
        this.ControlledSpeed = ControlledSpeed;
    }

    private int getFirstDivisionOfSpeed()

    {
        return FirstDivisionOfSpeed;
    }

    private int getSecondDivisionOfSpeed()

    {
        return SecondDivisionOfSpeed;
    }

    private double ControlMotorSpeed()
    {
        if(this.FirstButton == false && this.SecondButton == false)

        {
            setControlledSpeed(this.Speed/getSecondDivisionOfSpeed());
        }

        if(this.FirstButton == true && this.SecondButton == false)

        {
            setControlledSpeed(this.Speed/getFirstDivisionOfSpeed());
        }

        if(this.FirstButton == true && this.SecondButton == true)

        {
            setControlledSpeed(this.Speed);
        }

        return this.ControlledSpeed;
    }
}