
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
public class responsable {
    public int co = 0;
    public int coo=0;
    public responsable(){
    
    }
    public static void  getMat(JComboBox ex){
         Connection con = myconnection.getConnection();
        PreparedStatement ps;
        try { 
            ps = con.prepareStatement(" select libelle from matière");           
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
    public static  void searchabsence(int idet , int iden , int nums , String mat , String date ){
    Connection con = myconnection.getConnection();
        PreparedStatement ps;
        PreparedStatement rech;
        int mati=0 ;
        try{
        rech = con.prepareStatement("select id_matière from matière where matière.libelle= ? ");
        rech.setString(1, mat);
        ResultSet matres = rech.executeQuery();
        while(matres.next()){
            mati=matres.getInt(1);
        }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        try{
            ps = con.prepareStatement("delete from absence where id_etudiant= "+Integer.toString(idet)+" and id_enseignant="+ Integer.toString(iden)+" and numseance = "+Integer.toString(nums)+" and id_matière = "+Integer.toString(mati)+" and date_ab = ?");
            ps.setString(1, date);
            System.out.println(ps.executeUpdate());
            System.out.println(nums);
        }catch(Exception e){
            System.out.println(e.getMessage());
            }
    
        
    
    }
    public static void search(JTable table,String  id_classe , String filière ,String libelle, String niv , String date , String  nums, String  idet , String  iden ){
        Connection con = myconnection.getConnection();
        PreparedStatement ps;
        PreparedStatement rech;
        int fil=0 ;
        try{
        rech = con.prepareStatement("select id_classe from classe where filière= ? ");
        rech.setString(1, filière);
        ResultSet matres = rech.executeQuery();
        while(matres.next()){
            fil=matres.getInt(1);
        }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        
        String sql = "select etudiant.id_etudiant , concat(etudiant.nom,' ',etudiant.prenom) as nom_et_prenom , matière.libelle , concat(classe.libelle , ' ', classe.niveau ) as class , classe.filière , enseignant.id_enseignant , concat(enseignant.nom,' ',enseignant.prenom) as nom_prenom_enseignant,absence.date_ab from absence , enseignant, etudiant , classe , matière where absence.id_etudiant= etudiant.id_etudiant and etudiant.id_classe=classe.id_classe and absence.id_enseignant=enseignant.id_enseignant and absence.id_matière= matière.id_matière ";
        if(id_classe.equals("")==false){
            sql=sql+" and etudiant.id_classe ="+id_classe+"";
        }
        if(filière.equals("none")==false){
            sql=sql+" and etudiant.id_classe= "+fil+"";
        }
        if(libelle.equals("none")==false){
            sql=sql+" and matière.libelle= '"+libelle+"' ";
            
                
        }
        if(niv.equals("")==false){
            sql=sql+" and classe.niveau= '"+niv+"' ";
            
        }
        if(date.equals("")==false){
            sql=sql+" and absence.date_ab = '"+date+"' ";
        
        }
        if(nums.equals("")==false){
            sql=sql+" and absence.numseance=  "+nums+" ";
            
        }
        if(idet.equals("")==false){
            sql=sql+" and absence.id_etudiant = "+idet+" ";
        
        }
        if(iden.equals("")==false){
            sql=sql+" and absence.id_enseignant= "+iden+"";
        }
       try {
                 ps=con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery();
                 Object[] row ;
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while(rs.next()){
              row = new Object[8];
              row[0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              row[3] = rs.getString(4);
              row[4] = rs.getString(5);
              row[5] = rs.getInt(6);
              row[6] = rs.getString(7);
              row[7] = rs.getString(8);
                      
              model.addRow(row);
                System.out.println(sql);
            }
             } catch (Exception ex) {
                 System.err.println(ex.getMessage());
             }finally{
           System.out.println(sql);      
           try {
                     con.close();
                 } catch (SQLException ex) {
                     System.out.println(ex.getMessage());
                 }
                 
             }
        
        
    
    
    }
    public static void getlibmat(JComboBox ex){
    Connection con = myconnection.getConnection();
        PreparedStatement ps;
         try { 
            ps = con.prepareStatement(" select libelle from matière ");
            
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
    public static void getfil(JComboBox ex){
    Connection con = myconnection.getConnection();
        PreparedStatement ps;
         try { 
            ps = con.prepareStatement(" select Distinct filière from classe ");
            
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
    public void getinfos(String ab , String abs   ){
        Connection con = myconnection.getConnection();
        PreparedStatement ps;
        PreparedStatement hey;
        String sql1 = "";
        String sql2="";
        if(ab.equals("none")==false && abs.equals("none")){
        sql1="select count(*) as nb from classe , etudiant where classe.id_classe = etudiant.id_classe and classe.filière = '"+ab+"'";
        sql2="select count(*) as ronaldo from (select  distinct absence.id_etudiant  from classe , etudiant , absence where classe.id_classe = etudiant.id_classe and absence.id_etudiant=etudiant.id_etudiant and classe.filière = '"+ab+"') as messi;";
        }
        if(ab.equals("none") && abs.equals("none")==false){
        sql1="select count(*) as nb from classe , etudiant where classe.id_classe = etudiant.id_classe and classe.niveau= '"+abs+"'";
        sql2="select count(*) as ronaldo from (select  distinct absence.id_etudiant  from classe , etudiant , absence where classe.id_classe = etudiant.id_classe and absence.id_etudiant=etudiant.id_etudiant and classe.niveau = '"+abs+"') as messi;";
        }
        if(ab.equals("none")==false && abs.equals("none")==false){
        sql1="select count(*) as nb from classe , etudiant where classe.id_classe = etudiant.id_classe and classe.niveau= '"+abs+"' and classe.filière= '"+ab+"'";
        sql2="select count(*) as ronaldo from (select  distinct absence.id_etudiant  from classe , etudiant , absence where classe.id_classe = etudiant.id_classe and absence.id_etudiant=etudiant.id_etudiant and classe.niveau = '"+abs+"' and classe.filière= '"+ab+"') as messi;";
        }
        if(ab.equals("none") && abs.equals("none")){
            try {
                throw new Exception("invalid input");
            } catch (Exception ex) {
               System.out.println(ex.getMessage());
            }
        }
        try { 
            ps = con.prepareStatement(sql1);
            hey = con.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
             ResultSet gtr = hey.executeQuery();
            if(rs.next()){
              co= rs.getInt(1);
                
}
             if(gtr.next()){
               coo =gtr.getInt(1);
                
}
                System.out.println(co);
                System.out.println(coo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(sql1);
            System.out.println(sql2);
        }finally{
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
    
    }
     public static void getnv(JComboBox ex){
    Connection con = myconnection.getConnection();
        PreparedStatement ps;
         try { 
            ps = con.prepareStatement(" select niveau from classe ");
            
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
}

