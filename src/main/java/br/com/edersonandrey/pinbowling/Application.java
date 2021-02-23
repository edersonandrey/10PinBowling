package br.com.edersonandrey.pinbowling;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.edersonandrey.pinbowling.display.DisplayFactory;
import br.com.edersonandrey.pinbowling.display.DisplayFactoryImpl;
import br.com.edersonandrey.pinbowling.display.DisplayUI;
import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFormatFileException;
import br.com.edersonandrey.pinbowling.service.GameFactory;
import br.com.edersonandrey.pinbowling.service.GameFactoryImpl;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		try {
			DisplayFactory displayFactory = new DisplayFactoryImpl(); 
			GameFactory gameFactory = new GameFactoryImpl(); 
			DisplayUI display = new BowlingGame(displayFactory, gameFactory);
			display.displayGame(display.askForFile());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFormatFileException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFileException e) {
			System.out.println(e.getMessage());
		} catch (InvalidContentFileException e) {
			System.out.println(e.getMessage());
		}

	}

}
