package org.firstinspires.ftc.teamcode;
import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

public class Outtake implements Subsystem {
    public static final Outtake INSTANCE = new Outtake();
    private Outtake(){ }
    private ServoEx kicker1 = new ServoEx("kicker1");
    private ServoEx kicker2 = new ServoEx("kicker2");
    private ServoEx turret1 = new ServoEx("turret1");
    private ServoEx turret2 = new ServoEx("turret2");
    private ServoEx spin1 = new ServoEx("spin1");
    private ServoEx spin2 = new ServoEx("spin2");

    private MotorEx outtakeMotor1 = new MotorEx("shooter1");
    private MotorEx outtakeMotor2 = new MotorEx("shooter2");
    private ControlSystem controlSystem = ControlSystem.builder()
            .vel
            .build();

}
