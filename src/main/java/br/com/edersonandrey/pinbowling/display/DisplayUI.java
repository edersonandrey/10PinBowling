package br.com.edersonandrey.pinbowling.display;

import java.io.File;
import java.io.IOException;

import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFormatFileException;

public interface DisplayUI {
	
	File askForFile() throws InvalidFormatFileException, IOException, InvalidFileException;
	void displayGame(File file) throws IOException, InvalidContentFileException;

}
