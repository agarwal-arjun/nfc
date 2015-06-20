package com.nfc.resturant.test;

import org.springframework.web.client.RestTemplate;

import com.nfc.resturant.model.Notification;

public class CallREST {
	public static void main(String args[]) {
		final String uri = "http://localhost:8080/mercury/message/add";
		Notification notification=new Notification(2000, "323232", "32");
		RestTemplate restTemplate = new RestTemplate();
		Notification result = restTemplate.postForObject(uri, notification,
				Notification.class);
		System.out.println(result);
	}
}
