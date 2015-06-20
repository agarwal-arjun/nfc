package com.nfc.payments.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nfc.resturant.model.PaymentRequestVo;

public class PaypalHandlerImpl {
  private Properties props=new Properties();
  Logger log=LoggerFactory.getLogger(PaypalHandlerImpl.class);
 
  public void handleReuest(PaymentRequestVo pvo) throws IOException{
	  InputStream payment = PaypalHandlerImpl.class.getClassLoader().getResourceAsStream("payment.properties");
      props.load(payment);
      String postReqStr=createPostReqStr(pvo);
      HttpPost httppost = new HttpPost(postReqStr);
      HttpClient httpclient = new DefaultHttpClient();
      HttpResponse response = httpclient.execute(httppost);
      HttpEntity resEntity = response.getEntity();
      log.info("*****************Response status*****************\n"  + response.getStatusLine());
      if (resEntity != null) {
    	  log.info("*****************Response Message:****************\n"+EntityUtils.toString(response.getEntity()));
      }
      httpclient.getConnectionManager().shutdown();
      log.debug("Exiting handleReuest ............");   
  }

	private String createPostReqStr(PaymentRequestVo pvo) {
		  StringBuilder sb=new StringBuilder();
		  sb.append(props.getProperty("payment.post.url"));
		  sb.append("?button=buynow");
		  sb.append("&business="+props.getProperty("payment.post.mbid"));
		  sb.append("&item_name="+pvo.getItemName());
		  sb.append("&amount="+pvo.getTxnAmt());
		  sb.append("&currency_code=USD");
		  sb.append("&shipping="+pvo.getShippingCharge());
		  sb.append("&tax="+pvo.getTax());
		  sb.append("&notify_url="+props.getProperty("payment.post.callback"));
		  sb.append("&cmd=_xclick");
		  sb.append("&bn=JavaScriptButton_buynow");
		  sb.append("&env=www");
		  sb.append("&item_number="+pvo.getTxnId());
		  log.info("Post Request:"+sb.toString());
		  return sb.toString();
	}
}
