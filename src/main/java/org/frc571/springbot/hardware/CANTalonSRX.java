package org.frc571.springbot.hardware;

public class CANTalonSRX extends com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX {

    public static final int CONFIG_TIMEOUT = 30;

    public CANTalonSRX(int port) {
        super(port);
        this.configFactoryDefault(CONFIG_TIMEOUT);
    }
}