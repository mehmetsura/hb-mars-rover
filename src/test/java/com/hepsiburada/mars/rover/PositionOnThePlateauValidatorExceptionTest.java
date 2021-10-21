package com.hepsiburada.mars.rover;

import com.hepsiburada.mars.rover.model.Plateau;
import com.hepsiburada.mars.rover.model.Position;
import com.hepsiburada.mars.rover.validator.PositionOnThePlateauValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PositionOnThePlateauValidatorExceptionTest {

    public static Stream<Arguments> positionOnThePlateauValidatorIsValidArguments() {
        return Stream.of(
                Arguments.of(new Plateau(5, 5), new Position(2, 3), true),
                Arguments.of(new Plateau(2, 2), new Position(2, 3), false),
                Arguments.of(new Plateau(2, 2), new Position(3, 2), false),
                Arguments.of(null, new Position(2, 3), false),
                Arguments.of(new Plateau(2, 2), null, false)
        );
    }

    @DisplayName("positionOnThePlateauValidatorArguments_isValid_Control")
    @ParameterizedTest
    @MethodSource("positionOnThePlateauValidatorIsValidArguments")
    void isValid_control(Plateau plateau, Position position, boolean expected) {
        Assertions.assertEquals(expected, PositionOnThePlateauValidator.isValid(plateau, position));
    }

}
