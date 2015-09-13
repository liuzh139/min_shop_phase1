/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProductDAO;
import domain.Product;
import gui.ProductDialog;
import java.util.Collection;
import java.util.TreeSet;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 *
 * @author liuzh139
 */
public class ProductDialogTest {

   private ProductDAO dao;
   private DialogFixture fest;

   public ProductDialogTest() {
   }

   @Before
   public void setUp() {
      // add some majors for testing with
      Collection<String> categories = new TreeSet<>();
      categories.add("Snack");
      categories.add("SoftDrink");
// create a mock for the DAO
      dao = mock(ProductDAO.class);
// stub the getMajors method to return the test majors
      when(dao.getCategory()).thenReturn(categories);
   }

   @After
   public void tearDown() {
      // clean up FEST so that it is ready for the next test
      fest.cleanUp();
   }

   @Test
   public void testEdit() {
// a student to edit
      Product candybar = new Product(33333, "CandyBar", "Strawberry", "Snack", 2.00, 10.00);
// create dialog passing in student and mocked DAO
      ProductDialog dialog = new ProductDialog(null, true, candybar, dao);
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
// slow down the robot a bit .. default is 30 millis
      fest.robot.settings().delayBetweenEvents(75);
// ensure that the UI componenents contains the Jack’s details
      fest.textBox("txtId").requireText("33333");
      fest.textBox("txtName").requireText("CandyBar");
      fest.comboBox("cmbCategory").requireSelection("Snack");
      fest.textBox("txtDescription").requireText("Strawberry");
      fest.textBox("txtPrice").requireText("2.00");
      fest.textBox("txtStock").requireText("10.00");

// edit the name and major
      fest.textBox("txtName").selectAll().enterText("Bar");
      fest.comboBox("cmbCategory").selectItem("SoftDrink");
// click the save button
      fest.button("btnSave").click();
// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
      ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
// ensure that the DAO save method was called, and capture the passed student
      verify(dao)
              .save(argument.capture());
// retrieve the passed student from the captor
      Product edited = argument.getValue();
// ensure that the changes were saved

      assertEquals(
              "Ensure the name changed", "Bar", edited.getName());
      assertEquals(
              "Ensure the Category changed", "SoftDrink", edited.getCategory());
      
   }

   @Test
   public void testSave() {
// create the dialog passing in the mocked DAO
      ProductDialog dialog = new ProductDialog(null, true, dao);
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
// slow down the FEST robot a bit .. default is 30 millis
      fest.robot.settings().delayBetweenEvents(50);
// TODO: enter some details into the UI components (use enterText and selectItem)
      fest.textBox("txtId").selectAll().enterText("123456");
      fest.textBox("txtName").selectAll().enterText("CandyBar");
      fest.comboBox("cmbCategory").selectItem("Snack");
      fest.textBox("txtDescription").selectAll().enterText("Strawberry");
      fest.textBox("txtPrice").selectAll().enterText("2.00");
      fest.textBox("txtStock").selectAll().enterText("10.00");
// TODO: click the save button
      fest.button("btnSave").click();
// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
      ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
// TODO: verify that the DAO save method was called, and capture the passed student
      verify(dao).save(argument.capture());
// TODO: retrieve the passed student from the captor
      Product retrieve = argument.getValue();
// TODO: ensure that the student’s ID, name, and major were properly saved
      assertEquals("Ensure the ID changed", new Integer(123456), retrieve.getId());
      assertEquals("Ensure the name changed", "CandyBar", retrieve.getName());
      assertEquals("Ensure the Category changed", "Snack",retrieve.getCategory());
      assertEquals("Ensure the Description changed", "Strawberry", retrieve.getDescription());
      assertEquals("Ensure the Price changed", "2.0", String.valueOf(retrieve.getPrice()));
      assertEquals("Ensure the Stock changed", "10.0", String.valueOf(retrieve.getStock()));
   }
}
