package org.frc571.springbot.commands;

import java.util.function.DoubleSupplier;

import org.frc571.springbot.subsystems.Drive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveCommand extends CommandBase {

    private Drive drive;
    private DoubleSupplier speed;
    private DoubleSupplier curvature;
    private double deadZone;
    
    private Logger logger = LoggerFactory.getLogger(DriveCommand.class);

    public DriveCommand(DoubleSupplier speed, DoubleSupplier curvature) { 
        logger.info("creating " + this.getClass().getSimpleName());
        this.speed = speed;
        this.curvature = curvature;
    }

    
    public void setSubsystem(SubsystemBase subsystem) {
        drive = (Drive)subsystem;
        addRequirements(drive);
    }

    public void setDeadZone(Double deadZone) {
        this.deadZone = deadZone;
    }

    @Override
    public void execute() {
        double power = speed.getAsDouble();
        double turn = curvature.getAsDouble();
        boolean isSpinning = (Math.abs(power) < deadZone);

        drive.drive(power * Math.abs(power), -turn*.3, isSpinning);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drive.drive(0,0,true);
    }
   
    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

}
