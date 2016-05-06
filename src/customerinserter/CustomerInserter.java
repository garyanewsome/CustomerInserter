/*
 * Gary A. Newsome
 * CPT 213_WA Java Programming II
 * Westmoreland County Community College
 * Janet Powell
 */
package customerinserter;

import java.sql.*;
import java.util.Scanner;

/**
 * Chapter 23 Programming Challenge 1 Customer Inserter
 * @author garyanewsome
 */
public class CustomerInserter {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // Create a named constant for the URL.
    // NOTE: This value is specific for Java DB.
    final String DB_URL = "jdbc:derby:CoffeeDB";

    String key;
    String name;
    String address;
    String city;
    String state;
    String zip;

    try {
      // Create a connection to the database.
      Connection conn = DriverManager.getConnection(DB_URL);
      // Get a Statement object.
      Statement stmt = conn.createStatement();
      Scanner keyboard = new Scanner(System.in);
      println("Customer Inserter");
      print("Please enter the customer key: ");
      key = keyboard.nextLine();
      print("Please enter the customer name: ");
      name = keyboard.nextLine();
      print("Please enter customer address: ");
      address = keyboard.nextLine();
      print("Please enter customer city: ");
      city = keyboard.nextLine();
      print("Please enter customer state: ");
      state = keyboard.nextLine();
      print("Please enter customer zip: ");
      zip = keyboard.nextLine();
      
      // Add some rows to the new table.
      String sql = "INSERT INTO Customer VALUES"
              + "('" + key + "', '" + name + "', '" + address + "', '" + city + "','" + state + "', '" + zip + "')";

      stmt.executeUpdate(sql);

      println("\nPrint out of the current DB for confirmation and testing.\n");
      // Create a string with a SELECT statement.
      String sqlStatement = "SELECT * FROM Customer";

      // Send the statement to the DBMS.
      ResultSet result = stmt.executeQuery(sqlStatement);
      // Display the contents of the result set.
      // The result set will have three columns.
      while (result.next()) {
        println(result.getString("CustomerNumber")
                + result.getString("Name")
                + result.getString("Address")
                + result.getString("City")
                + result.getString("State")
                + result.getString("Zip"));
      }
      // Close the connection.
      conn.close();
    } catch (Exception ex) {
      println("ERROR: " + ex.getMessage());
    }
  }

  public static void println(String msg) {
    System.out.println(msg);
  }

  public static void print(String msg) {
    System.out.print(msg);
  }
}
