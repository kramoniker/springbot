package org.frc571.springbot.hardware;

import com.revrobotics.CANSparkMaxLowLevel;

/**
 * wrapper for com.revrobotics.CANSparkMax which automatically
 * restores factory defaults upon construction.
 * This ensures spring bean initialization is predictable.
 * 
 */
public class CANSparkMax extends com.revrobotics.CANSparkMax {
    
    public CANSparkMax(int port, CANSparkMaxLowLevel.MotorType type) {
        super(port, type);
        restoreFactoryDefaults();
    }
}
