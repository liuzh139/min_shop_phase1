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

   private int quantity;
   private Product product;

   public OrderItem(int quantity, Product product) {
      this.quantity = quantity;
      this.product = product;
   }

   public int getQuantity() {
      return quantity;
   }

   public Product getProduct() {
      return product;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public void setProduct(Product product) {
      this.product = product;
   }
   
   public Double getItemTotal(){
      return product.getPrice() * quantity;
   } 
}

