package com.nfc.resturant.SetObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nfc.resturant.constants.Constants;
import com.nfc.resturant.model.Menu;
import com.nfc.resturant.model.Notification;
import com.nfc.resturant.model.Orders;
import com.nfc.resturant.model.Request;
import com.nfc.resturant.model.TxnHistory;

public abstract class SetterObject implements Constants{
	public static List<Orders> menuToOrder(List<Menu> menuList, JSONObject data, long merchantId, boolean isCreatedDate) throws JSONException {
		Map<Long, Integer> quantityMap = getMenuQuantityMap(data.getJSONArray(ORDER_DETAILS));
		
		List<Orders> orderList = new ArrayList<Orders>(menuList.size());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Orders order = null;
		for(Menu menu : menuList) {
			order = new Orders();
			order.setOrder_id(data.getString("orderId"));
			order.setTable_id(data.getString(TABLE_ID));
			order.setMenu_id(menu.getId());
			order.setQuantity(quantityMap.get(menu.getId()));
			order.setAmount(menu.getAmount());
			order.setStatus(ACTIVE);
			if (isCreatedDate)
				order.setCreated_on(timestamp);
			order.setModified_on(timestamp);
			order.setMerchant_id(merchantId);
			orderList.add(order);
		}
		return orderList;
	}
	
	private static Map<Long, Integer> getMenuQuantityMap(JSONArray jsonArray) throws JSONException{
		Map<Long, Integer> data = new HashMap<Long, Integer>(jsonArray.length());
		for (int index = 0; index<jsonArray.length(); index++) {
			JSONObject obj = (JSONObject) jsonArray.get(index);
			data.put(obj.getLong(ID), obj.getInt(QUANTITY));
		}
		return data;
	}
	
	public static TxnHistory createTxnHistory(Request requestObj, JSONObject jsonObject, long merchantId) throws JSONException {
	  TxnHistory txn=new TxnHistory();
	  txn.setCreadedDate(new Timestamp(System.currentTimeMillis()));
	  txn.setOrderId(requestObj.getOrderId());
	  txn.setTotalAmt(jsonObject.getDouble(TXN_AMOUNT_KEY));
	  txn.setCreateBy("System");
	  txn.setStatus(ACTIVE_INT);
	  txn.setMerchant_id(merchantId);
	  return txn;
	}

	public static Map<String, List<Menu>> getMapOfMenuItemCategoryWise(List<Menu> menuList) {
		Map<String, List<Menu>> menuMap = new LinkedHashMap<String, List<Menu>>(menuList.size());
		List<Menu> list = null;
		
		for (Menu menu : menuList) {
			if (null!=menuMap.get(menu.getCategory())) 
				menuMap.get(menu.getCategory()).add(menu);
			else {
				list = new ArrayList<Menu>();
				list.add(menu);
				menuMap.put(menu.getCategory(), list);
			}
		}
		return menuMap;
	}
	
	public static Map<String, List<Menu>> getMapOfMenuItemCategoryWise(List<Menu> menuList,boolean imageFLag) {
		Map<String, List<Menu>> menuMap = new LinkedHashMap<String, List<Menu>>(menuList.size());
		List<Menu> list = null;
		
		for (Menu menu : menuList) {
			if(imageFLag){
				String image=menu.getURL();
				if(image!=null && !image.isEmpty() && image.endsWith("_small.png")){
					int ind=image.indexOf("_small.png");
					String oImage=image.substring(0,ind)+".png";
					menu.setURL(oImage);
				}
			}
			if (null!=menuMap.get(menu.getCategory())) 
				menuMap.get(menu.getCategory()).add(menu);
			else {
				list = new ArrayList<Menu>();
				list.add(menu);
				menuMap.put(menu.getCategory(), list);
			}
		}
		return menuMap;
	}
	
	public static String setOrderIdForUpdateQuery(List<String> notificationList) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Notification notificationObj = null;
		StringBuffer orderIds = new StringBuffer();
		for (String notification : notificationList) {
			notificationObj = mapper.readValue(notification, Notification.class);
			orderIds.append("'"+notificationObj.getOrder_id()+"'"+COMMA);
		}
		orderIds.setLength(orderIds.length()-1);
		return orderIds.toString();
	}	
}