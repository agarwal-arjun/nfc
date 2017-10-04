package com.nfc.resturant.model;

import java.sql.Timestamp;

import com.nfc.resturant.constants.Constants;

public class Orders implements Constants {
	private long id;
	private String order_id;
	private String table_id;
	private int menu_id;
	private int quantity;
	private double amount;
	private String status;
	private long merchant_id;
	private Timestamp created_on;
	private Timestamp modified_on;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return order_id+PIPE+table_id+PIPE+menu_id+PIPE+quantity+PIPE+amount+PIPE+status+PIPE+created_on+PIPE+modified_on+PIPE+merchant_id;
	}
}