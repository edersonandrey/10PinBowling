package br.com.edersonandrey.pinbowling.model;

import br.com.edersonandrey.pinbowling.constants.ApplicationContants;
import br.com.edersonandrey.pinbowling.enums.FrameType;

public class TenthRound extends Round {

	public TenthRound(int number, int firstThrow, int secondThrow, int thirdThrow, FrameType type) {
		super(ApplicationContants.TEN, firstThrow, secondThrow, type);
		this.thirdThrow = thirdThrow;
	}

	private int thirdThrow;

	public int getThirdThrow() {
		return thirdThrow;
	}

	public void setThirdThrow(int thirdThrow) {
		this.thirdThrow = thirdThrow;
	}

	@Override
	public String toString() {
		return "Round [number=" + getNumber() + ", firstThrow=" + getFirstThrow() + ", secondThrow=" + getSecondThrow()
				+ ", thirdThrow=" + thirdThrow + ",scoreSoFar=" + getScoreSoFar() + ", type=" + getType() + "]";
	}

}
