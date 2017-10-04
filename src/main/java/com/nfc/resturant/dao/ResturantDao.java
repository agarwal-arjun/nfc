package com.nfc.resturant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.nfc.common.exception.NFCDBException;
import com.nfc.resturant.SetObject.SetterObject;
import com.nfc.resturant.constants.Queries;
import com.nfc.resturant.model.Category;
import com.nfc.resturant.model.FeedbackVo;
import com.nfc.resturant.model.Menu;
import com.nfc.resturant.model.Orders;
import com.nfc.resturant.model.Request;
import com.nfc.resturant.model.TxnHistory;
import com.nfc.resturant.prototype.Prototype;
import com.nfc.resturant.util.NFCUtil;

@Repository
public class ResturantDao<T> extends Prototype implements Queries.ResturantQueries {
  private final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public void save(final T[] entityArray) throws NFCDBException {
		try {
			for(T entity : entityArray)
			{
				logger.info( ((Object[])entity.toString().split(PIPE)).toString());
				logger.info( ((Object[])entity.toString().split(PIPE)).length);
				jdbcTemplate.update(Prototype.getSaveQueries().get(entity.getClass().getSimpleName().toUpperCase()), (Object[])entity.toString().split(PIPE));

			}
						} catch (Exception e) {
		  logger.error("unable to save entityarray"+e.getMessage());
	      throw new NFCDBException("unable to save entityarray"+e.getMessage());
		}
	}
	
	public void save(final T entity) throws NFCDBException {
      try {
    	  jdbcTemplate.update(Prototype.getSaveQueries().get(entity.getClass().getSimpleName().toUpperCase()), (Object[])entity.toString().split(PIPE));
      } catch (Exception e) {
        logger.error("unable to save entity"+e.getMessage());
        throw new NFCDBException("unable to save entity"+e.getMessage());
      }
	}
	
