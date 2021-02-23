package br.com.edersonandrey.PinBowling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFormatFileException;
import br.com.edersonandrey.pinbowling.utils.FileUtils;

@SpringBootTest
public class FileTest {

	@BeforeEach
	public void deleteFile() {
		String filePath = "file.txt";
		File file = new File(filePath);
		file.delete();
		
	}
	
	@Test
	void testingValidTxtExtension() {
		String filePath1 = "file.txt";
		try {
			FileUtils.validTxtExtension(filePath1);
		} catch (InvalidFormatFileException e) {
			assertNotEquals(new InvalidFormatFileException(filePath1).getMessage(), e.getMessage());
		}
	}

	@Test
	public void testingInvalidTxtExtension() {
		String filePath2 = "file.xt";
		try {
			FileUtils.validTxtExtension(filePath2);
		} catch (InvalidFormatFileException e) {
			assertEquals(new InvalidFormatFileException(filePath2).getMessage(), e.getMessage());
		}

	}

	@Test
	public void testInvalidFile() {
		String filePath = "file.txt";
		File file = FileUtils.getFile(filePath);

		try {
			FileUtils.validateFile(file);
			assertTrue(false);
		} catch (InvalidFileException e) {
			assertEquals(new InvalidFileException(file.getPath()).getMessage(), e.getMessage());
		}

	}

	@Test
	public void testExistingValidFile() {
		String filePath = "file.txt";
		File file = FileUtils.getFile(filePath);

		try {
			file.createNewFile();
			FileUtils.validateFile(file);
		} catch (InvalidFileException e) {
			assertNotEquals(new InvalidFileException(file.getPath()).getMessage(), e.getMessage());
		} catch (IOException e) {
			assertNotEquals(new IOException().getMessage(), e.getMessage());
		}

	}
	@Test
	public void testExistingAndValidFile() {
		String filePath = "file.txt";
		File file = FileUtils.getFile(filePath);
		try {
			PrintWriter printFile = new PrintWriter(file);
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.println("Ederson\t10");
			printFile.close();
			FileUtils.getTurnsFromFile(file);
			assertTrue(true);
		} catch (IOException e) {
			assertNotEquals(new IOException().getMessage(), e.getMessage());
		} catch (InvalidContentFileException e) {
			assertNotEquals(new InvalidContentFileException().getMessage(), e.getMessage());
		}
	}
		@Test
		public void testInvalidContentFile() {
			String filePath = "file.txt";
			File file = FileUtils.getFile(filePath);
			try {
				PrintWriter printFile = new PrintWriter(file);
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10\t10");
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10");
				printFile.println("Ederson\t10");
				printFile.close();
				FileUtils.getTurnsFromFile(file);
				assertTrue(true);
			} catch (IOException e) {
				assertNotEquals(new IOException().getMessage(), e.getMessage());
			} catch (InvalidContentFileException e) {
				assertEquals(new InvalidContentFileException().getMessage(), e.getMessage());
			}
		
	}

}
