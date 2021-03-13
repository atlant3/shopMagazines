package pl.ciechocinek.mb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.xml.DOMConfigurator;

public class  ConnectionUtils {
    private static String URL = "jdbc:mysql://localhost:3306/shopMagazines";
    private static String USER = "root";
    private static String PASSWORD = "Vfrc!996";
    public static Connection connect() throws ClassNotFoundException {
    	DOMConfigurator.configure("log4j.xml");
    	Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}