package com.hepsiburada.mars.rover.validator;

import com.hepsiburada.mars.rover.model.Plateau;
import com.hepsiburada.mars.rover.model.Position;

public class PositionOnThePlateauValidator {

    private PositionOnThePlateauValidator() {
        throw new IllegalStateException("Validator class");
    }

    public static boolean isValid(Plateau plateau, Position position) {
        return plateau != null && position != null && position.getCoordinateX() >= 0 && position.getCoordinateY() >= 0 && position.getCoordinateX() <= plateau.getDimensionX() && position.getCoordinateY() <= plateau.getDimensionY();
    }

}
