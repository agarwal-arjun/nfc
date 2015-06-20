package com.nfc.resturant.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nfc.common.exception.NFCDBException;
import com.nfc.resturant.constants.Constants;
import com.nfc.resturant.dao.ResturantDao;

@Component
public class Verification implements Constants{
  private final Log logger = LogFactory.getLog(this.getClass());
	@SuppressWarnings("rawtypes")
	@Autowired
	private ResturantDao resturantDao;
	
	public void verifyOrder(JSONObject data) throws NFCDBException {
		try {
			if (EMPTY.equals(data.get(ORDER_ID)))
				data.put(ORDER_ID, resturantDao.verifyOrderWhenNull(data.getString(TABLE_ID)));
			else
				data.put(ORDER_ID, resturantDao.verifyOrderWhenNotNull(data));
		} catch (NFCDBException e) {
            throw e;
          }catch(Exception e){
            logger.error("unable to verify order:"+e.getMessage());      
            }
	
}
	
}
