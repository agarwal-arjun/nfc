package com.nfc.common.exception;
/**
 * Exception class for Social connect
 * @author arjun
 *
 */
public class SocialException extends Exception{

  private static final long serialVersionUID = 1L;
  
  private String message;
  
  public SocialException(){
      super();
  }
  
  public SocialException (String message) {
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
