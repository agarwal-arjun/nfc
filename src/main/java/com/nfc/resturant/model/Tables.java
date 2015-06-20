package com.nfc.resturant.model;

import com.nfc.resturant.constants.Constants;

public class Tables implements Constants {
	private int Id;
	private String TID;
	private int status;
	private int reserved;
	private int merchant_id;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	public int getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}
	
	@Override
	public String toString() {
		return TID + PIPE + status+ PIPE + reserved + PIPE + merchant_id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TID == null) ? 0 : TID.hashCode());
		result = prime * result + merchant_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tables other = (Tables) obj;
		if (TID == null) {
			if (other.TID != null)
				return false;
		} else if (!TID.equals(other.TID))
			return false;
		if (merchant_id != other.merchant_id)
			return false;
		return true;
	}
}