	public TxnHistory save(final TxnHistory txnHistory) throws NFCDBException {
		try {
      KeyHolder holder = new GeneratedKeyHolder();
      jdbcTemplate.update(new PreparedStatementCreator() {
      	
      	@Override
      	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
      		PreparedStatement ps = con.prepareStatement(UPDATE_TXNHISTORY, Statement.RETURN_GENERATED_KEYS);
      		ps.setString(1, txnHistory.getOrderId());
      		ps.setDouble(2, txnHistory.getTaxAmt());
      		ps.setDouble(3, txnHistory.getShipAmt());
      		ps.setDouble(4, txnHistory.getTxnAmt());
      		ps.setDouble(5, txnHistory.getTotalAmt());
      		ps.setString(6, txnHistory.getBankTxnId());
      		ps.setInt(7, txnHistory.getStatus());
      		ps.setTimestamp(8, txnHistory.getCreadedDate());
      		ps.setString(9, txnHistory.getCreateBy());
      		ps.setLong(10, txnHistory.getMerchant_id());
      		return ps;
      	}
      }, holder);
      txnHistory.setTxnId(holder.getKey().longValue());
    }catch (Exception e) {
      logger.error("unable to save txnHistory"+e.getMessage());
      throw new NFCDBException("unable to save txnHistory"+e.getMessage());
    }
		return txnHistory;
	}
	
	public List<Menu> getMenuById(JSONObject orderDetails, long merchantId) throws NFCDBException {
		List<Menu> menuList = null;
		try {
			menuList = jdbcTemplate.query(GET_MENU_BY_ID.replace(PARAM1, orderDetails.getString(MENU_ID)), new Object[]{merchantId}, new BeanPropertyRowMapper<Menu>(Menu.class));
		} catch (Exception e){
		  logger.error("unable to get menu id:"+e.getMessage());
          throw new NFCDBException("unable to get menu id:"+e.getMessage());
        }
		return menuList;
	}
	
	public String verifyOrderWhenNull(String tableNumber) throws NFCDBException{
		List<Orders> order = null;
		String orderId = null;
		try {
			order = jdbcTemplate.query(GET_ORDERS_BY_TABLE_ID, new Object[]{tableNumber}, new BeanPropertyRowMapper<Orders>(Orders.class));
			if(order.size() != 0)
			orderId = order.get(0).getOrder_id();
			else
				orderId = NFCUtil.generateOrderID();
		}catch (Exception e){
          logger.error("unable to verify order id:"+e.getMessage());
          throw new NFCDBException("unable to verify order id:"+e.getMessage());
        }
		return orderId;
	}
	
	public String verifyOrderWhenNotNull(JSONObject data) throws NFCDBException{
		Orders order = null;
		String orderId = null;
		try {
			order = jdbcTemplate.queryForObject(GET_ORDERS_BY_TABLE_ID, new Object[]{Integer.parseInt(data.getString(TABLE_ID))}, new BeanPropertyRowMapper<Orders>(Orders.class));
			if(order == null)
			{
				orderId = NFCUtil.generateOrderID();
			}
			else if (!data.get(ORDER_ID).equals(order.getOrder_id()))
				orderId = NFCUtil.generateOrderID();
			else
				orderId = data.getString(ORDER_ID);
		} catch (Exception e){
          logger.error("unable to verify order id:"+e.getMessage());
          throw new NFCDBException("unable to verify order id:"+e.getMessage());
        }
		return orderId;
	}
	
	@SuppressWarnings("unchecked")
	public void updateTable(JSONObject json,String tableName) throws NFCDBException{
		  try {
		    StringBuilder sb=new StringBuilder();
		    sb.append("UPDATE "+tableName+" SET ");
		    Iterator<String> iter=json.keys();
		    int size=json.length()-1;
		    int count=0;
		    Object[] obj=new Object[size+1];
		    while (iter.hasNext()) {
		      String val=iter.next();
	          if(!val.equals(ORDER_ID_KEY)){
	            obj[count]=json.getString(val);
	            count++;
	            sb.append(val+"=?"+(count==size?" ":","));
	          }
	        }
		    sb.append("WHERE "+ORDER_ID_KEY+"=?");
		    obj[count]=json.getString(ORDER_ID_KEY);
	       jdbcTemplate.update(sb.toString(),obj);
	    }catch (Exception e){
          logger.error("unable to updateTable:"+e.getMessage());
          throw new NFCDBException("unable to updateTable:"+e.getMessage());
        }
		}
	
	public double calulateTxnAmount(JSONObject data, Request requestObj, long merchantId) throws NFCDBException {
		Double txnAmount = 0.0;
		try {
			txnAmount = jdbcTemplate.queryForObject(SUM_TXN_AMOUNT, Double.class, new Object[]{requestObj.getOrderId(), merchantId});
			data.put(TXN_AMOUNT_KEY, txnAmount);
			
		} catch (Exception e){
          logger.error("unable to calculateTxnAmt:"+e.getMessage());
          throw new NFCDBException("unable to calculateTxnAmt:"+e.getMessage());
        }
		return txnAmount;
	}
	
	public Map<Category, Map<Category, List<Menu>>> getMenuToDisplay(long merchantId) throws NFCDBException {
		List<Menu> menuList = null;
		try {
			menuList = jdbcTemplate.query(GET_MENU, new Object[]{merchantId}, new BeanPropertyRowMapper<Menu>(Menu.class));
			Map<String, List<Menu>> map = SetterObject.getMapOfMenuItemCategoryWise(menuList);
			Map<Category, Map<Category, List<Menu>>> mapWithCategory= new LinkedHashMap<Category, Map<Category, List<Menu>>>(map.size());
			for(Entry<String, List<Menu>> entry:map.entrySet()){
				String key=entry.getKey();
				Category category=jdbcTemplate.queryForObject(GET_CATEGORY_BY_ID, new Object[]{key}, new BeanPropertyRowMapper<Category>(Category.class));
				Map<Category, List<Menu>> mapInner=new LinkedHashMap<Category, List<Menu>>();
				List<Menu> list = null;
				for(Menu menu:entry.getValue()){
					Category subCategory=null;
					if(menu.getSubCategory()!=null && !menu.getSubCategory().isEmpty() && !menu.getSubCategory().equals("0"))
						subCategory=jdbcTemplate.queryForObject(GET_CATEGORY_BY_ID, new Object[]{menu.getSubCategory()}, new BeanPropertyRowMapper<Category>(Category.class));
					if(subCategory==null){
						subCategory=new Category();
					}
					menu.setSubCategoryObj(subCategory);
					menu.setCategoryObj(category);
					if (null!=mapInner.get(subCategory)) 
						mapInner.get(subCategory).add(menu);
					else {
						list = new ArrayList<Menu>();
						list.add(menu);
						mapInner.put(subCategory, list);
					}
				}
				mapWithCategory.put(category, mapInner);
			}
			return mapWithCategory;
		} catch (Exception e) {
			logger.error("unable to display menu:"+e.getMessage());
			throw new NFCDBException("unable to display menu:"+e.getMessage());
		}
	}
	
	public Map<Category, List<Menu>> getDealsToDisplay(long merchantId) throws NFCDBException {
		List<Menu> menuList = null;
		try {
			menuList = jdbcTemplate.query(GET_DOD, new Object[]{merchantId}, new BeanPropertyRowMapper<Menu>(Menu.class));
			Map<String, List<Menu>> map = SetterObject.getMapOfMenuItemCategoryWise(menuList,true);
			Map<Category, List<Menu>> mapWithCategory= new LinkedHashMap<Category, List<Menu>>(map.size());
			for(Entry<String, List<Menu>> entry:map.entrySet()){
				String key=entry.getKey();
				Category category=jdbcTemplate.queryForObject(GET_CATEGORY_BY_ID, new Object[]{key}, new BeanPropertyRowMapper<Category>(Category.class));
				mapWithCategory.put(category, entry.getValue());
			}
			return mapWithCategory;
		} catch (Exception e) {
		  logger.error("unable to display deals:"+e.getMessage());
          throw new NFCDBException("unable to display deals:"+e.getMessage());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> fetchByMerchantId(T entity, String merchantId) {
		List<T> list = null;
		try {
			list = jdbcTemplate.query(SELECT_SQL_WITH_MERCHANT_ID.replace(PARAM1, entity.getClass().getSimpleName().toUpperCase()), new Object[]{merchantId}, new BeanPropertyRowMapper(entity.getClass()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateTableStatus(int status, String merchantId, String TID){
		try {
			return jdbcTemplate.update(UPDATE_TABLE_STATUS.replace(PARAM1, String.valueOf(status)).replace(PARAM2, String.valueOf(merchantId)).replace(PARAM3, "'"+TID+"'"));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

  public int insertFeedback(FeedbackVo feedback) throws NFCDBException {
   
    try {
      return jdbcTemplate.update(INSERT_INTO_FEEDBACK,feedback.getFirstName(),feedback.getLastName(),feedback.getMobile(),
          feedback.getEmail(),feedback.getRating(),feedback.getEatOutIndex(),feedback.getComment(),feedback.getDob().isEmpty()?null:feedback.getDob(),feedback.getAnniversary().isEmpty()?null:feedback.getAnniversary(),
          feedback.getMerchant(),feedback.getUserId());
  } catch (Exception e) {
      logger.error("unable to insert feedback:"+e.getMessage());
      throw new NFCDBException("unable to insert feedback:"+e.getMessage());
  }
    
  }

public void saveOrder(List<Orders> menuToOrder) {
	try {
		for(Orders entity : menuToOrder)
		{
	       jdbcTemplate.update(INSERT_INTO_ORDER,entity.getOrder_id(), entity.getTable_id(), entity.getMenu_id(), entity.getQuantity(), entity.getAmount(), entity.getStatus(), entity.getCreated_on(), entity.getModified_on(), entity.getMerchant_id());
		}
	  } catch (Exception e) {
	      logger.error("unable to insert order:"+e.getMessage());
	    
	  }
	
}
}