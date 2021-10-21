package com.hepsiburada.mars.rover;

import com.hepsiburada.mars.rover.enums.Instruction;
import com.hepsiburada.mars.rover.model.Plateau;
import com.hepsiburada.mars.rover.model.RoboticRover;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Log4j2
@SpringBootApplication
public class HbMarsRoverApplication implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(HbMarsRoverApplication.class);

    public static void main(String[] args) {

        if (args.length < 4) {
            logger.info("size of arguments must be minimum 4!");
            return;
        }

        if (args.length % 2 != 0) {
            logger.info("size of arguments must be even number!");
            return;
        }

        Plateau plateau = new Plateau(Integer.valueOf(args[0]), Integer.valueOf(args[1]));

        for (int i = 2; i < args.length; i = i + 2) {
            String startPositionOfRoboticRover = args[i];
            RoboticRover roboticRover = new RoboticRover(startPositionOfRoboticRover, plateau);
            List<Instruction> instructions = Instruction.prepareInstructions(args[i + 1]);
            roboticRover.processInstructions(instructions);
            log.info("Output:{}", roboticRover.reportStatus());
        }
    }

    @Override
    public void run(String... args) {
        main(args);
    }
}
