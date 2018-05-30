/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author cuhoa_000
 */
import Data.HoaDon;
import Data.classData;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class NhatKyBanHangBUS {
   private conDB con;
    public void getHoaDonMuaHang(JTable tb) throws Exception
    {
        con=new conDB();
        ArrayList<HoaDon> dshd =new ArrayList<>();
        String query="select * from HoaDon";
        ResultSet rs=con.executeQuery(query);
        HoaDon hd;
        while(rs.next()){
            hd=new HoaDon();
            hd.setMaHD(rs.getString(1));
            hd.setThueSuat(rs.getFloat(2));
            hd.setNgayThanhToan(rs.getDate(3));
            hd.setMSCH(rs.getString(4));
            hd.setMSKH(rs.getString(5));
            hd.setSoseri(rs.getString(6));
            //hd.setType(rs.getInt(7));
            //hd.setNgayTao(rs.getDate(8));
            dshd.add(hd);
        }
        con.close();
        DefaultTableModel dtm=(DefaultTableModel) tb.getModel();
        dtm.setRowCount(0);
        for(int i=0;i<dshd.size();i++){
            dtm.addRow(new Object[]{
                i+1,
                dshd.get(i).getMaHD(),
                dshd.get(i).getThueSuat(),
                dshd.get(i).getNgayThanhToan(),
                dshd.get(i).getMSCH(),
                dshd.get(i).getMSKH(),
                dshd.get(i).getSoseri(),
               // dshd.get(i).getType(),
               // dshd.get(i).getNgayTao()
            });
        }
        
    }
    
}
