package model;

public class CVB_CongToDien {
    private int idCongTo;
    private int idNguoiDung;
    private String soCongTo;
    private String trangThai;

    public CVB_CongToDien() {}

    public CVB_CongToDien(int idCongTo, int idNguoiDung, String soCongTo, String trangThai) {
        this.idCongTo = idCongTo;
        this.idNguoiDung = idNguoiDung;
        this.soCongTo = soCongTo;
        this.trangThai = trangThai;
    }

    public int getIdCongTo() { return idCongTo; }
    public void setIdCongTo(int idCongTo) { this.idCongTo = idCongTo; }

    public int getIdNguoiDung() { return idNguoiDung; }
    public void setIdNguoiDung(int idNguoiDung) { this.idNguoiDung = idNguoiDung; }

    public String getSoCongTo() { return soCongTo; }
    public void setSoCongTo(String soCongTo) { this.soCongTo = soCongTo; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}