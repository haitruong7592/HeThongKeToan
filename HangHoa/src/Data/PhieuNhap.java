/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Date;

/**
 *
 * @author HaiTruong
 */
public class PhieuNhap {
    private String MaPN;
    private Date NgayNhap;
    private float ThueSuatMua;
    private String MSKH;
    private String MSCH;

    /**
     * @return the MaPN
     */
    public String getMaPN() {
        return MaPN;
    }

    /**
     * @param MaPN the MaPN to set
     */
    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    /**
     * @return the NgayNhap
     */
    public Date getNgayNhap() {
        return NgayNhap;
    }

    /**
     * @param NgayNhap the NgayNhap to set
     */
    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    /**
     * @return the ThueSuatMua
     */
    public float getThueSuatMua() {
        return ThueSuatMua;
    }

    /**
     * @param ThueSuatMua the ThueSuatMua to set
     */
    public void setThueSuatMua(float ThueSuatMua) {
        this.ThueSuatMua = ThueSuatMua;
    }

    /**
     * @return the MSKH
     */
    public String getMSKH() {
        return MSKH;
    }

    /**
     * @param MSKH the MSKH to set
     */
    public void setMSKH(String MSKH) {
        this.MSKH = MSKH;
    }

    /**
     * @return the MSCH
     */
    public String getMSCH() {
        return MSCH;
    }

    /**
     * @param MSCH the MSCH to set
     */
    public void setMSCH(String MSCH) {
        this.MSCH = MSCH;
    }
    
}
