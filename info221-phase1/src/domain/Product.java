/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author liuzh139
 */
public class Product implements Comparable<Product> {

   @NotNull(message = "ID must be provided.")
   @Range(min = 1, max = 9999999, message = "ID must contain between 1 and 7 digits (inclusive).")
   private Integer id;
  
   @NotBlank(message = "Name must be provided.")
   @Length(min = 2, message = "Name must contain at least two characters.")
   private String name;
  
   @NotBlank(message = "Category must be provided.")
   private String category;
  
   @Length(min = 4, max = 50, message = "Description must be provided.")
   private String description;
 
   @NotNull(message = "Price must be provided.")
   @Range(min = 1, max = 99999, message = "Price must contain between 1 and 5 digits (inclusive).")
   private Double price;
   
   @NotNull(message = "Stock must be provided.")
   @Range(min = 0, max = 99999, message = "Stock must contain between 1 and 5 digits (inclusive).")
   private Integer stock;

   public Product() {
   }

   public Product(Integer id, String name, String description, String category, Double price, Integer stock) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.category = category;
      this.price = price;
      this.stock = stock;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public Integer getStock() {
      return stock;
   }

   public void setStock(Integer stock) {
      this.stock = stock;
   }

   
   @Override
   public int hashCode() {
      int hash = 7;
      hash = 79 * hash + Objects.hashCode(this.id);
      return hash;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Product other = (Product) obj;
      if (!Objects.equals(this.id, other.id)) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "ID: " + id + ", Name: " + name;
   }

   @Override
   public int compareTo(Product o) {
      return this.id.compareTo(o.id);
   }
}
