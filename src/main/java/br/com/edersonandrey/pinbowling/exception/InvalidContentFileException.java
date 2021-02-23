package br.com.edersonandrey.pinbowling.exception;

public class InvalidContentFileException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;;

	public InvalidContentFileException() {
        super("Your file has invalid content");
    }

}
