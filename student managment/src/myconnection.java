
import  java.sql.Connection;
import java.sql.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mohamed amine tebbin
 */

public class myconnection {

  
    
    public static  Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
            System.out.println("connection established");
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return con;
    }
    public static void main(String[] args) {
       myconnection.getConnection();
    }
}
