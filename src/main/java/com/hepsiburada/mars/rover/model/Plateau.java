package com.hepsiburada.mars.rover.model;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class Plateau {

    @NonNull private int dimensionX;
    @NonNull private int dimensionY;
    private List<RoboticRover> roboticRovers = new ArrayList<>();

}