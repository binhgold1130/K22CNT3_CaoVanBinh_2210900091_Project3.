package model;

public class CVB_ChiSoDien {
    private int idChiSo;
    private int idCongTo;
    private int chiSoCu;
    private int chiSoMoi;
    private int dienTieuThu;

    public CVB_ChiSoDien() {
    }

    public CVB_ChiSoDien(int idChiSo, int idCongTo, int chiSoCu, int chiSoMoi, int dienTieuThu) {
        this.idChiSo = idChiSo;
        this.idCongTo = idCongTo;
        this.chiSoCu = chiSoCu;
        this.chiSoMoi = chiSoMoi;
        this.dienTieuThu = calculateDienTieuThu();
    }

    public int getIdChiSo() { return idChiSo; }
    public void setIdChiSo(int idChiSo) { this.idChiSo = idChiSo; }

    public int getIdCongTo() { return idCongTo; }
    public void setIdCongTo(int idCongTo) { this.idCongTo = idCongTo; }

    public int getChiSoCu() { return chiSoCu; }
    public void setChiSoCu(int chiSoCu) {
        this.chiSoCu = chiSoCu;
        this.dienTieuThu = calculateDienTieuThu();
    }

    public int getChiSoMoi() { return chiSoMoi; }
    public void setChiSoMoi(int chiSoMoi) {
        this.chiSoMoi = chiSoMoi;
        this.dienTieuThu = calculateDienTieuThu();
    }

    public int getDienTieuThu() { return dienTieuThu; }
    public void setDienTieuThu(int dienTieuThu) { this.dienTieuThu = dienTieuThu; }

    private int calculateDienTieuThu() {
        return Math.max(0, chiSoMoi - chiSoCu);
    }

    @Override
    public String toString() {
        return "CVB_ChiSoDien{" +
                "idChiSo=" + idChiSo +
                ", idCongTo=" + idCongTo +
                ", chiSoCu=" + chiSoCu +
                ", chiSoMoi=" + chiSoMoi +
                ", dienTieuThu=" + dienTieuThu +
                '}';
    }
}