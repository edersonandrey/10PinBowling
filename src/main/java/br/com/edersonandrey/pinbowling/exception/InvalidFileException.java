package br.com.edersonandrey.pinbowling.exception;

public class InvalidFileException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFileException(String filePath) {
        super(filePath + " is not a valid file");
    }

}
