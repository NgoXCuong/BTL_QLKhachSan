package model;

public class KhachHangModel extends QLKSModel {
    private String  tenKhachHang, cmnd, soDienThoai;

    public KhachHangModel() {}

    public KhachHangModel(String makhachHang, String tenKhachHang, String cmnd, String soDienThoai) {
        super(makhachHang);
        this.tenKhachHang = tenKhachHang;
        this.cmnd = cmnd;
        this.soDienThoai = soDienThoai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
