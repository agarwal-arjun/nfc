package com.nfc.common.exception;

/**
 * @author gaurav
 * This exception is used for Restaurant DAO package only, to track the exact exception
 */
public class ResturantDAOException extends Throwable {

	private static final long serialVersionUID = 4338631122648434002L;
	
	private String message;
	
	public ResturantDAOException(){
		super();
	}
	
	public ResturantDAOException (String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
