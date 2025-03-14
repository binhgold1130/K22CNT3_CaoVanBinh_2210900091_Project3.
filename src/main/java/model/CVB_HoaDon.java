package model;

public class CVB_HoaDon {
    private int idHoaDon;
    private int idNguoiDung;
    private int idChiSo;
    private double tongTien;
    private String trangThai;

    public CVB_HoaDon() {}

    public CVB_HoaDon(int idHoaDon, int idNguoiDung, int idChiSo, double tongTien, String trangThai) {
        this.idHoaDon = idHoaDon;
        this.idNguoiDung = idNguoiDung;
        this.idChiSo = idChiSo;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdHoaDon() { return idHoaDon; }
    public void setIdHoaDon(int idHoaDon) { this.idHoaDon = idHoaDon; }

    public int getIdNguoiDung() { return idNguoiDung; }
    public void setIdNguoiDung(int idNguoiDung) { this.idNguoiDung = idNguoiDung; }

    public int getIdChiSo() { return idChiSo; }
    public void setIdChiSo(int idChiSo) { this.idChiSo = idChiSo; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}