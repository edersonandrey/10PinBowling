package br.com.edersonandrey.pinbowling.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.edersonandrey.pinbowling.constants.ApplicationContants;
import br.com.edersonandrey.pinbowling.enums.FrameType;
import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.model.Player;
import br.com.edersonandrey.pinbowling.model.Round;
import br.com.edersonandrey.pinbowling.model.TenthRound;
import br.com.edersonandrey.pinbowling.model.Turn;

public class GameFactoryImpl implements GameFactory {

	public GameFactoryImpl() {
	}

	@Override
	public List<Player> getPlayersByTurns(List<Turn> turns) throws InvalidContentFileException {
		List<Player> players = new ArrayList<Player>();
		Map<String, List<String>> map = new HashMap<>();
		List<String> scoreList;
		for (Turn t : turns) {
			scoreList = map.get(t.getPlayerName());
			if (scoreList != null) {
				scoreList.add(t.getPinsDown());
			} else {
				scoreList = new ArrayList<String>();
				scoreList.add(t.getPinsDown());
				map.put(t.getPlayerName(), scoreList);
			}
		}

		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			players.add(generatePlayer(entry.getKey(), entry.getValue()));

		}

		return players;
	}

	private Player generatePlayer(String name, List<String> scoreList) throws InvalidContentFileException {
		int roundNumber = 1;
		List<Round> rounds = new ArrayList<Round>();
		for (int i = 0; i < scoreList.size(); i++) {
			Round round = new Round();
			if (roundNumber < ApplicationContants.TEN) {
				if (scoreList.get(i).equals(Integer.toString(ApplicationContants.TEN))) {
					round = new Round(roundNumber, ApplicationContants.TEN, ApplicationContants.ZERO, FrameType.STRIKE);
					round.setScoreSoFar(calculateScore(roundNumber, scoreList, i, FrameType.STRIKE, rounds));

				} else if ((getScoreByString(scoreList.get(i))
						+ getScoreByString(scoreList.get(i + 1))) == ApplicationContants.TEN) {
					round = new Round(roundNumber, getScoreByString(scoreList.get(i)),
							getScoreByString(scoreList.get(i + 1)), FrameType.SPARE);
					round.setScoreSoFar(calculateScore(roundNumber, scoreList, ++i, FrameType.SPARE, rounds));

				} else {
					round = new Round(roundNumber, getScoreByString(scoreList.get(i)),
							getScoreByString(scoreList.get(i + 1)), FrameType.NORMAL);
					round.setScoreSoFar(calculateScore(roundNumber, scoreList, ++i, FrameType.NORMAL, rounds));

				}

			} else {
				int thirdThrow = ((i + 3) == scoreList.size()) ? getScoreByString(scoreList.get(i + 2)) : 0;

				TenthRound tenthRound = new TenthRound(roundNumber, getScoreByString(scoreList.get(i)),
						getScoreByString(scoreList.get(i + 1)), thirdThrow, FrameType.NORMAL);

				tenthRound.setScoreSoFar(calculateLastScore(roundNumber, scoreList, i, thirdThrow, rounds));

				rounds.add(tenthRound);
				return new Player(name, rounds);
			}
			roundNumber++;
			rounds.add(round);
		}
		throw new InvalidContentFileException();
	}

	private int calculateScore(int roundNumber, List<String> scoreList, int index, FrameType type, List<Round> rounds) {
		int scoreSofar = roundNumber == 1 ? 0 : rounds.get(roundNumber - 2).getScoreSoFar();
		switch (type.ordinal()) {
		case 0:
			return scoreSofar + +getScoreByString(scoreList.get(index + 1)) + ApplicationContants.TEN;
		case 1:
			return scoreSofar + getScoreByString(scoreList.get(index + 1)) + getScoreByString(scoreList.get(index + 2))
					+ ApplicationContants.TEN;
		default:
			return scoreSofar + getScoreByString(scoreList.get(index)) + getScoreByString(scoreList.get(index - 1));

		}

	}

	private int calculateLastScore(int roundNumber, List<String> scoreList, int index, int thirdThrow,
			List<Round> rounds) {
		int scoreSofar = rounds.get(roundNumber - 2).getScoreSoFar();
		return scoreSofar + getScoreByString(scoreList.get(index)) + getScoreByString(scoreList.get(index + 1))
				+ thirdThrow;

	}

	private int getScoreByString(String s) {
		if (s.equals("F")) {
			return 0;
		}
		return Integer.parseInt(s);
	}

}
