package com.hepsiburada.mars.rover.model;

import com.hepsiburada.mars.rover.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = false)
@AllArgsConstructor
public class Position {

    private int coordinateX;
    private int coordinateY;

    public void updateCoordinates(Direction direction) {
        if (direction == null) {
            throw new IllegalArgumentException("direction required not null");
        }
        switch (direction) {
            case NORTH:
                this.coordinateY++;
                break;
            case EAST:
                this.coordinateX++;
                break;
            case SOUTH:
                this.coordinateY--;
                break;
            case WEST:
                this.coordinateX--;
                break;
            default:
                break;
        }
    }

}
