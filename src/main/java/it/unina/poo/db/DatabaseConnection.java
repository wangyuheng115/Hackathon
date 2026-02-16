package it.unina.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection = null;
    private static final String URL = "jdbc:postgresql://localhost:5433/hackathon";
    private static final String USER = "postgres";
    private static final String PASSWORD = "wangyuheng115";

    private DatabaseConnection() throws SQLException {
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            System.out.println("Error Connection DB: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() throws SQLException{
        if(instance == null || instance.connection.isClosed()){
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
