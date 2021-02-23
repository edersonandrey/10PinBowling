package br.com.edersonandrey.pinbowling;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.edersonandrey.pinbowling.constants.ApplicationContants;
import br.com.edersonandrey.pinbowling.display.DisplayFactory;
import br.com.edersonandrey.pinbowling.display.DisplayUI;
import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFormatFileException;
import br.com.edersonandrey.pinbowling.model.Player;
import br.com.edersonandrey.pinbowling.service.GameFactory;
import br.com.edersonandrey.pinbowling.utils.FileUtils;

public class BowlingGame implements DisplayUI {

	DisplayFactory displayFactory;
	GameFactory gameFactory;

	public BowlingGame(DisplayFactory displayFactory, GameFactory gameFactory) {
		this.displayFactory = displayFactory;
		this.gameFactory = gameFactory;
	}

	@Override
	public File askForFile() throws InvalidFormatFileException, IOException, InvalidFileException {
		System.out.println(ApplicationContants.HELLO);
		System.out.println(ApplicationContants.ASK_FOR_INPUT);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String filePath;
		filePath = br.readLine();
		FileUtils.validTxtExtension(filePath);
		final File file = new File(filePath);
		FileUtils.validateFile(file);

		return file;
	}

	@Override
	public void displayGame(File file) throws IOException, InvalidContentFileException {
		for (Player player : gameFactory.getPlayersByTurns(FileUtils.getTurnsFromFile(file))) {
			displayFactory.printFrameByPlayer(player);
		}

	}

}
