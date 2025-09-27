package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
@Config
@TeleOp
public class ManualTele extends LinearOpMode {
    public double outtakeMotorPower = 0;
    public double intakepower = 0;
    public double kickerpos = 0.5;
    public double turretpos = 0.5;
    public double spindexerIndex = 0;
    public double spindexerPos = (spindexerIndex * 0.3) + 0.17;

    private boolean prevY = false;
    private boolean prevB = false;
    private boolean prevL = false;
    private boolean prevR = false;
    double forwardPower = 0;
    double turnPower = 0;
    double strafePower = 0;

    private DcMotor frontleft, frontright, backleft, backright;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        waitForStart();

        Servo kicker1 = hardwareMap.servo.get("kicker1");
        Servo kicker2 = hardwareMap.servo.get("kicker2");
        Servo turret1 = hardwareMap.servo.get("turret1");
        Servo turret2 = hardwareMap.servo.get("turret2");
        Servo spin1 = hardwareMap.servo.get("spin1");
        Servo spin2 = hardwareMap.servo.get("spin2");
        DcMotorEx outtakeMotor1 = (DcMotorEx) hardwareMap.dcMotor.get("shooter1");
        DcMotorEx outtakeMotor2 = (DcMotorEx) hardwareMap.dcMotor.get("shooter2");

        backleft = hardwareMap.dcMotor.get("backleft");
        backright = hardwareMap.dcMotor.get("backright");
        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        DcMotor intake = hardwareMap.dcMotor.get("intake");

        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {
                forwardPower = 0.3 * -gamepad1.left_stick_y;
                turnPower = 0.2 * gamepad1.right_stick_x;
                strafePower = 0.3 * gamepad1.left_stick_x;
            } else {
                forwardPower = -gamepad1.left_stick_y;
                turnPower = gamepad1.right_stick_x;
                strafePower = gamepad1.left_stick_x;
            }

            if (!gamepad1.left_bumper) {
                outtakeMotorPower = -0.85;
            } else {
                outtakeMotorPower = -1;
            }

            if (!gamepad1.a) {
                intakepower = -1;
            } else {
                intakepower = 0;
            }

            if (gamepad1.x) {
                kickerpos = 0.3;
            } else {
                kickerpos = 0.52;

                if (gamepad1.y && !prevY) {
                    spindexerPos -= 0.142;
                    if (spindexerPos <0) {
                        spindexerPos = 0.18;
                    }
                }

                if (gamepad1.b && !prevB) {
                    spindexerPos += 0.142;
                    if (spindexerPos > 1) {
                        spindexerPos = 0.18;
                    }
                }
                if (gamepad1.right_trigger>0.8 && !prevR) {
                    turretpos += 0.01;
                    if (turretpos >0.9) {
                        turretpos = 0.9;
                    }
                }

                if (gamepad1.left_trigger>0.8 && !prevL) {
                    turretpos -= 0.01;
                    if (turretpos <0.1) {
                        turretpos = 0.1;
                    }
                }
            }

            prevY = gamepad1.y;
            prevB = gamepad1.b;
            prevL = gamepad1.left_trigger>0.8;
            prevR = gamepad1.right_trigger>0.8;
            moveBot(forwardPower, turnPower, strafePower);
            outtakeMotor1.setPower(outtakeMotorPower);
            outtakeMotor2.setPower(outtakeMotorPower);
            intake.setPower(intakepower);
            kicker1.setPosition(kickerpos);
            kicker2.setPosition(1 - kickerpos);
            turret1.setPosition(turretpos);
            turret2.setPosition(turretpos);
            spin1.setPosition(spindexerPos);
            spin2.setPosition(spindexerPos);

            telemetry.addData("spindexerIndex", spindexerIndex);
            telemetry.addData("spindexerPos", spindexerPos);
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

    public void moveBot(double forwardPower, double turnPower, double strafePower) {
        frontleft.setPower(forwardPower + turnPower + strafePower);
        frontright.setPower(forwardPower - turnPower - strafePower);
        backleft.setPower(forwardPower + turnPower - strafePower);
        backright.setPower(forwardPower - turnPower + strafePower);
    }
}
