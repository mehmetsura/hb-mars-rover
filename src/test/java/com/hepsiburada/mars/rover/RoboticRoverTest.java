package com.hepsiburada.mars.rover;

import com.hepsiburada.mars.rover.enums.Direction;
import com.hepsiburada.mars.rover.enums.Instruction;
import com.hepsiburada.mars.rover.exception.MoveNotCompletedException;
import com.hepsiburada.mars.rover.exception.PositionNotOnPlateauException;
import com.hepsiburada.mars.rover.model.Plateau;
import com.hepsiburada.mars.rover.model.RoboticRover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RoboticRoverTest {

    public static Stream<Arguments> mainScenarioSuccessTestArguments() {
        return Stream.of(
                Arguments.of(5, 5, "1 2 N", "LMLMLMLMM", "1 3 N"),
                Arguments.of(5, 5, "3 3 E", "MMRMMRMRRM", "5 1 E")
        );
    }

    public static Stream<Arguments> roboticRoverConstructorPositionNotOnPlateauExceptionArguments() {
        return Stream.of(Arguments.of(new Plateau(2, 2), "3 3 N"));
    }

    @DisplayName("mainScenarioSuccessTestArguments")
    @ParameterizedTest
    @MethodSource("mainScenarioSuccessTestArguments")
    void main_scenario_tests(int dimensionX, int dimensionY, String startPosition, String instruction, String exceptedPosition) {
        Plateau plateau = new Plateau(dimensionX, dimensionY);
        RoboticRover roboticRover = new RoboticRover(startPosition, plateau);
        List<Instruction> instructions = Instruction.prepareInstructions(instruction);
        roboticRover.processInstructions(instructions);

        assertEquals(exceptedPosition, roboticRover.reportStatus());
    }

    @Test
    void roboticRoverConstructor_success_fieldAssignment() {
        Plateau plateau = new Plateau(6, 6);
        String roboticRoverPosition = "3 3 E";
        RoboticRover roboticRover = new RoboticRover(roboticRoverPosition, plateau);

        assertEquals(plateau, roboticRover.getPlateau());
        Assertions.assertEquals(Direction.EAST, roboticRover.getDirection());
        assertEquals(3, roboticRover.getPosition().getCoordinateX());
        assertEquals(3, roboticRover.getPosition().getCoordinateY());
        assertEquals(false, roboticRover.isMovingCompleted());
    }

    @DisplayName("roboticRover_constructor_positionNotOnPlateauException")
    @ParameterizedTest
    @MethodSource("roboticRoverConstructorPositionNotOnPlateauExceptionArguments")
    void roboticRover_constructor_positionNotOnPlateauException(Plateau plateau, String startPosition) {
        Exception exception = assertThrows(PositionNotOnPlateauException.class, () -> new RoboticRover(startPosition, plateau));
        assertEquals("Position is not on the plateau!", exception.getMessage());
    }

    @Test
    void robotic_rover_MoveNotCompletedException() {
        Plateau plateau = new Plateau(4, 4);
        RoboticRover roboticRover1 = new RoboticRover("3 3 N", plateau);
        Exception exception = assertThrows(MoveNotCompletedException.class, () -> {
            RoboticRover roboticRover2 = new RoboticRover("2 2 S", plateau);
            roboticRover2.processInstructions(Instruction.prepareInstructions("LMLMLMLMM"));
        });
        assertTrue("rover won't start to move until the another one has finished moving".contains(exception.getMessage()));
    }

    @Test
    void robotic_rover_movedPositionPlateauException() {
        Plateau plateau = new Plateau(5, 5);
        RoboticRover roboticRover = new RoboticRover("3 3 N", plateau);
        Exception exception = assertThrows(PositionNotOnPlateauException.class, () -> roboticRover.processInstructions(Instruction.prepareInstructions("MMMM")));
        assertTrue("Position is not on the plateau!".contains(exception.getMessage()));
    }

}
