package com.nfc.common.exception;
/**
 * Exception class for Social connect
 * @author arjun
 *
 */
public class NFCDBException extends Exception{

  private static final long serialVersionUID = 1L;
  
  private String message;
  
  public NFCDBException(){
      super();
  }
  
  public NFCDBException (String message) {
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
