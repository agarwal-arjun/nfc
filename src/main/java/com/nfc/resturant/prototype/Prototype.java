package com.nfc.resturant.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.nfc.resturant.constants.Constants;
import com.nfc.resturant.constants.Queries;
import com.nfc.resturant.model.Merchant;

@Component
public class Prototype implements Queries.Prototype, Constants, ApplicationListener<ContextRefreshedEvent> {
private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	static boolean isFirstCycle = true;
	
	static Map<String, String> saveQueries = new HashMap<String, String>();
	static Map<String, Merchant> merchantLookUp = new ConcurrentHashMap<String, Merchant>();
	
	public static Merchant getMerchant(String merchantBusinessName) {
		return merchantLookUp.get(merchantBusinessName);
	}
	public static void addMerchant(Merchant merchant) {
		merchantLookUp.put(merchant.getBusinessName(), merchant);
	}
	public static void updateMerchant(String merchantBusinessName, Merchant newMerchant) {
		merchantLookUp.remove(merchantBusinessName);
		merchantLookUp.put(merchantBusinessName, newMerchant);
	}
	public static void removeMerchant(String merchantBusinessName) {
		merchantLookUp.remove(merchantBusinessName);
	}
	public static Map<String, String> getSaveQueries() {
		return saveQueries;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isFirstCycle) {
			String columnNames = null;
			List<String> tableList = jdbcTemplate.queryForList(GET_TABLES, String.class);
			for (String tableName : tableList) {
				columnNames = jdbcTemplate.queryForObject(GET_COLUMNS, String.class, tableName);
				saveQueries.put(tableName, createInsertStatement(tableName, columnNames));
			}
			
			List<Merchant> merchantList = jdbcTemplate.query(GET_MERCHANT, new BeanPropertyRowMapper<Merchant>(Merchant.class));
			for (Merchant merchant : merchantList) 
				merchantLookUp.put(merchant.getBusinessName(), merchant);
			
			isFirstCycle=false;
		}
	}
	
  private String createInsertStatement(String tableName, String columnNames) {
    String str = null;
    try {
      str =
          INSERT_SQL.replace(PARAM1, tableName).replace(PARAM2, columnNames)
              .replace(PARAM3, createInputParametes(columnNames));
    } catch (Exception e) {
      logger.error("error creating insert statement:"+e.getMessage());
    }
    
    return str;
  }

	
	private String createInputParametes(String columnNames) {
		StringBuilder parameterQueue = new StringBuilder();
		for(@SuppressWarnings("unused") String column : columnNames.split(COMMA)) {
			parameterQueue.append(QUESTION_MARK+COMMA);
		}
		parameterQueue.setLength(parameterQueue.length() - ONE);
		return parameterQueue.toString();
	}
	
}
