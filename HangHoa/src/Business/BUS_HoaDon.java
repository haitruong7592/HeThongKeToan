/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
import Data.HoaDon;
import Data.classData;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author HaiTruong
 */
public class BUS_HoaDon {
        public static ArrayList<HoaDon> getInfoHoaDon() {
        ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
        classData my = new classData();
        String quenry = "Select * From HoaDon";
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(quenry);
            while (rs.next()) {
                HoaDon HoaDon = new HoaDon();

                HoaDon.setMaHD(rs.getString("MaHD"));
                HoaDon.setSoHD(rs.getString("SoHD"));
                HoaDon.setNgayBan(rs.getDate("NgayBan"));
                HoaDon.setLyDo(rs.getString("LyDo"));
                HoaDon.setThueSuat(rs.getDouble("ThueSuatBan"));
                HoaDon.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                HoaDon.setMSCH(rs.getString("MSCH"));
                HoaDon.setMSKH(rs.getString("MSKH"));
                HoaDon.setMaTK(rs.getString("MaTK"));
                HoaDon.setSoseri(rs.getString("Soseri"));
                HoaDon.setTongTien(rs.getDouble("TongTien"));          
                arr.add(HoaDon);

            }

        } catch (SQLException ex) {
            my.displayError(ex);
        }
        my.close();
        return arr;
    }

    public static ArrayList<HoaDon>getLastIDHoaDon(){
        ArrayList<HoaDon> result = new ArrayList<>();
        String query = "select TOP 1 MaHD FROM HoaDon order by MaHD  desc";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                HoaDon HoaDon = new HoaDon();

                HoaDon.setMaHD(rs.getString("MaHD"));
                result.add(HoaDon);
            }
            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
    public static ArrayList<HoaDon> getInfoHoaDonByID(String MaHD) {
        ArrayList<HoaDon> result = new ArrayList<>();
        String query = "select * from HoaDon where MaHD = '"+MaHD+"'";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                HoaDon HoaDon = new HoaDon();

                HoaDon.setMaHD(rs.getString("MaHD"));
                HoaDon.setSoHD(rs.getString("SoHD"));
                HoaDon.setNgayBan(rs.getDate("NgayBan"));
                HoaDon.setLyDo(rs.getString("LyDo"));
                HoaDon.setThueSuat(rs.getDouble("ThueSuatBan"));
                HoaDon.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                HoaDon.setMSCH(rs.getString("MSCH"));
                HoaDon.setMSKH(rs.getString("MSKH"));
                HoaDon.setMaTK(rs.getString("MaTK"));
                HoaDon.setSoseri(rs.getString("Soseri"));
                HoaDon.setTongTien(rs.getDouble("TongTien"));    
                result.add(HoaDon);


            }

            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
 
    
    public static int ThemHoaDon( String SoHD, Date NgayBan,  String LyDo, double ThueSuat,  Date NgayThanhToan, String MSCH, String MSKH, String MaTK, String Soseri, double TongTien) {
        String sql = "Insert into HoaDon Values('','" + SoHD + "', '"+NgayBan +"', '"+ LyDo +"', "+ThueSuat + ", '" + NgayThanhToan + "', '"+ MSCH + "', '" + MSKH + "', '"+MaTK +"', '"+ Soseri + "', "+ TongTien + ")";
        classData my = new classData();
        my.openConnect();
        int kq = my.executeUpdate(sql);
        my.close();
        return kq;
    }
}
