package org.frc571.springbot.hardware;

import java.util.function.DoubleSupplier;

/**
 * adding methods to accomodate retrival of stick values as functions.
 * Assists with spring wiring - there may be a better way with the spring notation, not sure.
 */
public class XboxController extends edu.wpi.first.wpilibj.XboxController {

    public XboxController(int port) {
        super(port);
    }

    public DoubleSupplier getLeftStickX() {
        return () -> super.getLeftX();
    }

    public DoubleSupplier getLeftStickY() {
        return () -> super.getLeftY();
    }

    public DoubleSupplier getRightStickX() {
        return () -> super.getRightX();
    }

    public DoubleSupplier getRightStickY() {
        return () -> super.getRightY();
    }

    public DoubleSupplier getLeftTrigger() {
        return () -> super.getLeftTriggerAxis();
    }

    public DoubleSupplier getRightTrigger() {
        return () -> super.getRightTriggerAxis();
    }
    
}
