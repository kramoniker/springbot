package org.frc571.springbot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.Pigeon2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive extends Subsystem {

    private WPI_TalonFX leftLeader, rightLeader, leftFollower, rightFollower;
    private Pigeon2 pigeon2;
    private DifferentialDrive drive;

    //@TODO - add to spring config
    private int kTimeoutMs = 30;

    //to enable flipping front of robot
    boolean direction = true;

    Logger logger = LoggerFactory.getLogger(Drive.class);

    public Drive() {
        logger.info("Drive class instance constructed");
        }

        @Bean
        public void setLeftLeader(WPI_TalonFX leftLeader) {
            this.leftLeader = leftLeader;
        }

        @Bean
        public void setRightLeader(WPI_TalonFX rightLeader) {
            this.rightLeader = rightLeader;
        }

        @Bean
        public void setLeftFollower(WPI_TalonFX leftFollower) {
            this.leftFollower = leftFollower;
        }

        @Bean
        public void setRightFollower(WPI_TalonFX rightFollower) {
            this.rightFollower = rightFollower;
        }

        public void initialize() {
            drive = new DifferentialDrive(leftLeader, rightLeader);

            leftFollower.follow(leftLeader);
            rightFollower.follow(rightLeader);

            leftLeader.setInverted(TalonFXInvertType.CounterClockwise);
            rightLeader.setInverted(TalonFXInvertType.Clockwise);

            leftFollower.setInverted(InvertType.FollowMaster);
            rightFollower.setInverted(InvertType.FollowMaster);

            leftLeader.setNeutralMode(NeutralMode.Coast);
            rightLeader.setNeutralMode(NeutralMode.Coast);
            leftFollower.setNeutralMode(NeutralMode.Coast);
            rightFollower.setNeutralMode(NeutralMode.Coast);

            zeroHeading();
            
        }

        public void drive(double speed, double curvature, boolean isSpinning) {
            drive.curvatureDrive(speed * (direction ? 1 : -1), curvature, isSpinning);
        }

        public void zeroHeading() {
            	/** Zero all sensors used. */
		pigeon2.setYaw(0, kTimeoutMs);
		pigeon2.setAccumZAngle(0, kTimeoutMs);
		logger.info("[Pigeon] All sensors are zeroed.\n");
        }
}
