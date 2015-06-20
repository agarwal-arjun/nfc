/**
 * 
 */
package com.nfc.resturant.model;

/**
 * @author Gaurav Oli
 *
 */
public class Request {
	private String tableId;
	private String merchantId;
	private String orderId;
	private long txn_id;
	
	public Request(String tableId, String merchantId) {
		this.tableId = tableId;
		this.merchantId = merchantId;
	}
	
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public long getTxn_id() {
		return txn_id;
	}
	public void setTxn_id(long txn_id) {
		this.txn_id = txn_id;
	}
}