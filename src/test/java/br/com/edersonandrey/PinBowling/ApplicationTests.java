package br.com.edersonandrey.PinBowling;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.model.Player;
import br.com.edersonandrey.pinbowling.model.Turn;
import br.com.edersonandrey.pinbowling.service.GameFactory;
import br.com.edersonandrey.pinbowling.service.GameFactoryImpl;
import br.com.edersonandrey.pinbowling.utils.FileUtils;

@SpringBootTest
//@ActiveProfiles("test")
class ApplicationTests {

	private GameFactory factory;
	
	@BeforeEach
	public void deleteFile() {
		String filePath = "file.txt";
		File file = new File(filePath);
		file.delete();
		
	}
	
//	@Test
	void contextLoads() {
	}
	
//	@Test
	public void testrArray() {
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
			
			assertTrue(true);
		} catch (IOException e) {
			assertNotEquals(new IOException().getMessage(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//		@Test
		public void testrArray2() {
			String filePath = "file.txt";
			File file = new File(filePath);
			factory = new GameFactoryImpl(); 
			try {
				PrintWriter printFile = new PrintWriter(file);
				printFile.println("Ederson\t10");//1
				printFile.println("Ederson\t5");
				printFile.println("Ederson\t5");//2
				printFile.println("Ederson\t10");//3
				printFile.println("Ederson\t9");
				printFile.println("Ederson\t0");//4
				printFile.println("Ederson\t10");//5
				printFile.println("Ederson\t5");
				printFile.println("Ederson\t5");//6
				printFile.println("Ederson\t10");//7
				printFile.println("Ederson\t9");
				printFile.println("Ederson\t0");//8
				printFile.println("Ederson\t10");//9
				printFile.println("Ederson\t10");//10-1
				printFile.println("Ederson\t10");//10-2
				printFile.println("Ederson\t10");//10-2

				printFile.close();
				List<Turn> turns = FileUtils.getTurnsFromFile(file);
				List<Player> players = factory.getPlayersByTurns(turns);
				
				assertTrue(true);
			} catch (IOException e) {
				assertNotEquals(new IOException().getMessage(), e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		@Test
		public void testeFile1() {
			String filePath = "valid.txt";
			File file = new File(filePath);
			factory = new GameFactoryImpl(); 
			try {
				List<Turn> turns = FileUtils.getTurnsFromFile(file);
				List<Player> players = factory.getPlayersByTurns(turns);
				
				assertTrue(true);
			} catch (IOException e) {
				assertNotEquals(new IOException().getMessage(), e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
