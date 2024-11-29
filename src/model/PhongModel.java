package model;

public class PhongModel extends QLKSModel{
    private String  loaiPhong, tinhTrang;
    private double giaPhong;

    public PhongModel() {}

    public PhongModel(String maPhong, String loaiPhong, double giaPhong, String tinhTrang) {
        super(maPhong);
        this.loaiPhong = loaiPhong;
        this.giaPhong = giaPhong;
        this.tinhTrang = tinhTrang;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public double getGiaPhong() {return giaPhong; }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
