
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mohamed amine tebbin
 */
public class test {
    public static int occuchar(String str , char car){
          int nb=0;
for(int i = 0 ; i<str.length();i++){
    if(str.charAt(i)==car){
        nb=nb+1;
    }
} 
return  nb;
    }
    public static String randomString(){
   final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
 final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  final String NUMBERS = "0123456789";
  final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\"<>,.?/~`";
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    sb.append(UPPERCASE_LETTERS.charAt(random.nextInt(UPPERCASE_LETTERS.length())));
    sb.append(LOWERCASE_LETTERS.charAt(random.nextInt(LOWERCASE_LETTERS.length())));
    sb.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
    sb.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
    sb.append(LOWERCASE_LETTERS.charAt(random.nextInt(LOWERCASE_LETTERS.length())));
    sb.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
    sb.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
    sb.append(UPPERCASE_LETTERS.charAt(random.nextInt(UPPERCASE_LETTERS.length())));
    return sb.toString();
    }
    public static ArrayList<String>  resetPassword(String email , String name ) throws Exception{
        PreparedStatement ps;
        Connection con = myconnection.getConnection();
        ArrayList<String> list = new ArrayList<String>();
        String newpass = test.randomString();
        
            ps=con.prepareStatement("update "+name+" set pwd= ? where login= ?");
            
            ps.setString(1, newpass);
            ps.setString(2, email);
            int rs = ps.executeUpdate();
            System.out.println(rs);
            list.add(newpass);
            list.add(Integer.toString(rs));
            return list;
            
       
        
    
    }
    public static  void logout(JFrame frame){
     login in  = new login();
     in.setVisible(true);
          in.pack();
          in.setLocationRelativeTo(null);
          frame.dispose();
    
    }
    public static void main(String[] args) {
        System.out.println(test.randomString());
    }
}
