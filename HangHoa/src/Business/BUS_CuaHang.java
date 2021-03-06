/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
import Data.CuaHang;
import Data.classData;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author HaiTruong
 */
public class BUS_CuaHang {
     public static ArrayList<CuaHang> getInfoCuaHang() {
        ArrayList<CuaHang> arr = new ArrayList<CuaHang>();
        classData my = new classData();
        String quenry = "Select * From CuaHang";
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(quenry);
            while (rs.next()) {
                CuaHang CuaHang = new CuaHang();

                CuaHang.setMSCH(rs.getString("MSCH"));
                CuaHang.setTenCH(rs.getString("TenCH"));
                CuaHang.setDiaChi(rs.getString("DiaChi"));
                arr.add(CuaHang);

            }

        } catch (SQLException ex) {
            my.displayError(ex);
        }
        my.close();
        return arr;
    }

    public static  ArrayList<String> loadInfo(){
        classData my = new classData();
        my.openConnect();
         String sql = "select distinct MSCH from CuaHang";
         
         ArrayList<String> a = new ArrayList<String>(); 
         ResultSet rs = my.excuteQuery(sql); 
         try{
                while(rs.next()){
                    a.add(rs.getString("MSCH"));
                } 
        }catch(SQLException e){System.out.println("Not Success!!!");};
        my.close();
        return a;
    }
    
    public static ArrayList<CuaHang> getInfoCuaHangByID(String MSCH) {
        ArrayList<CuaHang> result = new ArrayList<>();
        String query = "select * from CuaHang where MSCH = '"+MSCH+"'";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                CuaHang CuaHang = new CuaHang();

                CuaHang.setMSCH(rs.getString("MSCH"));
                CuaHang.setTenCH(rs.getString("TenCH"));
                CuaHang.setDiaChi(rs.getString("DiaChi"));
                result.add(CuaHang);



            }

            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
 
    
    public static int ThemCuaHang( String TenCH,  String DiaChi) {
        String sql = "Insert into CuaHang Values('','" + TenCH + "', '" + DiaChi + "')";
        classData my = new classData();
        my.openConnect();
        int kq = my.executeUpdate(sql);
        my.close();
        return kq;
    }

}
