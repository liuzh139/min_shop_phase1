/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author liuzh139
 */
public class ProductCollectionDAO implements ProductDAO {

   private static Map<Integer, Product> products = new TreeMap<>();
   private static Collection<String> categories = new ArrayList<>();

   @Override
   public void save(Product product) {
      products.put(product.getId(), product);
      categories.add(product.getCategory());
   }

   @Override
   public void delete(Product product) {
      products.remove(product.getId());
      categories.remove(product.getCategory());
   }

   @Override
   public Product getById(Integer ProductId) {
      return products.get(ProductId);
   }

   @Override
   public Collection<String> getCategory() {
      return categories;
   }

   @Override
   public Collection<Product> getAll() {
      return products.values();
   }

   @Override
   public Collection<Product> getByCategory(String category) {
      Collection<Product> getByCategory = new ArrayList();
      for (Product product : products.values()) {
         if (product.getCategory().equals(category)) {
            getByCategory.add(product);
         }
      }
      return getByCategory;
   }
}
