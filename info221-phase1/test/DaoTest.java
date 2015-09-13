/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProductCollectionDAO;
import dao.ProductDAO;
import domain.Product;
import java.util.Collection;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liuzh139
 */
public class DaoTest {

   private ProductDAO dao = new ProductCollectionDAO();
   private int muffinId = 11111;
   private Product muffin;
   private int juiceId = 22222;
   private Product juice;

   public DaoTest() {
   }

   @Before
   public void setUp() {
      muffin = new Product(muffinId, "Muffin", "Cupcake", "Snack", 5.00, 10.00);
      juice = new Product(juiceId, "Juice", "Orange juice", "SoftDrink", 4.00, 20.00);
      dao.save(muffin);
      dao.save(juice);
   }

   @After
   public void tearDown() {
      dao.delete(muffin);
      dao.delete(juice);
   }

   @Test
   public void testDaoSaveAndDelete() {
// create product for testing
      Product candybar = new Product(33333, "CandyBar", "Strawberry", "Snack", 2.00, 10.00);
// save the product using DAO
      dao.save(candybar);
// retreive the same product via DAO
      Product retrieved = dao.getById(33333);
// ensure that the product we saved is the one we got back
      assertEquals("Retrieved product should be the same as the saved one",
              candybar, retrieved);
// delete the product via the DAO
      dao.delete(candybar);
// try to retrieve the deleted product
      retrieved = dao.getById(33333);
// ensure that the product was not retrieved (should be null)
      assertNull("Product should no longer exist", retrieved);
   }

   @Test
   public void testDaoGetAll() {
// call getAll
      Collection<Product> products = dao.getAll();
// ensure the result includes the test products
      assertTrue("Muffin should exist in the result", products.contains(muffin));
      assertTrue("CandyBar should exist in the result", products.contains(juice));
// find muffin .. result is not a map, so we have to sequentially search
      for (Product s : products) {
         if (s.equals(muffin)) {
// ensure that muffin’s details were correctly retrieved
            assertEquals(muffin.getId(), s.getId());
            assertEquals(muffin.getName(), s.getName());
            assertEquals(muffin.getCategory(), s.getCategory());
            assertEquals(muffin.getDescription(), s.getDescription());
            assertEquals(muffin.getPrice(), s.getPrice(), 0);
            assertEquals(muffin.getStock(), s.getStock());

         }
      }
   }

   @Test
   public void testDaoGetById() {
      Collection<Product> products = dao.getAll();
// get muffin using getById method
      Product retrieved = dao.getById(muffinId);
// ensure that we got back Jack and not another product
      assertTrue("Muffin should exist in the result", products.contains(retrieved));
// ensure that muffin’s details were properly retrieved (which
// indirectly tests that the details were properly saved).
      for (Product s : products) {
         if (s.equals(retrieved)) {
// ensure that muffin’s details were correctly retrieved
            assertEquals(retrieved.getId(), s.getId());
            assertEquals(retrieved.getName(), s.getName());
            assertEquals(retrieved.getCategory(), s.getCategory());
            assertEquals(retrieved.getDescription(), s.getDescription());
            assertEquals(muffin.getPrice(), s.getPrice(), 0);
            assertEquals(retrieved.getStock(), s.getStock());
         }
      }
// call getById using a non-existent ID
      Product nonExist = dao.getById(123456);
// ensure that the result is null
      assertNull("This one should not exist.", nonExist);
   }

   @Test
   public void testDaoGetMajors() {

      Collection<String> categories = dao.getCategory();

      assertTrue("Snack should exist in the result", categories.contains(muffin.getCategory()));
      assertTrue("SoftDrink should exist in the result", categories.contains(juice.getCategory()));
   }

   @Test
   public void testEdit() {

      muffin.setName("Cupcake");
      dao.save(muffin);
      Product retrieved = dao.getById(muffinId);

      assertEquals("Retrieved product should be the same as the saved one",
              "Cupcake", retrieved.getName());

   }
  
}
