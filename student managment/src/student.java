import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mohamed amine tebbin
 */
public class student {
       
    public student(){
    
    }
    public static void  getStdByDate(JTable table, String dat){
    Connection con = myconnection.getConnection();
        PreparedStatement ps;
        try { 
            ps = con.prepareStatement("select absence.numseance , matière.libelle, concat(enseignant.prenom,' ',enseignant.nom) as nom_et_prenom , absence.date_ab from absence, etudiant,enseignant,matière where absence.id_etudiant=etudiant.id_etudiant and absence.id_enseignant=enseignant.id_enseignant and matière.id_matière=absence.id_matière and absence.date_ab = ? ");
            ps.setString(1,dat);
            ResultSet rs = ps.executeQuery();
            Object[] row ;
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while(rs.next()){
              row = new Object[4];
              row[0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              row[3] = rs.getString(4);
              model.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    
    
    
    
    }
    public static void getStdById(JTable table , int id){
        Connection con = myconnection.getConnection();
        PreparedStatement ps;
        try { 
            ps = con.prepareStatement("select absence.numseance , matière.libelle, concat(enseignant.prenom,' ',enseignant.nom) as nom_et_prenom , absence.date_ab from absence, etudiant,enseignant,matière where absence.id_etudiant=etudiant.id_etudiant and absence.id_enseignant=enseignant.id_enseignant and matière.id_matière=absence.id_matière and etudiant.id_etudiant= ? ");
            ps.setString(1,Integer.toString(id));
            ResultSet rs = ps.executeQuery();
            Object[] row ;
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while(rs.next()){
              row = new Object[4];
              row[0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              row[3] = rs.getString(4);
              model.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    
    }
    public static  String getLogin(int id ){
        String res = "";
        Connection con = myconnection.getConnection();
        PreparedStatement ps;
         try { 
            ps = con.prepareStatement("select distinct etudiant.login from etudiant , absence where etudiant.id_etudiant=absence.id_etudiant and absence.id_etudiant= "+Integer.toString(id)+" ");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
             res=rs.getString(1);
            }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    return res;
    }
}
