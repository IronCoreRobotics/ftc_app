package com.ironcorerobotics.ControlClasses;

/**
 * Created by Fam on 9/23/2017.
 */

public class MotorControl

{
    private double Speed;
    private boolean FirstButton;
    private boolean SecondButton;
    private boolean ThirdButton;
    private double ControlledSpeed;
    private int FirstDivisionOfSpeed;
    private int SecondDivisionOfSpeed;

    public MotorControl(double Speed, int FirstDivisionOfSpeed, int SecondDivisionOfSpeed)

    {
        this.Speed = Speed;
        this.FirstDivisionOfSpeed = FirstDivisionOfSpeed;
        this.SecondDivisionOfSpeed = SecondDivisionOfSpeed;
    }

    public MotorControl(double speed)

    {
        Speed = speed;
    }

    private void setControlledSpeed(double ControlledSpeed) {
        this.ControlledSpeed = ControlledSpeed;
    }

    public void setFirstButton(boolean firstButton) {
        FirstButton = firstButton;
    }

    public void setSecondButton(boolean secondButton) {
        SecondButton = secondButton;
    }

    public void setThirdButton(boolean thirdButton) {
        ThirdButton = thirdButton;
    }

    public double getControlledSpeed()

    {
        return ControlMotorSpeed();
    }

    public double getSpeed() {
        return Speed;
    }

    private int getFirstDivisionOfSpeed()

    {
        return FirstDivisionOfSpeed;
    }

    private int getSecondDivisionOfSpeed()

    {
        return SecondDivisionOfSpeed;
    }

    private double ControlMotorSpeed() {
        if (this.FirstButton == true)

        {
            setControlledSpeed(this.Speed);

        }
        if (this.SecondButton == true)

        {
            setControlledSpeed(this.Speed / getFirstDivisionOfSpeed());
        }

        if (this.ThirdButton == true)

        {
            setControlledSpeed(this.Speed / getSecondDivisionOfSpeed());
        }

        return this.ControlledSpeed;
    }

    public void updateMotorControl(boolean ThirdButton, boolean SecondButton, double FirstButton) {
        if (ThirdButton) {
            this.ThirdButton = true;
            this.SecondButton = false;
            this.FirstButton = false;
        } else if (SecondButton) {
            this.ThirdButton = false;
            this.SecondButton = true;
            this.FirstButton = false;
        } else if (FirstButton > 0) { //first button is a trigger which is a double{
             this.ThirdButton = false;
             this.SecondButton = false;
             this.FirstButton = true;
        }
    }

    public void initMotorControl(){
        this.setThirdButton(true);
        this.setSecondButton(false);
        this.setFirstButton(false);
    }
}
