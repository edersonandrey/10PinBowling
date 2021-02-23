package br.com.edersonandrey.pinbowling.exception;

public class InvalidFormatFileException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFormatFileException(String filePath) {
        super(filePath + " is not a valid format file");
    }

}
