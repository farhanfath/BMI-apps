/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bmiproject;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
/**
 *
 * @author farha
 */
public class model_bmi {
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost:3306/db_bmi";
    String user = "root";
    String password = "";
    
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    
    boolean respons;
    
    public model_bmi(){
        try {
            Class.forName(jdbcDriver);
            System.out.println("driver load..");
        } catch (ClassNotFoundException ex) {
            System.out.println("driver tidak ditemukan");
            Logger.getLogger(model_bmi.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("berhasil terkoneksi dengan mysql");
        } catch (SQLException ex) {
            System.out.println("gagal terkoneksi, periksa config mysql!!");
            Logger.getLogger(model_bmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //insert data ke database
    public boolean insertbmi(String npm, String nama, double tinggi, double berat,double bmi, String status){
        String query = "insert into record (npm, nama, tinggi, berat, bmi, status) values (?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, npm);
            ps.setString(2, nama);
            ps.setString(3, Double.toString(tinggi));
            ps.setString(4, Double.toString(berat));
            ps.setString(5, Double.toString(bmi));
            ps.setString(6, status);
            ps.executeUpdate();
            respons = true;
            System.out.println("Sukses insert..");
        } catch (SQLException ex) {
            respons = false;
            System.out.println("Gagal insert..");
            Logger.getLogger(model_bmi.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return respons;
    }
    
    public ResultSet getAllbmi(){
        String query = "select * from record";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(model_bmi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    //update data dari table + database
    public boolean updatebmi(String npm, String nama, double tinggi, double berat,double bmi, String status){
        String query = "update record set nama = ?, tinggi = ?, berat = ?, bmi = ?, status = ? where npm = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nama);
            ps.setString(2, Double.toString(tinggi));
            ps.setString(3, Double.toString(berat));
            ps.setString(4, Double.toString(bmi));
            ps.setString(5, status);
            ps.setString(6, npm);
            ps.executeUpdate();
            respons = true;
            System.out.println("Sukses Update..");
        } catch (SQLException ex) {
            respons = false;
            System.out.println("Gagal Update..");
            Logger.getLogger(model_bmi.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return respons;
    }
    
    //hapus data dari table + database
    public void hapusBmi(String npm){
        String query = "delete from record where npm = ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, npm);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(model_bmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
