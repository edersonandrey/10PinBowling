package br.com.edersonandrey.pinbowling.model;

public class Turn {

	private String playerName;
	private String pinsDown;

	public Turn(String playerName, String pinsDown) {
		super();
		this.playerName = playerName;
		this.pinsDown = pinsDown;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPinsDown() {
		return pinsDown;
	}

	@Override
	public String toString() {
		return "Turn [playerName=" + playerName + ", pinsDown=" + pinsDown + "]";
	}
	
}
