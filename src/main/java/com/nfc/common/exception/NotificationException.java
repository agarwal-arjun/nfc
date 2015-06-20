package com.nfc.common.exception;
/**
 * Exception class for notification related errors
 * @author arjun
 *
 */
public class NotificationException extends Exception{

  private static final long serialVersionUID = 1L;
  
  private String message;
  
  public NotificationException(){
      super();
  }
  
  public NotificationException (String message) {
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
