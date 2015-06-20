package com.nfc.common.exception;

/**
 * This class handles GenericException.
 */
public class GenericException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -6029106838866713166L;

  /** The custom msg. */
  private String customMsg;

  /**
   * Gets the custom msg.
   * 
   * @return the custom msg
   */
  public String getCustomMsg() {
    return customMsg;
  }

  /**
   * Sets the custom msg.
   * 
   * @param customMsg the new custom msg
   */
  public void setCustomMsg(String customMsg) {
    this.customMsg = customMsg;
  }

  /**
   * Instantiates a new generic exception.
   * 
   * @param customMsg the custom msg
   */
  public GenericException(String customMsg) {
    this.customMsg = customMsg;
  }

}
