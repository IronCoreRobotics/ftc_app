package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Fam on 11/4/2017.
 */
@TeleOp(name = "LiftSystem")
public class LiftSystem extends OpMode{
    // Down 0
    // Middle One Glyph 3200
    // Top Two Glyphs 5600

    long loc;

    DcMotor lift;

    @Override
    public void init() {

        lift = hardwareMap.dcMotor.get("LiftMotor");
        loc = 0;
    }

    @Override
    public void loop() {

        lift.setPower(gamepad1.left_stick_y);
        loc = lift.getCurrentPosition();
        telemetry.addData("motor: ", loc);

    }
}
