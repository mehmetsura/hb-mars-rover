package com.hepsiburada.mars.rover.enums;

import com.hepsiburada.mars.rover.enums.common.CommonEnum;

import java.util.Arrays;

public enum Direction implements CommonEnum {
	
	NORTH("N", 1), EAST("E", 2), SOUTH("S", 3), WEST("W", 4);
	
	private String shortName;
	private Integer index;
	
	Direction(String shortName, Integer index) {
		this.shortName = shortName;
		this.index = index;
	}

	@Override
	public String getShortName() {
		return shortName;
	}
	
	public Integer getIndex() {
		return index;
	}

	public static Direction getDirectionByShortName(String shortName) {
		return Arrays.asList(values()).stream().filter(direction -> direction.shortName.equalsIgnoreCase(shortName))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("No direction found with the that value : " + shortName));
	}

	public static Direction getDirectionByIndex(Integer index) {
		return Arrays.asList(values()).stream().filter(direction -> direction.index.equals(index))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("No direction found with the that index value : " + index));
	}
	
}
