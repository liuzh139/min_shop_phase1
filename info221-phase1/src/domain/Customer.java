/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author liuzh139
 */
public class Customer {
   private String name;
   private String address;
   private String cardDetails;
   private String username;
   private String passoword;

   public Customer(String name, String address, String cardDetails, String username, String passoword) {
      this.name = name;
      this.address = address;
      this.cardDetails = cardDetails;
      this.username = username;
      this.passoword = passoword;
   }

   public String getName() {
      return name;
   }

   public String getAddress() {
      return address;
   }

   public String getCardDetails() {
      return cardDetails;
   }

   public String getUsername() {
      return username;
   }

   public String getPassoword() {
      return passoword;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public void setCardDetails(String cardDetails) {
      this.cardDetails = cardDetails;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassoword(String passoword) {
      this.passoword = passoword;
   }

   @Override
   public String toString() {
      return "Customer{" + "name=" + name + ", address=" + address + ", cardDetails=" + cardDetails + ", username=" + username + '}';
   }
   
}
