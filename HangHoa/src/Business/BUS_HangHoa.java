/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
import Data.HangHoa;
import Data.classData;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author HaiTruong
 */
public class BUS_HangHoa {
    public static ArrayList<HangHoa> getInfoHangHoa() {
        ArrayList<HangHoa> arr = new ArrayList<HangHoa>();
        classData my = new classData();
        String quenry = "Select * From HangHoa";
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(quenry);
            while (rs.next()) {
                HangHoa HangHoa = new HangHoa();

                HangHoa.setMSHH(rs.getString("MSHH"));
                HangHoa.setTenHang(rs.getString("TenHang"));
                HangHoa.setDonViTinh(rs.getString("DonViTinh"));
                 arr.add(HangHoa);

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
         String sql = "select distinct MSHH from HangHoa";
         
         ArrayList<String> a = new ArrayList<String>(); 
         ResultSet rs = my.excuteQuery(sql); 
         try{
                while(rs.next()){
                    a.add(rs.getString("MSHH"));
                } 
        }catch(SQLException e){System.out.println("Not Success!!!");};
        my.close();
        return a;
    }
    
    public static ArrayList<HangHoa> getInfoHangHoaByID(String MSHH) {
        ArrayList<HangHoa> result = new ArrayList<>();
        String query = "select * from HangHoa where MSHH = '"+MSHH+"'";

        classData my = new classData();
        try {
            my.openConnect();
            ResultSet rs = my.excuteQuery(query);
            while (rs.next()) {
                HangHoa HangHoa = new HangHoa();
                HangHoa.setMSHH(rs.getString("MSHH"));
                HangHoa.setTenHang(rs.getString("TenHang"));
                HangHoa.setDonViTinh(rs.getString("DonViTinh"));
                result.add(HangHoa);


            }

            my.close();
        } catch (SQLException ex) {
            my.displayError(ex);
        }

        return result;
    }
    
 
    
    public static int ThemHangHoa(String TenHang,  String DonViTinh) {
        String sql = "Insert into HangHoa Values('','" + TenHang + "', '" + DonViTinh + "')";
        classData my = new classData();
        my.openConnect();
        int kq = my.executeUpdate(sql);
        my.close();
        return kq;
    }
}
