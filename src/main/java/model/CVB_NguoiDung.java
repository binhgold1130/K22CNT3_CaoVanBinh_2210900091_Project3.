package model;

public class CVB_NguoiDung {
    private int idNguoiDung;
    private String hoTen;
    private String email;
    private String sdt;
    private String diaChi;
    private String vaiTro;
    private String password; // Thêm thuộc tính password

    public CVB_NguoiDung() {}

    public CVB_NguoiDung(int idNguoiDung, String hoTen, String email, String sdt, String diaChi, String vaiTro, String password) {
        this.idNguoiDung = idNguoiDung;
        this.hoTen = hoTen;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
        this.password = password; // Thêm password vào constructor
    }

    // Getters and Setters
    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getPassword() { // Thêm getter cho password
        return password;
    }

    public void setPassword(String password) { // Thêm setter cho password
        this.password = password;
    }
}