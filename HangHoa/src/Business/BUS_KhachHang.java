/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
import Data.KhachHang;
import Data.classData;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author HaiTruong
 */
public class BUS_KhachHang {
    public static ArrayList<KhachHang> getInfoKhachHang() {
        ArrayList<KhachHang> arr = new ArrayList<KhachHang>();
        classData my = new classData();
        String quenry = "Select * From KhachHang";
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(quenry);
            while (rs.next()) {
                KhachHang KhachHang = new KhachHang();

                KhachHang.setMSKH(rs.getString("MSKH"));
                KhachHang.setHoTenKhach(rs.getString("HoTenKhach"));
                KhachHang.setDiaChiKhach(rs.getString("DiaChiKhach"));
                KhachHang.setMaSoThue(rs.getString("MaSoThue"));
                arr.add(KhachHang);

            }

        } catch (SQLException ex) {
            my.displayError(ex);
        }
        my.close();
        return arr;
    }
    public static ArrayList<KhachHang>getLastIDKhachHang(){
        ArrayList<KhachHang> result = new ArrayList<>();
        String query = "select TOP 1 MSKH FROM KhachHang order by MSKH  desc";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                KhachHang KhachHang = new KhachHang();

                KhachHang.setMSKH(rs.getString("MSKH"));
                result.add(KhachHang);
            }
            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
    
    public static ArrayList<KhachHang> getInfoKhachHangByID(String MSKH) {
        ArrayList<KhachHang> result = new ArrayList<>();
        String query = "select * from KhachHang where MSKH = '"+MSKH+"'";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                KhachHang KhachHang = new KhachHang();

                KhachHang.setMSKH(rs.getString("MSKH"));
                KhachHang.setHoTenKhach(rs.getString("HoTenKhach"));
                KhachHang.setDiaChiKhach(rs.getString("DiaChiKhach"));
                KhachHang.setMaSoThue(rs.getString("MaSoThue"));
                result.add(KhachHang);


            }

            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
 
    
    public static int ThemKhachHang(String HoTenKhach,  String DiaChiKhach, String MaSoThue) {
        String sql = "Insert into KhachHang Values('','" + HoTenKhach + "', '" + DiaChiKhach + "', '"+ MaSoThue + "')";
        classData my = new classData();
        my.openConnect();
        int kq = my.executeUpdate(sql);
        my.close();
        return kq;
    }


}
