package org.frc571.springbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import edu.wpi.first.wpilibj.TimedRobot;

@SpringBootApplication
public class Robot extends TimedRobot {

    private ApplicationContext context;
    private Logger logger = LoggerFactory.getLogger(Robot.class);

    @Override
    public void robotInit() {
        logger.info("initializing Robot");

        String sources = "spring-bot.xml";
        SpringApplication application = new SpringApplication(Robot.class);

        // enable simulation profile if Robot is running in simulation mode
        if (Robot.isSimulation()) {
            application.setAdditionalProfiles("simulation");
            logger.info("Robot in simulation mode.");
        }

        context = application.run(sources);

        String contextName = context.getEnvironment().getProperty("spring.application.name");
        
        logger.info("Context: '" + contextName + "' successfully loaded.");
    }

}