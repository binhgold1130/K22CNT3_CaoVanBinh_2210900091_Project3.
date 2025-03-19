package model;

public class CVB_ThanhToan {
    private int idThanhToan;
    private int idHoaDon;
    private double soTienTT;
    private String phuongThuc;

    public CVB_ThanhToan() {}

    public CVB_ThanhToan(int idThanhToan, int idHoaDon, double soTienTT, String phuongThuc) {
        this.idThanhToan = idThanhToan;
        this.idHoaDon = idHoaDon;
        this.soTienTT = soTienTT;
        this.phuongThuc = phuongThuc;
    }

    // Getters and Setters
    public int getIdThanhToan() { return idThanhToan; }
    public void setIdThanhToan(int idThanhToan) { this.idThanhToan = idThanhToan; }

    public int getIdHoaDon() { return idHoaDon; }
    public void setIdHoaDon(int idHoaDon) { this.idHoaDon = idHoaDon; }

    public double getSoTienTT() { return soTienTT; }
    public void setSoTienTT(double soTienTT) { this.soTienTT = soTienTT; }

    public String getPhuongThuc() { return phuongThuc; }
    public void setPhuongThuc(String phuongThuc) { this.phuongThuc = phuongThuc; }
}