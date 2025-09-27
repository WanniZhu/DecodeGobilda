package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Config
@TeleOp
public class SystemTest extends LinearOpMode {
    public static double outtakeMotorPower = 0;
    public static double motorpower = 0;
    public static double intakepower = 0;
    public static double kickerpos = 0.5;
    public static double turretpos = 0.5;
    public static double spindexerPos = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        Servo kicker1 = hardwareMap.servo.get("kicker1");
        Servo kicker2 = hardwareMap.servo.get("kicker2");
        Servo turret1 = hardwareMap.servo.get("turret1");
        Servo turret2 = hardwareMap.servo.get("turret2");
        Servo spin1 = hardwareMap.servo.get("spin1");
        Servo spin2 = hardwareMap.servo.get("spin2");
        DcMotorEx outtakeMotor1 = (DcMotorEx) hardwareMap.dcMotor.get("shooter1");
        DcMotorEx outtakeMotor2 = (DcMotorEx) hardwareMap.dcMotor.get("shooter2");
        DcMotor backleft = hardwareMap.dcMotor.get("backleft");
        DcMotor backright = hardwareMap.dcMotor.get("backright");
        DcMotor frontleft = hardwareMap.dcMotor.get("frontleft");
        DcMotor frontright = hardwareMap.dcMotor.get("frontright");
        DcMotor intake = hardwareMap.dcMotor.get("intake");
        waitForStart();

        while (opModeIsActive()){
            outtakeMotor1.setPower(outtakeMotorPower);
            outtakeMotor2.setPower(outtakeMotorPower);
            backleft.setPower(motorpower);
            backright.setPower(-1*motorpower);
            frontleft.setPower(motorpower);
            frontright.setPower(-1*motorpower);
            intake.setPower(intakepower);
            kicker1.setPosition(kickerpos);
            kicker2.setPosition(1-kickerpos);
            turret1.setPosition(turretpos);
            turret2.setPosition(turretpos);
            spin1.setPosition(spindexerPos);
            spin2.setPosition(spindexerPos);


            telemetry.addData("outtakeMotor1 pos", outtakeMotor1.getCurrentPosition());

            telemetry.addData("frontleft pos", frontleft.getCurrentPosition());
            telemetry.addData("backleft pos", backleft.getCurrentPosition());
            telemetry.addData("frontright pos", frontright.getCurrentPosition());
            telemetry.addData("backright pos", backright.getCurrentPosition());



            telemetry.addData("outtakeMotor1 current", outtakeMotor1.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("outtakeMotor2 current", outtakeMotor2.getCurrent(CurrentUnit.AMPS));


            telemetry.update();
        }
    }
}