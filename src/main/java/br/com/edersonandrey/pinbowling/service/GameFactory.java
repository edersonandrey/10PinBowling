package br.com.edersonandrey.pinbowling.service;

import java.util.List;

import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.model.Player;
import br.com.edersonandrey.pinbowling.model.Turn;

public interface GameFactory {

	List<Player> getPlayersByTurns(List<Turn> turns) throws InvalidContentFileException;
	
}
