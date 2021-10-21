package com.hepsiburada.mars.rover;

import com.hepsiburada.mars.rover.enums.Direction;
import com.hepsiburada.mars.rover.model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionTest {

    public static Stream<Arguments> updateCoordinatesTrueArguments() {
        return Stream.of(Arguments.of(new Position(3,3), Direction.WEST, 2, 3));
    }

    @DisplayName("updateCoordinates_success")
    @ParameterizedTest
    @MethodSource("updateCoordinatesTrueArguments")
    void updateCoordinates_success(Position position, Direction expectedDirection, int expectedCoordinateX, int expectedCoordinateY) {
        position.updateCoordinates(expectedDirection);
        assertEquals(expectedCoordinateX, position.getCoordinateX());
        assertEquals(expectedCoordinateY, position.getCoordinateY());
    }

    @Test
    @DisplayName("updateCoordinates_illegalArgumentException")
    void updateCoordinates_illegalArgumentException() {
        Position position = new Position(2, 2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> position.updateCoordinates(null));
        assertEquals("direction required not null", exception.getMessage());
    }

}
