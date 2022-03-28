package org.frc571.springbot.subsystems.simulation;

import com.ctre.phoenix.motorcontrol.TalonFXSimCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.BasePigeonSimCollection;
import com.ctre.phoenix.sensors.WPI_Pigeon2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Component("DifferentialDrive")
@Profile("simulation")
public class DriveSimulation extends SubsystemBase {
    WPI_TalonFX m_leftDrive = new WPI_TalonFX(0, "rio");
    WPI_TalonFX m_leftFollower = new WPI_TalonFX(1, "rio");
    WPI_TalonFX m_rightDrive = new WPI_TalonFX(2, "rio");
    WPI_TalonFX m_rightFollower = new WPI_TalonFX(3, "rio");

    /* Object for simulated inputs into Talon. */
    TalonFXSimCollection m_leftDriveSim = m_leftDrive.getSimCollection();
    TalonFXSimCollection m_rightDriveSim = m_rightDrive.getSimCollection();

    WPI_Pigeon2 m_pigeon = new WPI_Pigeon2(1, "rio");
    /* Object for simulated inputs into Pigeon. */
    BasePigeonSimCollection m_pigeonSim = m_pigeon.getSimCollection();
    /*
     * These numbers are an example AndyMark Drivetrain with some additional weight.
     * This is a fairly light robot.
     * Note you can utilize results from robot characterization instead of
     * theoretical numbers.
     * https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-
     * characterization/introduction.html#introduction-to-robot-characterization
     */
    final int kCountsPerRev = 2048; // Encoder counts per revolution of the motor shaft.
    final double kSensorGearRatio = 1; // Gear ratio is the ratio between the *encoder* and the wheels. On the AndyMark
                                       // drivetrain, encoders mount 1:1 with the gearbox shaft.
    final double kGearRatio = 10.71; // Switch kSensorGearRatio to this gear ratio if encoder is on the motor instead
                                     // of on the gearbox.
    final double kWheelRadiusInches = 3;
    final int k100msPerSecond = 10;

    /* Simulation model of the drivetrain */
    DifferentialDrivetrainSim m_driveSim = new DifferentialDrivetrainSim(
            DCMotor.getFalcon500(2), // 2 Falcon 500s on each side of the drivetrain.
            kGearRatio, // Standard AndyMark Gearing reduction.
            2.1, // MOI of 2.1 kg m^2 (from CAD model).
            26.5, // Mass of the robot is 26.5 kg.
            Units.inchesToMeters(kWheelRadiusInches), // Robot uses 3" radius (6" diameter) wheels.
            0.546, // Distance between wheels is _ meters.

            /*
             * The standard deviations for measurement noise:
             * x and y: 0.001 m
             * heading: 0.001 rad
             * l and r velocity: 0.1 m/s
             * l and r position: 0.005 m
             */
            null // VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005) //Uncomment this
                 // line to add measurement noise.
    );

    Field2d m_field = new Field2d();
    /*
     * Creating my odometry object. Here,
     * our starting pose is 5 meters along the long end of the field and in the
     * center of the field along the short end, facing forward.
     */
    DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(m_pigeon.getRotation2d());

    Logger logger = LoggerFactory.getLogger(DriveSimulation.class);
    
    public DriveSimulation() {
        logger.info("DriveSimulation class instance constructed");
    }
}
