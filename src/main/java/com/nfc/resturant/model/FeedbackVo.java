package com.nfc.resturant.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class FeedbackVo implements Serializable{
/*  anniversary
  comment
  dob
  eatOutIndex
  email
  firstName
  lastName
  merchant
  mobile
  rating
  userId*/
  private static final long serialVersionUID = 1L;
private String firstName;
private String lastName;
private String mobile;
private String email;
private int rating=0;
private String comment;
private String dob;
private String anniversary;
private int eatOutIndex=0;
private String merchant;
private String userId;



public String getFirstName() {
  return firstName;
}
public void setFirstName(String firstName) {
  this.firstName = firstName;
}
public String getLastName() {
  return lastName;
}
public void setLastName(String lastName) {
  this.lastName = lastName;
}
public String getMobile() {
  return mobile;
}
public void setMobile(String mobile) {
  this.mobile = mobile;
}
public String getEmail() {
  return email;
}
public void setEmail(String email) {
  this.email = email;
}
public int getRating() {
  return rating;
}
public void setRating(int rating) {
  this.rating = rating;
}
public String getComment() {
  return comment;
}
public void setComment(String comment) {
  this.comment = comment;
}
public String getDob() {
  return dob;
}
public void setDob(String dob) {
  this.dob = dob;
}
public String getAnniversary() {
  return anniversary;
}
public void setAnniversary(String anniversary) {
  this.anniversary = anniversary;
}
public int getEatOutIndex() {
  return eatOutIndex;
}
public void setEatOutIndex(int eatOutIndex) {
  this.eatOutIndex = eatOutIndex;
}
public String getMerchant() {
  return merchant;
}
public void setMerchant(String merchant) {
  this.merchant = merchant;
}
public String getUserId() {
  return userId;
}
public void setUserId(String userId) {
  this.userId = userId;
}
@Override
public String toString() {
  return "FeedbackVo [firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile
      + ", email=" + email + ", rating=" + rating + ", comment=" + comment + ", dob=" + dob
      + ", anniversary=" + anniversary + ", eatOutIndex=" + eatOutIndex + ", merchant=" + merchant
      + ", userId=" + userId + "]";
}


}
