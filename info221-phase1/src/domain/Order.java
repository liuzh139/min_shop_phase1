/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author liuzh139
 */
public class Order {
   private String id;
   private Date orderDate = new Date();
   private Customer customer;
   private Collection<OrderItem> orderItem = new ArrayList<OrderItem>();

   public Order(Customer customer) {
      this.customer = customer;
   }

   public String getId() {
      return id;
   }

   public Date getOrderDate() {
      return orderDate;
   }

   public Customer getCustomer() {
      return customer;
   }

   public Collection<OrderItem> getOrderItem() {
      return orderItem;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setOrderDate(Date orderDate) {
      this.orderDate = orderDate;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public void setOrderItem(Collection<OrderItem> orderItem) {
      this.orderItem = orderItem;
   }
   
   public Double getTotal(){
      double result = 0;
      for(OrderItem s: orderItem){
         result += s.getItemTotal();  
      }
      return result;
   }
   
   public void addItem(OrderItem orderItem){
      this.orderItem.add(orderItem);
   }
}
