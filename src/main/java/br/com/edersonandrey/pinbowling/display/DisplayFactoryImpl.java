package br.com.edersonandrey.pinbowling.display;

import java.util.ArrayList;
import java.util.List;

import br.com.edersonandrey.pinbowling.enums.FrameType;
import br.com.edersonandrey.pinbowling.model.Player;
import br.com.edersonandrey.pinbowling.model.Round;
import br.com.edersonandrey.pinbowling.model.TenthRound;

public class DisplayFactoryImpl implements DisplayFactory {

	@Override
	public void printFrameByPlayer(Player player) {
		List<StringBuilder> strings = new ArrayList<StringBuilder>();
		StringBuilder frame = new StringBuilder("Frame\t\t");
		StringBuilder pinfalls = new StringBuilder("Pinfalls\t");
		StringBuilder score = new StringBuilder("Score\t\t");
		for (Round r : player.getRounds()) {
			frame.append(r.getNumber()).append("\t\t");
			score.append(r.getScoreSoFar()).append("\t\t");
			pinfalls.append(getPrintScoreForRound(r));
		}
		strings.add(frame);
		strings.add(new StringBuilder(player.getName()));
		strings.add(pinfalls);
		strings.add(score);
		strings.forEach(System.out::println);

	}

	private String getPrintScoreForRound(Round round) {
		StringBuilder s = new StringBuilder();
		if (round instanceof TenthRound) {
			s.append(round.getFirstThrow() == 10 ? "X" : round.getFirstThrow()) 
					.append("\t")
					.append(round.getSecondThrow() == 10 ? "X" : round.getSecondThrow())
					.append("\t")
					.append(((TenthRound) round).getThirdThrow() == 10 ? "X" : ((TenthRound) round).getThirdThrow());
		} else {
			if (round.getType() == FrameType.STRIKE) {
				s.append("\tX\t");
			} else {
				s.append(round.getFirstThrow())
				.append("\t").append(round.getSecondThrow())
				.append("\t");
			}
		}
		return s.toString();
	}

}
