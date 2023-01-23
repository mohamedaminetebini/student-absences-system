

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
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
public class enseignant {

    public enseignant() {
    }
    
    public static void  getMat(JComboBox ex , int id){
         Connection con = myconnection.getConnection();
        PreparedStatement ps;
        try { 
            ps = con.prepareStatement(" select matière.libelle from matière,correspondance where matière.id_matière=correspondance.id_matière and correspondance.id_enseignant= ? ");
            ps.setString(1,Integer.toString(id));
            ResultSet rs = ps.executeQuery();
            Object[] row ;
            while(rs.next()){
              row = new Object[1];
              row[0] = rs.getString(1);
              ex.addItem(row[0]);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
        
    
    }
    public static void getclasse(JComboBox ex , int id){
         Connection con = myconnection.getConnection();
        PreparedStatement ps;
        try { 
            ps = con.prepareStatement(" select DISTINCT concat(classe.libelle,' ',classe.niveau) as classe_nom from correspondance , classe where correspondance.id_classe= classe.id_classe and correspondance.id_enseignant = ? ");
            ps.setString(1,Integer.toString(id));
            ResultSet rs = ps.executeQuery();
            Object[] row ;
            while(rs.next()){
              row = new Object[1];
              row[0] = rs.getString(1);
              ex.addItem(row[0]);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
  
        }
}
    public static void search(JTable tab,String mat , String cla ,int id ){
         Connection con = myconnection.getConnection();
         PreparedStatement ps;
         if(cla.equals("none") && mat.equals("none")){
             try {
                 ps=con.prepareStatement("select absence.id_etudiant , matière.libelle , concat(etudiant.prenom,' ',etudiant.nom) as nom_prenom  , absence.numseance , concat(classe.libelle,' ',classe.niveau) as classe , classe.filière , absence.date_ab from etudiant , classe , matière , absence  where etudiant.id_etudiant = absence.id_etudiant and classe.id_classe = etudiant.id_classe and matière.id_matière = absence.id_matière and absence.id_enseignant = "+Integer.toString(id)+"");
                 ResultSet rs = ps.executeQuery();
                 Object[] row ;
            DefaultTableModel model = (DefaultTableModel) tab.getModel();
            while(rs.next()){
              row = new Object[7];
              row[0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              row[3] = rs.getInt(4);
              row[4] = rs.getString(5);
              row[5] = rs.getString(6);
              row[6] = rs.getString(7);
                      
              model.addRow(row);
            }
             } catch (SQLException ex) {
                 
             }finally{
                 try {
                     con.close();
                 } catch (SQLException ex) {
                     System.out.println(ex.getMessage());
                 }
                 
             }
         
         }
         else if (cla.equals("none")==true && mat.equals("none")==false)
         {
              try {
                 ps=con.prepareStatement("select absence.id_etudiant , matière.libelle , concat(etudiant.prenom,' ',etudiant.nom) as nom_prenom  , absence.numseance , concat(classe.libelle,' ',classe.niveau) as classe , classe.filière , absence.date_ab from etudiant , classe , matière , absence  where etudiant.id_etudiant = absence.id_etudiant and classe.id_classe = etudiant.id_classe and matière.id_matière = absence.id_matière and absence.id_enseignant = "+Integer.toString(id)+" and matière.libelle=? ");
                 ps.setString(1, mat);
                 ResultSet rs = ps.executeQuery();
                 Object[] row ;
            DefaultTableModel model = (DefaultTableModel) tab.getModel();
            while(rs.next()){
              row = new Object[7];
              row[0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              row[3] = rs.getInt(4);
              row[4] = rs.getString(5);
              row[5] = rs.getString(6);
              row[6] = rs.getString(7);
                      
              model.addRow(row);
            }
             } catch (SQLException ex) {
                 
             }finally{
                  try {
                      con.close();
                  } catch (SQLException ex) {
                      
                  }
              }
         }
         else if(cla.equals("none")==false && mat.equals("none")==true){
             try {
                 ps=con.prepareStatement("select absence.id_etudiant , matière.libelle , concat(etudiant.prenom,' ',etudiant.nom) as nom_prenom  , absence.numseance , concat(classe.libelle,' ',classe.niveau) as classe , classe.filière , absence.date_ab from etudiant , classe , matière , absence  where etudiant.id_etudiant = absence.id_etudiant and classe.id_classe = etudiant.id_classe and matière.id_matière = absence.id_matière and absence.id_enseignant = "+Integer.toString(id)+" and concat(classe.libelle,' ',classe.niveau) like ? ");
                 ps.setString(1, "%"+cla+"%");
                 ResultSet rs = ps.executeQuery();
                 Object[] row ;
            DefaultTableModel model = (DefaultTableModel) tab.getModel();
            while(rs.next()){
              row = new Object[7];
              row[0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              row[3] = rs.getInt(4);
              row[4] = rs.getString(5);
              row[5] = rs.getString(6);
              row[6] = rs.getString(7);
                      
              model.addRow(row);
            }
             } catch (SQLException ex) {
                 
             }finally{
                  try {
                      con.close();
                  } catch (SQLException ex) {
                      
                  }
              }
         
         
         }else{
             try {
                 ps=con.prepareStatement("select absence.id_etudiant , matière.libelle , concat(etudiant.prenom,' ',etudiant.nom) as nom_prenom  , absence.numseance , concat(classe.libelle,' ',classe.niveau) as classe , classe.filière , absence.date_ab from etudiant , classe , matière , absence  where etudiant.id_etudiant = absence.id_etudiant and classe.id_classe = etudiant.id_classe and matière.id_matière = absence.id_matière and absence.id_enseignant = "+Integer.toString(id)+" and concat(classe.libelle,' ',classe.niveau) like ? and matière.libelle= ? ");
                 ps.setString(1, "%"+cla+"%");
                 ps.setString(2, mat);
                 ResultSet rs = ps.executeQuery();
                 Object[] row ;
            DefaultTableModel model = (DefaultTableModel) tab.getModel();
            while(rs.next()){
              row = new Object[7];
              row[0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              row[3] = rs.getInt(4);
              row[4] = rs.getString(5);
              row[5] = rs.getString(6);
              row[6] = rs.getString(7);
                      
              model.addRow(row);
            }
             } catch (SQLException ex) {
                 
             }finally{
                  try {
                      con.close();
                  } catch (SQLException ex) {
                      
                  }
              }
         }
    
    }
    public static void ajouterabsence(int idet , int nums , String mat , String date,int id) throws Exception{
         Connection con = myconnection.getConnection();
        PreparedStatement ps;
        PreparedStatement rech;
        int mati=0 ;
        
        rech = con.prepareStatement("select id_matière from matière where matière.libelle= ? ");
        rech.setString(1, mat);
        ResultSet matres = rech.executeQuery();
        while(matres.next()){
            mati=matres.getInt(1);
        }
            
                
        
        
                    
            ps = con.prepareStatement("insert into absence values("+Integer.toString(idet)+","+Integer.toString(id)+","+Integer.toString(mati)+","+nums+",?) ");
            ps.setString(1, date);
            System.out.println(ps.executeUpdate());
            System.out.println(nums);
           
       
    
        
    
    }

}
