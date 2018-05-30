/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.TaiKhoan;
import Data.classData;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author HaiTruong
 */
public class BUS_TaiKhoan {
     public static ArrayList<TaiKhoan> getInfoTaiKhoan() {
        ArrayList<TaiKhoan> arr = new ArrayList<TaiKhoan>();
        classData my = new classData();
        String quenry = "Select * From TaiKhoan";
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(quenry);
            while (rs.next()) {
                TaiKhoan TaiKhoan = new TaiKhoan();

                TaiKhoan.setMaTK(rs.getString("MaTK"));
                TaiKhoan.setTenTK(rs.getString("TenTK"));
                arr.add(TaiKhoan);

            }

        } catch (SQLException ex) {
            my.displayError(ex);
        }
        my.close();
        return arr;
    }
    public static ArrayList<TaiKhoan>getLastIDTaiKhoan(){
        ArrayList<TaiKhoan> result = new ArrayList<>();
        String query = "select TOP 1 MaTK FROM TaiKhoan order by MaTK  desc";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                TaiKhoan TaiKhoan = new TaiKhoan();

                TaiKhoan.setMaTK(rs.getString("MaTK"));
                result.add(TaiKhoan);
            }
            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
    public static  ArrayList<String> loadInfo(){
        classData my = new classData();
        my.openConnect();
         String sql = "select distinct MaTK from TaiKhoan";
         
         ArrayList<String> a = new ArrayList<String>(); 
         ResultSet rs = my.excuteQuery(sql); 
         try{
                while(rs.next()){
                    a.add(rs.getString("MaTK"));
                } 
        }catch(SQLException e){System.out.println("Not Success!!!");};
        my.close();
        return a;
    }
    
    public static ArrayList<TaiKhoan> getInfoTaiKhoanByID(String MaTK) {
        ArrayList<TaiKhoan> result = new ArrayList<>();
        String query = "select * from TaiKhoan where MaTK = '"+MaTK+"'";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                TaiKhoan TaiKhoan = new TaiKhoan();

                TaiKhoan.setMaTK(rs.getString("MaTK"));
                TaiKhoan.setTenTK(rs.getString("TenTK"));
                result.add(TaiKhoan);


            }

            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
 
    
    public static int ThemTaiKhoan(String MaTK,  String TenTK) {
        String sql = "Insert into TaiKhoan Values('" + MaTK + "', '" + TenTK + "')";
        classData my = new classData();
        my.openConnect();
        int kq = my.executeUpdate(sql);
        my.close();
        return kq;
    }
    
    public static int SuaTaiKhoan(String MaTK, String TenTK) {
        String sql = "Update TaiKhoan Set TenTK = '" + TenTK + "' Where MaTK = '" + MaTK + "'";
        classData my = new classData();
        my.openConnect();
        int kq = my.executeUpdate(sql);
        my.close();
        return kq;
    }

    public static int XoaTaiKhoan(String MaTaiKhoan) {
        String sql = "Delete  From TaiKhoan Where MaTK = '" + MaTaiKhoan + "'";
        classData my = new classData();
        my.openConnect();
        int kq = my.executeUpdate(sql);
        my.close();
        return kq;
    }
}
