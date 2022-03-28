package org.frc571.springbot;

import org.frc571.springbot.hardware.XboxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Component
public class RobotContainer implements ApplicationContextAware {

    private ApplicationContext context;
    private final Logger logger = LoggerFactory.getLogger(RobotContainer.class);

    public CommandBase getCommand(String commandName) {
        Object o = context.getBean(commandName);
        if ((null != o) && (o instanceof SubsystemBase)) {
        return (CommandBase)o;
        } 
        logger.warn("bean '" + commandName + "' not found or not of type SubsystemBase");
        return null;
    }


    public SubsystemBase getSubsystem(String subsystemName) {
        Object o = context.getBean(subsystemName);
        if ((null != o) && (o instanceof SubsystemBase)) {
        return (SubsystemBase)o;
        } 
        logger.warn("bean '" + subsystemName + "' not found or not of type SubsystemBase");
        return null;
    }

    public XboxController getDriveStick() {
        Object o = context.getBean("driveStick");
        if ((null != o) && (o instanceof SubsystemBase)) {
        return (XboxController)o;
        } 
        logger.warn("driveStick not found or not of type XBoxController");
        return null;
    }


    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

}
