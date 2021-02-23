package br.com.edersonandrey.pinbowling.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.edersonandrey.pinbowling.constants.ApplicationContants;
import br.com.edersonandrey.pinbowling.exception.InvalidContentFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFileException;
import br.com.edersonandrey.pinbowling.exception.InvalidFormatFileException;
import br.com.edersonandrey.pinbowling.model.Turn;

public final class FileUtils {
	public static void validTxtExtension(String filePath) throws InvalidFormatFileException {
		int i = filePath.lastIndexOf('.');
		if (i > 0) {
			if (filePath.substring(i + 1).equals(ApplicationContants.TXT_EXTENSION))
				return;
		}
		throw new InvalidFormatFileException(filePath);

	}

	public static void validateFile(File filePath) throws InvalidFileException {
		if (!filePath.canRead())
			throw new InvalidFileException(filePath.getName());

	}

	public static List<Turn> getTurnsFromFile(File file) throws IOException, InvalidContentFileException {
		List<Turn> turns = new ArrayList<Turn>();
		String lineBuffered;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while ((lineBuffered = reader.readLine()) != null) {
			String[] line = lineBuffered.split("\\s+");
			if (line.length < 2)
				throw new InvalidContentFileException();
			turns.add(new Turn(line[0], line[1]));
		}
		reader.close();

		return turns;
	}

	public static File getFile(String filePath) {
		return new File(filePath);
	}
}
