package edu.eci.arsw.ecasino.persistence;

public class CasinoPersistenceException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4311329307929654836L;

	public CasinoPersistenceException(String message) {
        super(message);
    }

    public CasinoPersistenceException(String message, Throwable cause) {
        super(message, cause);
    } 

}
