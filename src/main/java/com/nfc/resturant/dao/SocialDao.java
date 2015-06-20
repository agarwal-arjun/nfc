package com.nfc.resturant.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nfc.common.exception.NFCDBException;
import com.nfc.resturant.constants.Queries;

@Repository
public class SocialDao implements Queries.SocialQueries{
  private final Log logger = LogFactory.getLog(this.getClass());
  @Autowired
  public JdbcTemplate jdbcTemplate;
  
  public void updateSocialDetails(JSONObject data) throws NFCDBException {
    
    try {
        Map<String, Object> rows=jdbcTemplate.queryForMap(IS_PRESENT_SOCIAL_USER,data.get("id"));
                
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.add(Calendar.SECOND, Integer.valueOf(data.get("expires_in").toString()));
            
        if(Integer.valueOf(rows.get("CNT").toString())>0 ){
                jdbcTemplate.update(UPDATE_SOCIAL_USER,
                    data.get("token"),sdf.format(c.getTime()),
                    data.get("id"));
        }else{
          jdbcTemplate.update(INSERT_INTO_SOCIAL_USER,
              data.get("id"),
              data.get("first_name"),
              data.get("last_name"),
              data.get("gender"),
              data.get("email"),
              "fb",
              data.get("token"),
              data.get("link"),
              sdf.format(c.getTime()));
        }
        
    } catch(Exception e) {
      logger.error("unable to update social details:"+e.getMessage());
      throw new NFCDBException("unable to update social details:"+e.getMessage());
    }
}
  
  private String checkNull(Object val) {
    return val == null ? "" : val.toString().trim();
  }
  
  
}
