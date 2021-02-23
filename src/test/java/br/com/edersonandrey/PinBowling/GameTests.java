package br.com.edersonandrey.PinBowling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.model.Player;
import br.com.edersonandrey.pinbowling.model.TenthRound;
import br.com.edersonandrey.pinbowling.model.Turn;
import br.com.edersonandrey.pinbowling.service.GameFactory;
import br.com.edersonandrey.pinbowling.service.GameFactoryImpl;
import br.com.edersonandrey.pinbowling.utils.FileUtils;

@SpringBootTest
class GameTests {

	private GameFactory factory;

	@BeforeEach
	public void deleteFile() {
		String filePath = "file.txt";
		File file = new File(filePath);
		file.delete();
	}

	@Test
	public void testInvalidGame() {
		String filePath = "file.txt";
		File file = new File(filePath);
		factory = new GameFactoryImpl();
		try {
			PrintWriter printFile = new PrintWriter(file);
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t5");
			printFile.println("Ederson\t5");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t9");
			printFile.println("Ederson\t0");
			printFile.println("Jhon\t10");
			printFile.println("Jhon\t5");
			printFile.println("Jhon\t5");
			printFile.println("Jhon\t10");
			printFile.println("Jhon\t9");
			printFile.println("Jhon\t0");
			printFile.close();
			List<Turn> turns = FileUtils.getTurnsFromFile(file);
			List<Player> players = factory.getPlayersByTurns(turns);

			assertNotEquals(players.get(0).getRounds().get(0).getFirstThrow(), 10);
		} catch (IOException e) {
			assertNotEquals(new IOException().getMessage(), e.getMessage());
		} catch (InvalidContentFileException e) {
			assertEquals(new InvalidContentFileException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testValidGame() {
		String filePath = "file.txt";
		File file = new File(filePath);
		factory = new GameFactoryImpl();
		try {
			PrintWriter printFile = new PrintWriter(file);
			printFile.println("Ederson\t10");// 1 round
			printFile.println("Ederson\t5");
			printFile.println("Ederson\t5");// 2 round
			printFile.println("Ederson\t10");// 3 round
			printFile.println("Ederson\t9");
			printFile.println("Ederson\t0");// 4 round
			printFile.println("Ederson\t10");// 5 round
			printFile.println("Ederson\t5");
			printFile.println("Ederson\t5");// 6 round
			printFile.println("Ederson\t10");// 7 round
			printFile.println("Ederson\t9");
			printFile.println("Ederson\t0");// 8 round
			printFile.println("Ederson\t10");// 9 round
			printFile.println("Ederson\t10");// 10.1 round
			printFile.println("Ederson\t10");// 10.2 round
			printFile.println("Ederson\t10");// 10.3 round

			printFile.close();
			List<Turn> turns = FileUtils.getTurnsFromFile(file);
			List<Player> players = factory.getPlayersByTurns(turns);
			
			assertEquals(players.get(0).getRounds().get(0).getFirstThrow(), 10);
			assertEquals(players.get(0).getRounds().get(1).getFirstThrow(), 5);
			assertEquals(players.get(0).getRounds().get(1).getSecondThrow(), 5);
			assertEquals(players.get(0).getRounds().get(3).getFirstThrow(), 9);
			assertEquals(players.get(0).getRounds().get(3).getSecondThrow(), 0);
			assertEquals(((TenthRound)players.get(0).getRounds().get(9)).getThirdThrow(), 10);
			
		} catch (IOException e) {
			assertNotEquals(new IOException().getMessage(), e.getMessage());
		} catch (InvalidContentFileException e) {
			assertNotEquals(new InvalidContentFileException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testPerfectGameFromFile() {
		String filePath = "perfect.txt";
		File file = new File(filePath);
		factory = new GameFactoryImpl();
		try {
			List<Turn> turns = FileUtils.getTurnsFromFile(file);
			List<Player> players = factory.getPlayersByTurns(turns);

			assertEquals(players.get(0).getRounds().get(9).getScoreSoFar(), 300);

			
		} catch (IOException e) {
			assertNotEquals(new IOException().getMessage(), e.getMessage());
		} catch (InvalidContentFileException e) {
			assertNotEquals(new InvalidContentFileException().getMessage(), e.getMessage());
		}
	}
	
	@Test
	public void testValidGameFromFile() {
		String filePath = "valid.txt";
		File file = new File(filePath);
		factory = new GameFactoryImpl();
		try {
			List<Turn> turns = FileUtils.getTurnsFromFile(file);
			List<Player> players = factory.getPlayersByTurns(turns);
			
			assertEquals(players.get(0).getRounds().get(9).getScoreSoFar(), 167);
			assertEquals(players.get(0).getRounds().get(6).getScoreSoFar(), 90);
			assertEquals(players.get(1).getRounds().get(6).getScoreSoFar(), 110);
			assertEquals(players.get(1).getRounds().get(9).getScoreSoFar(), 151);
			
			
		} catch (IOException e) {
			assertNotEquals(new IOException().getMessage(), e.getMessage());
		} catch (InvalidContentFileException e) {
			assertNotEquals(new InvalidContentFileException().getMessage(), e.getMessage());
		}
	}

}
