package br.com.edersonandrey.pinbowling.model;

import java.util.List;

public class Player {

	private String name;
	private List<Round> rounds;

	public Player(String name, List<Round> rounds) {
		this.name = name;
		this.rounds = rounds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", rounds=" + rounds + "]";
	}

}
