package org.frc571.springbot.hardware;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class CANTalonFX extends WPI_TalonFX {

    public static final int CONFIG_TIMEOUT = 30;

    public CANTalonFX(int port) {
        super(port);
        this.configFactoryDefault(CONFIG_TIMEOUT);
    }
}