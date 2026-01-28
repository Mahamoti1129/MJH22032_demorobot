package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp Arcade Drive")
public class TeleOpDemobot extends OpMode {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;

    private Servo launcher;

    private DcMotorEx carLauncher;

    private Gamepad driveController;

    @Override
    public void init() {
        this.leftMotor = hardwareMap.get(DcMotorEx.class, "motorleft");
        this.rightMotor = hardwareMap.get(DcMotorEx.class, "motorright");
        this.driveController = gamepad1;

        leftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        rightMotor.setDirection(DcMotorEx.Direction.REVERSE);
        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

        launcher = hardwareMap.get(Servo.class, "launcher");

        this.carLauncher = hardwareMap.get(DcMotorEx.class, "carlauncher");
        carLauncher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void loop() {
        double y = driveController.left_stick_y;
        double x = driveController.right_stick_x;

        double leftPower = Range.clip(y + x, -1.0, 1.0) ;
        double rightPower = Range.clip(y - x, -1.0, 1.0) ;

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

        if (driveController.a){
            launcher.setPosition(0.0);
        } else {
            launcher.setPosition(0.5);
        }

        if (driveController.b){
            carLauncher.setPower(1.0);
        }else {
            carLauncher.setPower(0.0);
        }
    }

    @Override
    public void stop() {
        leftMotor.setPower(0d);
        rightMotor.setPower(0d);
    }
}
