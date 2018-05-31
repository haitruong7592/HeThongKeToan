/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
import Data.Ban;
import Data.classData;
import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author HaiTruong
 */
public class BUS_Ban {
     public static ArrayList<Ban> getInfoBan() {
        ArrayList<Ban> arr = new ArrayList<Ban>();
        classData my = new classData();
        String quenry = "Select * From Ban";
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(quenry);
            while (rs.next()) {
                Ban Ban = new Ban();

                Ban.setMaHD(rs.getString("MaHD"));
                Ban.setMSHH(rs.getString("MSHH"));
                Ban.setSoLuong(rs.getInt("SoLuong"));
                Ban.setDonGiaBan(rs.getDouble("DonGiaBan"));
                arr.add(Ban);

            }

        } catch (SQLException ex) {
            my.displayError(ex);
        }
        my.close();
        return arr;
    }

    
    
    public static ArrayList<Ban> getInfoBanByID(String MaHD) {
        ArrayList<Ban> result = new ArrayList<>();
        String query = "select * from Ban where MaHD = '"+MaHD+"'";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                Ban Ban = new Ban();

                Ban.setMaHD(rs.getString("MaHD"));
                Ban.setMSHH(rs.getString("MSHH"));
                Ban.setSoLuong(rs.getInt("SoLuong"));
                Ban.setDonGiaBan(rs.getDouble("DonGiaBan"));
                result.add(Ban);


            }

            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
 
    
    public static int ThemBan(String MaHD, String MSHH,  int SoLuong, Double DonGia) {
        String sql = "Insert into Ban Values('" + MaHD + "', '" + MSHH + "', " + SoLuong + ", "+ DonGia + ")";
        classData my = new classData();
        my.openConnect();
        int kq = my.executeUpdate(sql);
        my.close();
        return kq;
    }

}
