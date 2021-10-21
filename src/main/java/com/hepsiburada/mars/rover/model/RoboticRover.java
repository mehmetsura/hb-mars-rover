package com.hepsiburada.mars.rover.model;

import com.hepsiburada.mars.rover.common.Constants;
import com.hepsiburada.mars.rover.enums.Direction;
import com.hepsiburada.mars.rover.enums.Instruction;
import com.hepsiburada.mars.rover.exception.MoveNotCompletedException;
import com.hepsiburada.mars.rover.exception.PositionNotOnPlateauException;
import com.hepsiburada.mars.rover.validator.PositionOnThePlateauValidator;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Data
@Log4j2
public class RoboticRover {

    private Position position;
    private Direction direction;
    private boolean isMovingCompleted;
    private Plateau plateau;

    public RoboticRover(String roboticRoverPosition, Plateau plateau) {
        String[] positionParamArray = roboticRoverPosition.split(Constants.POSITION_DELIMITER_OF_ROBOTIC_ROVER);

        Integer coordinateX = Integer.valueOf(positionParamArray[Constants.PARAM_COORDINATE_X_INDEX]);
        Integer coordinateY = Integer.valueOf(positionParamArray[Constants.PARAM_COORDINATE_Y_INDEX]);

        Position positionParam = new Position(coordinateX, coordinateY);
        Direction directionParam = Direction.getDirectionByShortName(positionParamArray[Constants.PARAM_POSITION_INDEX]);

        if (!PositionOnThePlateauValidator.isValid(plateau, positionParam)) {
            throw new PositionNotOnPlateauException(plateau, positionParam);
        }

        if (plateau.getRoboticRovers().stream().anyMatch(roboticRover -> !roboticRover.isMovingCompleted)) {
            throw new MoveNotCompletedException("rover won't start to move until the another one has finished moving");
        }

        this.position = positionParam;
        this.direction = directionParam;
        this.plateau = plateau;
        this.plateau.getRoboticRovers().add(this);

        this.log.info("The starting position of the rover : {}", reportStatus());
    }

    public String reportStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append(position.getCoordinateX()).append(Constants.POSITION_DELIMITER_OF_ROBOTIC_ROVER);
        sb.append(position.getCoordinateY()).append(Constants.POSITION_DELIMITER_OF_ROBOTIC_ROVER);
        sb.append(direction.getShortName());
        return sb.toString();
    }

    public void processInstructions(List<Instruction> instructions) {
        log.info("Incoming instruction sequence will be executed : {}", instructions.stream().map(Instruction::getShortName).reduce("", String::concat));
        instructions.forEach(instruction -> changeDirectionByInstruction(direction, instruction));
        isMovingCompleted = true;
    }

    private void changeDirectionByInstruction(Direction direction, Instruction instruction) {
        Integer index = direction.getIndex();
        switch (instruction) {
            case LEFT: {
                index = index - 1;
                this.direction = index < Direction.NORTH.getIndex() ? Direction.WEST : Direction.getDirectionByIndex(index);
                break;
            }
            case RIGHT: {
                index = index + 1;
                this.direction = index > Direction.WEST.getIndex() ? Direction.NORTH : Direction.getDirectionByIndex(index);
                break;
            }
            case MOVE_FORWARD: {
                position.updateCoordinates(direction);
                if (!PositionOnThePlateauValidator.isValid(plateau, position)) {
                    throw new PositionNotOnPlateauException(plateau, position);
                }
                break;
            }
            default:
                break;
        }
    }
}
