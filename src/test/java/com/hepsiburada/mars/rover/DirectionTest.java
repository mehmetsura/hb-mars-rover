package com.hepsiburada.mars.rover;

import com.hepsiburada.mars.rover.enums.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    public static Stream<Arguments> directionByShortNameRightScenarioArguments() {
        return Arrays.stream(Direction.values()).map(p -> Arguments.of(p.getShortName(), p));
    }

    public static Stream<String> directionByShortNameWrongScenarioArguments() {
        return Stream.of("A", "null", " ", null);
    }

    public static Stream<Arguments> directionByIndexRightScenarioArguments() {
        return Arrays.stream(Direction.values()).map(p -> Arguments.of(p.getIndex(), p));
    }

    public static Stream<Integer> directionByIndexWrongScenarioArguments() {
        return Stream.of(5, -1, null);
    }

    @DisplayName("getDirectionByShortName_success")
    @ParameterizedTest
    @MethodSource("directionByShortNameRightScenarioArguments")
    void getDirectionByShortName_success(String shortName, Direction expected) {
        Direction directionByShortName = Direction.getDirectionByShortName(shortName);
        assertEquals(expected, directionByShortName);
    }

    @DisplayName("getDirectionByShortName_illegalArgumentException")
    @ParameterizedTest
    @MethodSource("directionByShortNameWrongScenarioArguments")
    void getDirectionByShortName_illegalArgumentException(String shortName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Direction.getDirectionByShortName(shortName));
        String expectedMessage = "No direction found with the that value : " + shortName;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @DisplayName("getDirectionByIndex_success")
    @ParameterizedTest
    @MethodSource("directionByIndexRightScenarioArguments")
    void getDirectionByIndex_success(Integer index, Direction expected) {
        Direction directionByIndex = Direction.getDirectionByIndex(index);
        assertEquals(expected, directionByIndex);
    }

    @DisplayName("getDirectionByIndex_illegalArgumentException")
    @ParameterizedTest
    @MethodSource("directionByIndexWrongScenarioArguments")
    void getDirectionByIndex_illegalArgumentException(Integer index) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Direction.getDirectionByIndex(index));
        String expectedMessage = "No direction found with the that index value : " + index;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
