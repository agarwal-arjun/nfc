package com.nfc.resturant.model;

import java.sql.Timestamp;

import com.nfc.resturant.constants.Constants;

public class TxnHistory implements Constants {
  private long txnId;
  private String orderId;
  private double taxAmt;
  private double txnAmt;
  private double shipAmt;
  private double totalAmt;
  private String bankTxnId;
  private int status;
  private Timestamp creadedDate;
  private String createBy;
  private long merchant_id;
  
  public long getTxnId() {
    return txnId;
  }
  public void setTxnId(long txnId) {
    this.txnId = txnId;
  }
  public String getOrderId() {
    return orderId;
  }
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
  public double getTaxAmt() {
    return taxAmt;
  }
  public void setTaxAmt(double taxAmt) {
    this.taxAmt = taxAmt;
  }
  public double getTxnAmt() {
    return txnAmt;
  }
  public void setTxnAmt(double txnAmt) {
    this.txnAmt = txnAmt;
  }
  public double getShipAmt() {
    return shipAmt;
  }
  public void setShipAmt(double shipAmt) {
    this.shipAmt = shipAmt;
  }
  public double getTotalAmt() {
    return totalAmt;
  }
  public void setTotalAmt(double totalAmt) {
    this.totalAmt = totalAmt;
  }
  public String getBankTxnId() {
    return bankTxnId;
  }
  public void setBankTxnId(String bankTxnId) {
    this.bankTxnId = bankTxnId;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public Timestamp getCreadedDate() {
    return creadedDate;
  }
  public void setCreadedDate(Timestamp creadedDate) {
    this.creadedDate = creadedDate;
  }
  public String getCreateBy() {
    return createBy;
  }
  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  } 
  public long getMerchant_id() {
	return merchant_id;
  }
  public void setMerchant_id(long merchant_id) {
	this.merchant_id = merchant_id;
  }
  
  @Override
  public String toString() {
      return orderId+PIPE+taxAmt+PIPE+txnAmt+PIPE+shipAmt+PIPE+totalAmt+PIPE+bankTxnId+PIPE+status+PIPE+creadedDate+PIPE+createBy+PIPE+merchant_id;
  }
  
}
