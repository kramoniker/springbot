package org.frc571.springbot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.wpi.first.wpilibj2.command.CommandBase;

@Component
public class AutonomousCommand extends CommandBase{

    private Logger logger = LoggerFactory.getLogger(AutonomousCommand.class);

    public AutonomousCommand() { 
        logger.info("creating " + this.getClass().getSimpleName());
    }
    
}
