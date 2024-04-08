package org.ibs.jdbs;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBSTest {

        @Test
        public void ConnectionTest() throws SQLException {
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb",
            "user", "pass");

            String insert =
                    "insert into FOOD (FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) values (5, 'Апельсин', 'FRUIT', 0)";
            String query = "SELECT FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC FROM FOOD";

            Statement statement = connection.createStatement();
            statement.executeUpdate(insert);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("FOOD_ID");
                String name = resultSet.getString("FOOD_NAME");
                String type = resultSet.getNString("FOOD_TYPE");
                int exotic = resultSet.getInt("FOOD_EXOTIC");
                System.out.println(id + " " + name + " " + type + " " + exotic);
            }
            connection.close();
        }

        @Test
        public void GetLastObject() throws SQLException {
                Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb",
                        "user", "pass");

            String query = "SELECT FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC FROM FOOD";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            String deleteQuery = "DELETE FROM FOOD WHERE FOOD_ID = 5";
            statement.executeUpdate(deleteQuery);
            System.out.println("Удаление объекта с id = 5");
            connection.close();
        }


}
