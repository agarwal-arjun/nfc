package com.nfc.payments.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nfc.common.exception.SocialException;
import com.nfc.payments.handler.PaypalHandlerImpl;
import com.nfc.resturant.constants.Constants;
import com.nfc.resturant.dao.SocialDao;
import com.nfc.resturant.model.Merchant;
import com.nfc.resturant.util.PropertiesUtil;

@Controller
@RequestMapping("/response")
public class CallbackInterceptor {
  Logger log = LoggerFactory.getLogger(CallbackInterceptor.class);
  private Properties props = new Properties();

  @Autowired
  private SocialDao socialDao;
  @RequestMapping(value = "/callback")
  public void callback(HttpServletRequest req, HttpServletResponse res) throws IOException {
    log.info("inside callback method");
    log.info("BankTxnID:" + req.getParameter("tx") + "\n");
    log.info("txnId:" + req.getParameter("item_number") + "\n");
    Map<String, String[]> map = req.getParameterMap();
    for (String val : map.keySet()) {
      String[] str = map.get(val);
      log.info(str[0] + "\n");
    }

    postIdentityToken(req.getParameter("tx"));

  }

  @RequestMapping(value = "/handlesocial")
  public String handleSocial(HttpServletRequest req, HttpServletResponse res) throws IOException,
      SocialException, JSONException {
    log.info("inside handleSocial method");
    String code = req.getParameter("code");
    if (code == null || code.equals("")) {
      log.info("code not found");
      throw new SocialException("code not found");
    }
    
    JSONObject tokenJson= getToken(code);
    
    try {
      String token =tokenJson.get("access_token").toString();
      String graph = getGraph(token);
      
      JSONObject json= new JSONObject(graph);
      json.put("token", token);
      json.put("expires_in",tokenJson.get("expires_in"));
      socialDao.updateSocialDetails(json);
      log.info(json.toString());
      /*data.get("first_name"),
      data.get("last_name"),
      data.get("gender"),
      data.get("email"),*/
      req.setAttribute("userName", json.get("first_name"));
      
      Cookie social = new Cookie("social", "id:"+json.get("id")+
                                          ",firstname:"+json.get("first_name")+
                                          ",lastname:"+json.get("last_name")+
                                          ",email:"+json.get("email")+
                                          ",gender:"+json.get("gender"));
      
      social.setMaxAge(json.getInt("expires_in"));
      social.setPath("/");
      res.addCookie(social);
      
      return "redirect:/response/displayPage";
    } catch (Exception e) {
      throw new SocialException("unable to insert in db:"+e.getMessage());
    }
    
  }
  
	@RequestMapping(value="/displayPage", method=RequestMethod.GET)
	public ModelAndView displayPage(HttpServletRequest request, HttpServletResponse response,HttpSession session,ModelAndView modelAndView) {
		modelAndView.setViewName("social_callback");
		Merchant merchant = (Merchant)session.getAttribute(Constants.MERCHANT);
		if(merchant==null){
			modelAndView.setViewName("error");
		}
		modelAndView.addObject("FB_LIKE_URL",merchant.getFbLikeUrl());
		modelAndView.addObject("LOGO_IMAGE", request.getContextPath()+"/logos/"+merchant.getBusinessName()+"_logo.png");
		modelAndView.addObject("FB_LIKE_NAME", merchant.getFirstName()+" "+merchant.getLastName());
		return modelAndView;
	}

  private String getGraph(String token) throws SocialException {
    try {
      String g = PropertiesUtil.getSocialProp("fb.graph.url").replace("$TOKEN", token);
      URL u = new URL(g);
      URLConnection c = u.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
      String inputLine;
      StringBuffer b = new StringBuffer();
      while ((inputLine = in.readLine()) != null)
        b.append(inputLine + "\n");
      in.close();
      return b.toString();
    } catch (Exception e) {
      throw new SocialException("unable to get graph" + e.getMessage());
    }
  }

  private JSONObject getToken(String code) throws SocialException {
    try {

      String g =
          PropertiesUtil.getSocialProp("fb.token.url")
              .replace("$CID", PropertiesUtil.getSocialProp("fb.cid"))
              .replace("$URI", URLEncoder.encode(PropertiesUtil.getSocialProp("fb.redirect.uri"),"UTF-8"))
              .replace("$SECRET", PropertiesUtil.getSocialProp("fb.client.secret"))
              .replace("$CODE",code);
              

      URL u = new URL(g);
      URLConnection c = u.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
      String inputLine;
      StringBuffer b = new StringBuffer();
      while ((inputLine = in.readLine()) != null)
        b.append(inputLine + "\n");
      in.close();
      JSONObject json = new JSONObject(b.toString());
      
      log.info(json.toString());
      return json;//.get("access_token").toString();

    } catch (Exception e) {
      // an error occurred, handle this
      throw new SocialException("unable to get token" + e.getMessage());
    }


  }

  private void postIdentityToken(String banTxnId) throws IOException {
    InputStream payment =
        PaypalHandlerImpl.class.getClassLoader().getResourceAsStream("payment.properties");
    props.load(payment);


    String postReqStr = createPostReqStr(props, banTxnId);
    HttpPost httppost = new HttpPost(postReqStr);
    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response = httpclient.execute(httppost);


    HttpEntity resEntity = response.getEntity();

    log.info("*****************Status Response Code *****************\n" + response.getStatusLine());


    if (resEntity != null) {
      log.info("*****************Status Response Message:****************\n"
          + EntityUtils.toString(response.getEntity()));
    }
    httpclient.getConnectionManager().shutdown();
    log.debug("Exiting postIdentityToken ............");

  }

  private String createPostReqStr(Properties props2, String banTxnId) {
    StringBuilder sb = new StringBuilder();
    sb.append(props.getProperty("payment.post.url"));
    sb.append("?at=" + props.getProperty("payment.post.tokencode"));
    sb.append("&tx=" + banTxnId);
    sb.append("&cmd=_notify-synch");
    log.info("Status Request:" + sb.toString());
    return sb.toString();
  }

}
