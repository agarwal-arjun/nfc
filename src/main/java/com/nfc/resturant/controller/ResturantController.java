/**
 * @author Gaurav Oli
 */
package com.nfc.resturant.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nfc.common.exception.GenericException;
import com.nfc.common.exception.NFCDBException;
import com.nfc.payments.controller.CallbackInterceptor;
import com.nfc.resturant.SetObject.SetterObject;
import com.nfc.resturant.constants.Constants;
import com.nfc.resturant.dao.ResturantDao;
import com.nfc.resturant.model.FeedbackVo;
import com.nfc.resturant.model.Merchant;
import com.nfc.resturant.model.Notification;
import com.nfc.resturant.model.Orders;
import com.nfc.resturant.model.Request;
import com.nfc.resturant.model.Tables;
import com.nfc.resturant.model.TxnHistory;
import com.nfc.resturant.prototype.Prototype;
import com.nfc.resturant.util.PropertiesUtil;
import com.nfc.resturant.util.Verification;

@RestController
@RequestMapping("/restaurant")
public class ResturantController<T> implements Constants {
  Logger log = LoggerFactory.getLogger(ResturantController.class);
  @SuppressWarnings("rawtypes")
  @Autowired
  private ResturantDao resturantDao;

  private Properties props = new Properties();

  @Autowired
  private Verification verification;

