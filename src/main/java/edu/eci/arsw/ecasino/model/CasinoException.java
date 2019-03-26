package edu.eci.arsw.ecasino.model;

public class CasinoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7578614015362648167L;

	public CasinoException(String message) {
        super(message);
    }

    public CasinoException(String message, Throwable cause) {
        super(message, cause);
    } 

}
