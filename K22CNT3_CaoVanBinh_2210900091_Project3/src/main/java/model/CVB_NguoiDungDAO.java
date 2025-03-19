package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CVB_NguoiDungDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Lấy người dùng theo email
    public CVB_NguoiDung getNguoiDungByEmail(String email) {
        String sql = "SELECT * FROM CVB_NguoiDung WHERE email = ?";
        List<CVB_NguoiDung> nguoiDungs = jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> new CVB_NguoiDung(
                rs.getInt("idNguoiDung"),
                rs.getString("hoTen"),
                rs.getString("email"),
                rs.getString("sdt"),
                rs.getString("diaChi"),
                rs.getString("vaiTro"),
                rs.getString("password")
        ));
        return nguoiDungs.isEmpty() ? null : nguoiDungs.get(0);
    }

    // Lấy danh sách tất cả người dùng
    public List<CVB_NguoiDung> getAllNguoiDung() {
        String sql = "SELECT * FROM CVB_NguoiDung";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new CVB_NguoiDung(
                rs.getInt("idNguoiDung"),
                rs.getString("hoTen"),
                rs.getString("email"),
                rs.getString("sdt"),
                rs.getString("diaChi"),
                rs.getString("vaiTro"),
                rs.getString("password")
        ));
    }

    // Admin: Thống kê số tiền thu được
    public double getTongTienThuDuoc() {
        String sql = "SELECT SUM(soTienTT) FROM CVB_ThanhToan";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    // Admin: Thống kê số tiền thu được theo tháng
    public double getTongTienThuDuocTheoThang() {
        return 0.0;
    }

    // Khách hàng: Xem thông tin công tơ của mình
    public List<CVB_CongToDien> getCongToDienByNguoiDungId(int nguoiDungId) {
        String sql = "SELECT * FROM CVB_CongToDien WHERE idNguoiDung = ?";
        return jdbcTemplate.query(sql, new Object[]{nguoiDungId}, (rs, rowNum) -> new CVB_CongToDien(
                rs.getInt("idCongTo"),
                rs.getInt("idNguoiDung"),
                rs.getString("soCongTo"),
                rs.getString("trangThai")
        ));
    }

    // Khách hàng: Xem số điện đã dùng và số tiền cần thanh toán
    public List<CVB_ChiSoDien> getChiSoDienByCongToId(int congToId) {
        String sql = "SELECT idChiSo, idCongTo, chiSoCu, chiSoMoi FROM CVB_ChiSoDien WHERE idCongTo = ?";
        return jdbcTemplate.query(sql, new Object[]{congToId}, (rs, rowNum) -> {
            CVB_ChiSoDien chiSoDien = new CVB_ChiSoDien(
                    rs.getInt("idChiSo"),
                    rs.getInt("idCongTo"),
                    rs.getInt("chiSoCu"),
                    rs.getInt("chiSoMoi"),
                    0
            );
            return chiSoDien;
        });
    }

    // Khách hàng: Xem thông tin hóa đơn và thanh toán
    public List<CVB_HoaDon> getHoaDonByNguoiDungId(int nguoiDungId) {
        String sql = "SELECT idHoaDon, idNguoiDung, idChiSo, tongTien, trangThai FROM CVB_HoaDon WHERE idNguoiDung = ?";
        return jdbcTemplate.query(sql, new Object[]{nguoiDungId}, (rs, rowNum) -> new CVB_HoaDon(
                rs.getInt("idHoaDon"),
                rs.getInt("idNguoiDung"),
                rs.getInt("idChiSo"),
                rs.getDouble("tongTien"),
                rs.getString("trangThai")
        ));
    }

    // Admin: Thống kê tổng số công tơ điện
    public int getTotalRooms() {
        String sql = "SELECT COUNT(*) FROM CVB_CongToDien";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Admin: Thống kê số công tơ điện đang hoạt động
    public int getAvailableRooms() {
        String sql = "SELECT COUNT(*) FROM CVB_CongToDien WHERE trangThai = 'hoatdong'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Admin: Thống kê tổng số khách hàng
    public int getTotalTenants() {
        String sql = "SELECT COUNT(*) FROM CVB_NguoiDung WHERE vaiTro = 'khachhang'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Admin: Thống kê doanh thu tháng hiện tại (đã bỏ thống kê theo tháng)
    public double getMonthlyRevenue() {
        return 0.0;
    }

    // Admin: Thống kê tổng số hóa đơn chưa thanh toán
    public int getTotalHoaDonChuaThanhToan() {
        String sql = "SELECT COUNT(*) FROM CVB_HoaDon WHERE trangThai = 'chuaTT'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Admin: Thống kê tổng số chỉ số điện
    public int getTotalChiSoDien() {
        String sql = "SELECT COUNT(*) FROM CVB_ChiSoDien";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Lấy danh sách tất cả khách hàng
    public List<CVB_NguoiDung> getAllKhachHang() {
        String sql = "SELECT * FROM CVB_NguoiDung WHERE vaiTro = 'khachhang'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new CVB_NguoiDung(
                rs.getInt("idNguoiDung"),
                rs.getString("hoTen"),
                rs.getString("email"),
                rs.getString("sdt"),
                rs.getString("diaChi"),
                rs.getString("vaiTro"),
                rs.getString("password")
        ));
    }

    // Lấy người dùng theo id
    public CVB_NguoiDung getNguoiDungById(int idNguoiDung) {
        String sql = "SELECT * FROM CVB_NguoiDung WHERE idNguoiDung = ?";
        List<CVB_NguoiDung> nguoiDungs = jdbcTemplate.query(sql, new Object[]{idNguoiDung}, (rs, rowNum) -> new CVB_NguoiDung(
                rs.getInt("idNguoiDung"),
                rs.getString("hoTen"),
                rs.getString("email"),
                rs.getString("sdt"),
                rs.getString("diaChi"),
                rs.getString("vaiTro"),
                rs.getString("password")
        ));
        return nguoiDungs.isEmpty() ? null : nguoiDungs.get(0);
    }

    // Thêm người dùng mới
    public void insertNguoiDung(CVB_NguoiDung nguoiDung) {
        System.out.println("insertNguoiDung() được gọi."); // Kiểm tra phương thức được gọi
        String sql = "INSERT INTO CVB_NguoiDung (hoTen, email, sdt, diaChi, vaiTro, password) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, nguoiDung.getHoTen(), nguoiDung.getEmail(), nguoiDung.getSdt(), nguoiDung.getDiaChi(), nguoiDung.getVaiTro(), nguoiDung.getPassword());
        System.out.println("Người dùng đã được thêm vào cơ sở dữ liệu."); // Kiểm tra thêm người dùng
    }
}