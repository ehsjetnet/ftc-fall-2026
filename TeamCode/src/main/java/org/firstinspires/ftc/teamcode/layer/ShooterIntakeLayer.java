package org.firstinspires.ftc.teamcode.layer;

import java.util.Iterator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.layer.Layer;
import org.firstinspires.ftc.teamcode.layer.LayerSetupInfo;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.TeleopAgitatorTask;
import org.firstinspires.ftc.teamcode.task.TeleopFeederTask;
import org.firstinspires.ftc.teamcode.task.TeleopShooterTask;
import org.firstinspires.ftc.teamcode.task.AutoShooterTask;

public final class ShooterIntakeLayer implements Layer {

    private static final String flywheelMotorName = "flywheel";

    private static final String intakeMotorName = "bandy";

    private static final String agitatorName = "servo 1";

    private ElapsedTime timer;

    private double startTime = 0;

    private DcMotor flywheel;

    private DcMotor bandy;

    private CRServo agitator;

    private boolean isFinished;

    public ShooterIntakeLayer() {
    }

    @Override
    public void setup(LayerSetupInfo setupInfo) {
        flywheel = setupInfo.getHardwareMap().get(DcMotor.class, flywheelMotorName);
        bandy = setupInfo.getHardwareMap().get(DcMotor.class, intakeMotorName);
        agitator = setupInfo.getHardwareMap().get(CRServo.class, agitatorName);
        timer = new ElapsedTime();
        isFinished = true;
    }

    @Override
    public boolean isTaskDone() {
        return isFinished;
    }

    @Override
    public Iterator<Task> update(Iterable<Task> completed) {
        return null;
    }

    @Override
    public void acceptTask(Task task) {
        if (task instanceof AutoShooterTask) {
            AutoShooterTask castedTask = (AutoShooterTask) task;
            if (castedTask.getShoot()) {
                flywheel.setPower(1.0);
            } else if (castedTask.getIntake()) {
                bandy.setPower(1.0);
            } else if (castedTask.getEject()) {
                bandy.setPower(-1.0);
            } else if (castedTask.getShooterEject()) {
                flywheel.setPower(-1.0);
            } else {
                flywheel.setPower(0);
                bandy.setPower(0);
            }
        }
    }
}

