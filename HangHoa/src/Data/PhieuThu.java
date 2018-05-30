/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Date;

/**
 *
 * @author cuhoa_000
 */
public class PhieuThu {
    private String MaPT;
    private Date NgayThu;
    private float SoTienThu;
    private String MSKH;

    /**
     * @return the MaPT
     */
    public String getMaPT() {
        return MaPT;
    }

    /**
     * @param MaPT the MaPT to set
     */
    public void setMaPT(String MaPT) {
        this.MaPT = MaPT;
    }

    /**
     * @return the NgayThu
     */
    public Date getNgayThu() {
        return NgayThu;
    }

    /**
     * @param NgayThu the NgayThu to set
     */
    public void setNgayThu(Date NgayThu) {
        this.NgayThu = NgayThu;
    }

    /**
     * @return the SoTienThu
     */
    public float getSoTienThu() {
        return SoTienThu;
    }

    /**
     * @param SoTienThu the SoTienThu to set
     */
    public void setSoTienThu(float SoTienThu) {
        this.SoTienThu = SoTienThu;
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

}
