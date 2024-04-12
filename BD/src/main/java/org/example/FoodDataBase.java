package org.example;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class FoodDataBase {
    public static void main (String[] args) throws SQLException {
        FoodDataBase bd = new FoodDataBase ();
        bd.Simple ();
    }
    @Test
    public void Simple () throws SQLException {
        try {

            Connection connection = DriverManager.getConnection
                    ("jdbc:h2:tcp://localhost:9092/mem:testdb",
                            "user", "pass");


            Statement statement = connection.createStatement ();
            ProductPageActions actions = new ProductPageActions();

            boolean tableBase = actions.isTableBase (statement);
            if (!tableBase) {
                throw new IllegalStateException ("Таблица не является изначальной.");
            }
            actions.isTableBase(statement);
            actions.AddNewProducts (statement);

            boolean foundAll = actions.isFoundAll (statement);
            if (!foundAll) {
                throw new IllegalStateException ("Не все товары были добавлены.");
            }

            actions.DeleteNewProducts (statement);
            boolean deleteAll = actions.isDeleteAll (statement);
            if (!deleteAll) {
                throw new IllegalStateException ("Не все товары были удалены.");
            }
            connection.close ();


        } catch (SQLException ex) {
            System.out.println ("SQL Exception: " + ex.getMessage ());

        }
    }
}

