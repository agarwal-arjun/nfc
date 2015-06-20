package com.nfc.resturant.model;

import java.sql.Timestamp;

/**
 * @author Gaurav Oli
 *
 */
public class Notification {
	private String id;
	private String order_id;
	private String txn_id;
	private String table_id;
	private short is_notified;
	private long merchant_id;
	private Timestamp created_on;
	private Timestamp modified_on;
	
	public Notification(){}
	
	/**
	 * This constructor is for Order Notification when order is placed
	 * @param order_id
	 * @param merchant_id
	 */
	public Notification(String order_id, long merchant_id, String table_id) {
		this.order_id = order_id;
		this.is_notified = 0;
		this.merchant_id = merchant_id;
		this.table_id = table_id;
	}
	
	/**
	 * This constructor is for Txn Notification when bill is paid using our Gateway
	 * @param merchant_id
	 * @param txn_id
	 */
	public Notification(long merchant_id, String txn_id, String table_id) {
		this.txn_id = txn_id;
		this.is_notified = 0;
		this.merchant_id = merchant_id;
		this.table_id = table_id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getTxn_id() {
		return txn_id;
	}
	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}
	public short getIs_notified() {
		return is_notified;
	}
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	public void setIs_notified(short is_notified) {
		this.is_notified = is_notified;
	}
	public long getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}
	public Timestamp getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}
	public Timestamp getModified_on() {
		return modified_on;
	}
	public void setModified_on(Timestamp modified_on) {
		this.modified_on = modified_on;
	}
	
	@Override
	public String toString() {
		if (null!=order_id)
			return order_id+",(NULL),"+is_notified+","+merchant_id+","+table_id+","+new Timestamp(System.currentTimeMillis())+","+new Timestamp(System.currentTimeMillis());
		else
			return "(NULL),"+txn_id+","+is_notified+","+merchant_id+","+table_id+","+new Timestamp(System.currentTimeMillis())+","+new Timestamp(System.currentTimeMillis());
	}
}