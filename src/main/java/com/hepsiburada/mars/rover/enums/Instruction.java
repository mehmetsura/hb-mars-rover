package com.hepsiburada.mars.rover.enums;

import com.hepsiburada.mars.rover.exception.UnexpectedInstructionException;
import com.hepsiburada.mars.rover.enums.common.CommonEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Instruction implements CommonEnum {

    MOVE_FORWARD("M"), LEFT("L"), RIGHT("R");

    private String shortName;

    Instruction(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String getShortName() {
        return shortName;
    }

    public static Instruction getInstructionByShortName(String shortName) {
        return Arrays.asList(values()).stream().filter(instruction -> instruction.shortName.equalsIgnoreCase(shortName))
                .findFirst().orElseThrow(() -> new UnexpectedInstructionException("No instruction found with the that value : " + shortName));
    }

    public static List<Instruction> prepareInstructions(String instructions) {
        if (instructions == null) {
            throw new UnexpectedInstructionException("No instruction found with the that value");
        }
        return instructions.chars().mapToObj(instruction -> (char) instruction).map(item -> getInstructionByShortName(item.toString())).collect(Collectors.toList());
    }

}
