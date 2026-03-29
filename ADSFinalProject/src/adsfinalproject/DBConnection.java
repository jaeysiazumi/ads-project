/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adsfinalproject;

/**
 *
 * @author Lorraine
 */
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    //sana gumana naman na oh
    public static Connection getConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adsfinalproject",
                "root",
                ""
            );
            System.out.println("Connected!");
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
}
