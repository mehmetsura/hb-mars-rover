package com.hepsiburada.mars.rover.exception;

import com.hepsiburada.mars.rover.model.Plateau;
import com.hepsiburada.mars.rover.model.Position;

public class PositionNotOnPlateauException extends RuntimeException {

	private final Plateau plateau;
	private final Position position;
	
	public PositionNotOnPlateauException(Plateau plateau, Position position) {
		super("Position is not on the plateau!");
		this.plateau = plateau;
		this.position = position;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public Position getPosition() {
		return position;
	}
}