  @RequestMapping(value = "/*", method = RequestMethod.GET)
  public String displayMerchant(@RequestParam(value = "auth", required = false) String SID,
      HttpServletRequest request, HttpServletResponse response, Model model) {
    HttpSession session = request.getSession(false);
    if (session == null || !session.getId().equals(SID))
      return "error";
    model.addAttribute("feedback", new FeedbackVo());
    Request requestObj = (Request) session.getAttribute(REQUEST);
    if (requestObj == null)
      return "error";

    if (requestObj.getTableId() != null && requestObj.getTableId().equals(SOCIAL_PLUGIN_ONLY)) {
      return "social_home";
    } else if (requestObj.getTableId() != null && requestObj.getTableId().equals(FEEDBACK_ONLY)) {
      return "feedback_home";
    }

    Cookie order =
        new Cookie("order", "{orderId : '', tableId : " + requestObj.getTableId()
            + ", merchantId : " + requestObj.getMerchantId() + "}");
    order.setPath("http://127.0.0.1:8082");
   // order.setSecure(true);
    order.setHttpOnly(true);
    response.addCookie(order);
    return "home";
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/home/{merchantBusinessName}/{tableId}", method = RequestMethod.GET)
  public String displayHome(@PathVariable("merchantBusinessName") String merchantBusinessName,
      @PathVariable("tableId") String tableId, HttpServletRequest request,
      HttpServletResponse response, HttpSession session) {
    session = request.getSession(true);

    Merchant merchant = Prototype.getMerchant(merchantBusinessName);
    if (merchant == null)
      return "unauthorized";
    String merchantId = merchant.getId() + EMPTY;

    if (merchant.getMerchantType().equals(PASSIVE_MERCHANT)) {
      // tableId="PASSIVE_0";
    } else {
      List<Tables> tableList = resturantDao.fetchByMerchantId(new Tables(), merchantId);
      Tables table = new Tables();
      table.setTID(tableId);
      table.setMerchant_id(Integer.valueOf(merchantId));

      int ind = tableList.indexOf(table);
      if (ind > -1) {
        table = tableList.get(ind);
        if (table.getStatus() == 0) {
          // code for table reservation and availability
        }
      } else if (!table.getTID().equals(SOCIAL_PLUGIN_ONLY)
          && !table.getTID().equals(FEEDBACK_ONLY)) {
        return "unknownTable";
      }
    }


    session.setAttribute(MERCHANT, merchant);
    session.setAttribute(MERCHANT_ID, merchant.getId());
    session.setAttribute(MERCHANT_TYPE, merchant.getMerchantType());
    session.setAttribute(REQUEST, new Request(tableId, merchantId));
    return "redirect:/restaurant/" + merchantBusinessName + "?auth=" + session.getId();
  }

  @RequestMapping(value = "/menu", method = RequestMethod.GET)
  public String displayMenu(HttpServletRequest request, HttpServletResponse response,
      HttpSession session) {
    return MENU;
  }

  @RequestMapping(value = "/social_ui", method = RequestMethod.GET)
  public ModelAndView displaySocial(HttpServletRequest request, HttpServletResponse response,
      HttpSession session) {
    @SuppressWarnings("deprecation")
    String FB_LOGIN =
        "https://www.facebook.com/dialog/oauth?client_id="
            + PropertiesUtil.getMessageProperty("social.clientId") + "&redirect_uri="
            + URLEncoder.encode(PropertiesUtil.getMessageProperty("social.redirect_uri"))
            + "&scope=" + PropertiesUtil.getMessageProperty("social.scope");
    ModelAndView modelAndView = new ModelAndView("social_ui");
    modelAndView.addObject("FB_LOGIN", FB_LOGIN);
    Merchant merchant = (Merchant) session.getAttribute(Constants.MERCHANT);
    if (merchant == null) {
      modelAndView.setViewName("error");
    }
    modelAndView.addObject("FB_LIKE_URL", merchant.getFbLikeUrl());
    modelAndView.addObject("LOGO_IMAGE", request.getContextPath() + "/logos/" + merchant.getBusinessName() + "_logo.png");
    modelAndView.addObject("FB_LIKE_NAME", merchant.getFirstName() + " " + merchant.getLastName());
    return modelAndView;
  }

  @RequestMapping(value = "/deals", method = RequestMethod.GET)
  public String displayDeals(HttpServletRequest request, HttpServletResponse response,
      HttpSession session, Model model) {
    Request requestObj = (Request) session.getAttribute(REQUEST);
    try {
      model.addAttribute("menu", resturantDao.getDealsToDisplay(Long.valueOf(requestObj.getMerchantId())));
    } catch (NFCDBException e) {
      e.printStackTrace();
      throw new GenericException("unable to display deals:" + e.getMessage());
    }
    return "deals";
  }

  @SuppressWarnings("unused")
  @RequestMapping(value = "/card", method = RequestMethod.GET)
  public String displayMenuCard(HttpServletRequest req, HttpServletResponse res,
      HttpSession session, Model model) throws JSONException {
    session = req.getSession(false);
    Request requestObj = (Request) session.getAttribute(REQUEST);

    try {
      model.addAttribute("menu",
          resturantDao.getMenuToDisplay(Long.valueOf(requestObj.getMerchantId())));
      String cardType = req.getParameter("cType");
      if (cardType != null && cardType.equalsIgnoreCase("list"))
        return "cardList";
      else if (cardType != null && cardType.equalsIgnoreCase("grid"))
        return "cardGrid";
    } catch (NFCDBException e) {
      e.printStackTrace();
      throw new GenericException("uable to get cards:" + e.getMessage());
    }
    return "card";
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
  public @ResponseBody String getOrder(@RequestBody String data,
      @CookieValue(COOKIE_ORDER) Cookie order, HttpServletRequest request,
      HttpServletResponse response, HttpSession session) throws JSONException,
      JsonGenerationException, JsonMappingException, IOException {
    JSONObject orderDetails = new JSONObject(data);
    JSONObject verifyOrGenerateId = new JSONObject(order.getValue());

    try {
      session = request.getSession(false);
      Request requestObj = (Request) session.getAttribute(REQUEST);

     verification.verifyOrder(verifyOrGenerateId);
      orderDetails.put(ORDER_ID, verifyOrGenerateId.get(ORDER_ID));

      log.info(SetterObject.menuToOrder(
              resturantDao.getMenuById(orderDetails, Long.valueOf(requestObj.getMerchantId())),
              orderDetails, Long.valueOf(requestObj.getMerchantId()), true).get(0).toString());
      
      resturantDao.saveOrder(SetterObject.menuToOrder(
          resturantDao.getMenuById(orderDetails, Long.valueOf(requestObj.getMerchantId())),
          orderDetails, Long.valueOf(requestObj.getMerchantId()), true));
      resturantDao.updateTableStatus(TABLE_OCCUPIED, requestObj.getMerchantId(),
          requestObj.getTableId());

      Notification notification =
          new Notification((String) verifyOrGenerateId.get(ORDER_ID), Long.valueOf(requestObj
              .getMerchantId()), requestObj.getTableId());
      ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();

      RestTemplate restTemplate = new RestTemplate();
      String result =
          restTemplate.postForObject(PropertiesUtil.getMessageProperty(ADD_NOTIFY_URL),
              mapper.writeValueAsString(notification), String.class);
      System.out.println(result);

      order.setValue("{" + ORDER_ID + ":" + orderDetails.getString(ORDER_ID) + "," + TABLE_ID + ":"
          + orderDetails.getString(TABLE_ID) + "}");
      requestObj.setOrderId(orderDetails.getString(ORDER_ID));
      response.addCookie(order);
    } catch (NFCDBException e) {
      e.printStackTrace();
      throw new GenericException("uable to place order:" + e.getMessage());
    }catch(Throwable e){
    	e.printStackTrace();
    }
    return "SUCCESS";
  }


  @RequestMapping(value = "/payOnline", method = RequestMethod.GET)
  public String payOnline(@CookieValue(COOKIE_ORDER) Cookie orderCookie, HttpSession session,
      HttpServletRequest request, Model model) throws JSONException {
    InputStream payment =
        ResturantController.class.getClassLoader().getResourceAsStream("payment.properties");
    session = request.getSession(false);
    Request requestObj = (Request) session.getAttribute(REQUEST);
    try {
      props.load(payment);
      JSONObject verifyOrGenerateId = new JSONObject(orderCookie.getValue());
      model.addAttribute(
          "txnAmount",
          resturantDao.calulateTxnAmount(verifyOrGenerateId, requestObj,
              Long.valueOf(requestObj.getMerchantId())));
      TxnHistory txnHistory =
          resturantDao.save(SetterObject.createTxnHistory(requestObj, verifyOrGenerateId,
              Long.valueOf(requestObj.getMerchantId())));
      requestObj.setTxn_id(txnHistory.getTxnId());

      model.addAttribute("mbid", props.getProperty("payment.post.mbid"));
      model.addAttribute("notifyUrl", props.getProperty("payment.post.callback"));
      model.addAttribute("postUrl", props.getProperty("payment.post.url"));
      model.addAttribute("orderId", verifyOrGenerateId.getString(ORDER_ID));
    } catch (Exception e) {
      e.printStackTrace();
      throw new GenericException("unable to pay online:" + e.getMessage());
    }
    return SUMMARY;
  }

  @RequestMapping(value = "/paySuccess", method = RequestMethod.GET)
  public String paySuccess(HttpServletRequest req, HttpServletResponse res, HttpSession session,
      Model model) throws JsonGenerationException, JsonMappingException, IOException {
    JSONObject json = new JSONObject();
    session = req.getSession(false);

    try {
      json.put(ORDER_ID_KEY, req.getParameter("item_number"));
      json.put(BANK_REF_ID_KEY, req.getParameter("tx"));
      String status = req.getParameter("st");
      if (status.equals("Completed")) {
        json.put(STATUS_KEY, COMPLETE_INT);
        JSONObject orderJson = new JSONObject();
        orderJson.put(ORDER_ID_KEY, req.getParameter("item_number"));
        orderJson.put(STATUS_KEY, "INACTIVE");
        resturantDao.updateTable(orderJson, Orders.class.getSimpleName().toUpperCase());
      } else
        json.put(STATUS_KEY, ACTIVE_INT);

      resturantDao.updateTable(json, TxnHistory.class.getSimpleName().toUpperCase());


      model.addAttribute("bankRefId", req.getParameter("tx"));
      model.addAttribute("orderId", req.getParameter("item_number"));
      model.addAttribute("amount", req.getParameter("amt"));
      model.addAttribute("status", req.getParameter("st"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return RETURN;
  }

  @RequestMapping(value = "/feedback", method = RequestMethod.POST)//,consumes=MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody String feedback(@RequestBody String feedback,
      HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
      try {
        log.info("feedback recorded:" + feedback.toString());
        ObjectMapper mapper = new ObjectMapper();
        FeedbackVo feedbackVo=mapper.readValue(feedback, FeedbackVo.class);
        log.info("feedback recorded:" + feedbackVo.toString());
        resturantDao.insertFeedback(feedbackVo);
      } catch (Exception e) {
        e.printStackTrace();
        throw new GenericException("unable to pay online:" + e.getMessage());
      }
    return feedback;
  }

 /* private void populateFeedbackObj(FeedbackVo feedback, HttpServletRequest request) {
    if (feedback.getFirstName() == null)
      feedback.setFirstName(request.getParameter("firstName"));
    if (feedback.getLastName() == null)
      feedback.setLastName(request.getParameter("lastName"));
    if (feedback.getMobile() != 0)
      feedback.setMobile(Long.parseLong(request.getParameter("mobile")));
    if (feedback.getEmail() == null)
      feedback.setEmail(request.getParameter("email"));
    if (feedback.getRating() != 0)
      feedback.setRating(Integer.parseInt(request.getParameter("rating")));

    if (feedback.getComment() == null)
      feedback.setComment(request.getParameter("comment"));
    if (feedback.getDob() == null)
      feedback.setDob(request.getParameter("dob"));
    if (feedback.getAnniversary() == null)
      feedback.setAnniversary(request.getParameter("anniversary"));
    if (feedback.getEatOutIndex() != 0)
      feedback.setEatOutIndex(Integer.parseInt(request.getParameter("eatOutIndex")));
    if (feedback.getMerchant() == null)
      feedback.setMerchant(request.getParameter("merchant"));
    if (feedback.getUserId() == null)
      feedback.setUserId(request.getParameter("userId"));

  }*/
}
