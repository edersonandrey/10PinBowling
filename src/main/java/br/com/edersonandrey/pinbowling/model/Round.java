package br.com.edersonandrey.pinbowling.model;

import br.com.edersonandrey.pinbowling.enums.FrameType;

public class Round {

	private int number;
	private int firstThrow;
	private int secondThrow;
	private int scoreSoFar;
	private FrameType type;

	public Round() {
		
	}
	public Round(int number, int firstThrow, int secondThrow, FrameType type) {
		super();
		this.number = number;
		this.firstThrow = firstThrow;
		this.secondThrow = secondThrow;
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFirstThrow() {
		return firstThrow;
	}

	public void setFirstThrow(int firstThrow) {
		this.firstThrow = firstThrow;
	}

	public int getSecondThrow() {
		return secondThrow;
	}

	public void setSecondThrow(int secondThrow) {
		this.secondThrow = secondThrow;
	}

	public int getScoreSoFar() {
		return scoreSoFar;
	}

	public void setScoreSoFar(int scoreSoFar) {
		this.scoreSoFar = scoreSoFar;
	}

	public FrameType getType() {
		return type;
	}

	public void setType(FrameType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Round [number=" + number + ", firstThrow=" + firstThrow + ", secondThrow=" + secondThrow
				+ ", scoreSoFar=" + scoreSoFar + ", type=" + type + "]";
	}
	
	

}
