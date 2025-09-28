package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp

public class TeleOpManual extends LinearOpMode{
    DcMotor frontLeft, frontRight, backLeft, backRight;
    DcMotor intake;
    DcMotorEx shooter1;
    DcMotorEx shooter2;
    Servo spindexer1;
    Servo spindexer2;
    Servo kicker1;
    Servo kicker2;
    Servo turret1;
    Servo turret2;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontleft");
        frontRight = hardwareMap.dcMotor.get("frontright");
        backLeft = hardwareMap.dcMotor.get("backleft");
        backRight = hardwareMap.dcMotor.get("backright");
        intake = hardwareMap.dcMotor.get("intake");

        shooter1 = (DcMotorEx) hardwareMap.dcMotor.get("shooter1");
        shooter2 = (DcMotorEx) hardwareMap.dcMotor.get("shooter2");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        spindexer1 = hardwareMap.servo.get("spindexer1");
        spindexer2 = hardwareMap.servo.get("spindexer2");
        kicker1 = hardwareMap.servo.get("kicker1");
        kicker2 = hardwareMap.servo.get("kicker2");
        turret1 = hardwareMap.servo.get("turret1");
        turret2 = hardwareMap.servo.get("turret2");

        waitForStart();
        while (opModeIsActive()){
            shooter1.setPower(-1);
            shooter2.setPower(-1);
            if (gamepad1.b) { //slowmode
                double y = -gamepad1.left_stick_y;
                double x = gamepad1.left_stick_x;

                double frontLeftPower = (y + x)/2;
                double backLeftPower = (y - x)/2;
                double frontRightPower = (y - x)/2;
                double backRightPower = (y + x)/2;

                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(backRightPower);
            }

            else { //regular
                double y = -gamepad1.left_stick_y;
                double x = gamepad1.left_stick_x;

                double frontLeftPower = y + x;
                double backLeftPower = y - x;
                double frontRightPower = y - x;
                double backRightPower = y + x;

                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(backRightPower);
            }

            if (gamepad1.a) { //intake
                intake.setPower(-1);
            }
            else{
                intake.setPower(0);
            }

            if (gamepad1.y) { //kicker
                kicker1.setPosition(0.3);
                kicker2.setPosition(1-0.3);
            }
            else {
                kicker1.setPosition(0.52);
                kicker2.setPosition(1-0.52);
            }
            if (gamepad1.right_stick_x>0) {
                double prevposition = spindexer1.getPosition();
                double prevposition1 = spindexer2.getPosition();
                spindexer1.setPosition(prevposition+0.142);
                spindexer2.setPosition(prevposition1+0.142);
            }
            if (gamepad1.right_stick_x<0) {
                double prevposition2 = spindexer1.getPosition();
                double prevposition3 = spindexer1.getPosition();
                spindexer1.setPosition(prevposition2-0.142);
                spindexer2.setPosition(prevposition3-0.142);
            }
            if (gamepad1.right_stick_y>0) {
                double turretprev = turret1.getPosition();
                double turretprev1 = turret2.getPosition();
                turret1.setPosition(turretprev+0.01);
                turret2.setPosition(turretprev1+0.01);
            }
            if (gamepad1.right_stick_y>0) {
                double turretprev2 = turret1.getPosition();
                double turretprev3 = turret2.getPosition();
                turret1.setPosition(turretprev2-0.01);
                turret2.setPosition(turretprev3-0.01);
            }
        }
    }
}