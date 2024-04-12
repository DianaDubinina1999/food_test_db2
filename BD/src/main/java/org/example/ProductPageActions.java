package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ProductPageActions {

    public ProductPageActions () throws SQLException {
    }

    static boolean isFoundAll (Statement statement) throws SQLException {
        String query = "SELECT * FROM FOOD WHERE FOOD_ID BETWEEN 5 AND 8";
        ResultSet resultSet = statement.executeQuery (query);

        boolean cucumberFound = false;
        boolean artichokeFound = false;
        boolean plumFound = false;
        boolean mangoFound = false;

        while (resultSet.next ()) {
            int id = resultSet.getInt ("FOOD_ID");
            String name = resultSet.getString ("FOOD_NAME");

            if (id == 5 && name.equals ("Огурец")) {
                cucumberFound = true;
            } else if (id == 6 && name.equals ("Артишок")) {
                artichokeFound = true;
            } else if (id == 7 && name.equals ("Слива")) {
                plumFound = true;
            } else if (id == 8 && name.equals ("Манго")) {
                mangoFound = true;
            }
        }

        return cucumberFound && artichokeFound && plumFound && mangoFound;
    }

    static boolean isTableBase (Statement statement) throws SQLException {
        String query = "SELECT * FROM FOOD WHERE FOOD_ID >= 5";
        ResultSet resultSet = statement.executeQuery (query);
        return !resultSet.next (); // Вернуть true, если результат пустой
    }

    void DeleteNewProducts (Statement statement) throws SQLException {
        String delete = "DELETE from FOOD where FOOD_ID >= 5";
        statement.executeUpdate (delete);
    }

    void AddNewProducts (Statement statement) throws SQLException {
        String insert = " insert into FOOD(FOOD_ID,FOOD_NAME,FOOD_TYPE,FOOD_EXOTIC) values " +
         "(5,'Огурец','VEGETABLE',0), (6,'Артишок','VEGETABLE',1)," +
                "(7,'Слива','FRUIT',0),(8,'Манго','FRUIT',1)";
        statement.executeUpdate (insert);
    }

    static boolean isDeleteAll (Statement statement) throws SQLException {
        String query = "SELECT * FROM FOOD WHERE FOOD_ID >= 5";
        ResultSet resultSet = statement.executeQuery (query);
        return !resultSet.next (); // Вернуть true, если результат пустой
    }

}


