package com.nfc.resturant.constants;

public interface Constants {
	String INSERT_SQL = "INSERT INTO P1(P2) VALUES(P3)";
	String QUESTION_MARK = "?";
	String EMPTY = "";
	String COMMA = ",";
	String PIPE = "\\|";
	String ID = "id";
	String QUANTITY = "quantity";
	String MERCHANT_ID = "merchantId";
	String TABLE_ID = "tableId";
	String ORDER_ID = "orderId";
	String ORDER_DETAILS = "orderDetails";
	String MENU_ID = "menuId";
	String REQUEST = "request";
	String SUMMARY = "summary";
	String MENU = "menu";
	String RETURN = "returnpage";
	String MERCHANT_TYPE = "mType";
	String PASSIVE_MERCHANT="3";
	String MERCHANT = "merchant";
	String PARAM1 = "P1";
	String PARAM2 = "P2";
	String PARAM3 = "P3";
	String TABLE_NAME = "tableName";
	String ACTIVE = "ACTIVE";
	String INACTIVE = "INACTIVE";
	String COOKIE_ORDER = "order";
	int ACTIVE_INT = 111;
	int COMPLETE_INT = 111;

	String ORDER_ID_KEY = "ORDER_ID";
	String TXN_AMOUNT_KEY = "TOTAL_AMT";
	String BANK_REF_ID_KEY = "BANK_TXN_ID";
	String STATUS_KEY = "STATUS";

	String MESSAGE_FILE = "messages";
	int ONE = 1;
	
	String SESSION_TOKEN="SID";
	
	String REDIRECT_URL="/restaurant/home";
	String NEWLINE="\n";
	String TAB="\t";
	String ADD_NOTIFY_URL="url.notifyAdd";
	
	String SELECT_SQL_WITH_MERCHANT_ID="SELECT * FROM P1 WHERE MERCHANT_ID = ?";
	String UPDATE_TABLE_STATUS="UPDATE TABLES SET STATUS = P1 WHERE MERCHANT_ID = P2 and TID = P3";
	
	int TABLE_OCCUPIED=1;
	int TABLE_AVAILABLE=0;
	
	String SOCIAL_PLUGIN_ONLY = "social_plugin";
	String FEEDBACK_ONLY = "feedback";
}
