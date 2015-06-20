package com.nfc.resturant.model;

public class PaymentRequestVo {
	private String itemName=null;
	private String orderId=null;
	private double tax=0;
	private double txnAmt=0;
	private double shippingCharge=0;
	private long txnId=0;
	
	public String getItemName() {
	  return itemName;
	}
	public void setItemName(String itemName) {
	  this.itemName = itemName;
	}
	public String getOrderId() {
	  return orderId;
	}
	public void setOrderId(String orderId) {
	  this.orderId = orderId;
	}
	public double getTax() {
	  return tax;
	}
	public void setTax(double tax) {
	  this.tax = tax;
	}
	public double getTxnAmt() {
	  return txnAmt;
	}
	public void setTxnAmt(double txnAmt) {
	  this.txnAmt = txnAmt;
	}
	public double getShippingCharge() {
	  return shippingCharge;
	}
	public void setShippingCharge(double shippingCharge) {
	  this.shippingCharge = shippingCharge;
	}
	public long getTxnId() {
	  return txnId;
	}
	public void setTxnId(long txnId) {
	  this.txnId = txnId;
	}
}