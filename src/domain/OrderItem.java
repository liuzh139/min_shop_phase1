/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author liuzh139
 */
public class OrderItem {
   int quantity;

   public OrderItem(int quantity) {
      this.quantity = quantity;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
   
}
