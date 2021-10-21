package com.hepsiburada.mars.rover;

import com.hepsiburada.mars.rover.enums.Instruction;
import com.hepsiburada.mars.rover.exception.UnexpectedInstructionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class InstructionTest {

    public static Stream<Arguments> instructionByShortNameRightScenarioArguments() {
        return Arrays.stream(Instruction.values()).map(p -> Arguments.of(p.getShortName(), p));
    }

    public static Stream<String> instructionByShortNameWrongScenarioArguments() {
        return Stream.of("A", "null", " ", null);
    }

    public static Stream<Arguments> prepareInstructionsArguments() {
        return Stream.of(Arguments.of("LMR", Arrays.asList(Instruction.LEFT, Instruction.MOVE_FORWARD, Instruction.RIGHT)));
    }

    public static Stream<String> prepareInstructionsWrongArguments() {
        return Stream.of("ASD", null);
    }

    @DisplayName("getInstructionByShortName_success")
    @ParameterizedTest
    @MethodSource("instructionByShortNameRightScenarioArguments")
    void getInstructionByShortName_success(String shortName, Instruction expected) {
        Instruction instructionByShortName = Instruction.getInstructionByShortName(shortName);
        assertEquals(expected, instructionByShortName);
    }

    @DisplayName("getInstructionByShortName_illegalArgumentException")
    @ParameterizedTest
    @MethodSource("instructionByShortNameWrongScenarioArguments")
    void getInstructionByShortName_illegalArgumentException(String shortName) {
        Exception exception = assertThrows(UnexpectedInstructionException.class, () -> Instruction.getInstructionByShortName(shortName));
        String expectedMessage = "No instruction found with the that value : " + shortName;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @DisplayName("prepareInstructions_success")
    @ParameterizedTest
    @MethodSource("prepareInstructionsArguments")
    void prepareInstructions_success(String instruction, List<Instruction> expectedInstruction) {
        List<Instruction> instructions = Instruction.prepareInstructions(instruction);
        assertArrayEquals(expectedInstruction.toArray(), instructions.toArray());
    }

    @DisplayName("prepareInstructions_illegalStateException")
    @ParameterizedTest
    @MethodSource("prepareInstructionsWrongArguments")
    void prepareInstructions_illegalStateException(String instruction) {
        Exception exception = assertThrows(UnexpectedInstructionException.class, () -> Instruction.prepareInstructions(instruction));
        String expectedMessage = "No instruction found with the that value";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
}
