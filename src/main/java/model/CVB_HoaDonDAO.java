package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CVB_HoaDonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CVB_HoaDonDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Lấy danh sách hóa đơn
    public List<CVB_HoaDon> getAllHoaDon() {
        String sql = "SELECT * FROM CVB_HoaDon";
        return jdbcTemplate.query(sql, this::mapRowToHoaDon);
    }

    // Lấy hóa đơn theo ID
    public CVB_HoaDon getHoaDonById(int id) {
        String sql = "SELECT * FROM CVB_HoaDon WHERE idHoaDon = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToHoaDon, id);
    }

    // Xóa hóa đơn
    public boolean deleteHoaDon(int id) {
        String sql = "DELETE FROM CVB_HoaDon WHERE idHoaDon=?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public boolean insertHoaDon(CVB_HoaDon hoaDon) {
        String sql = "INSERT INTO CVB_HoaDon (idNguoiDung, idChiSo, tongTien, trangThai) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, hoaDon.getIdNguoiDung(), hoaDon.getIdChiSo(), hoaDon.getTongTien(), hoaDon.getTrangThai()) > 0;
    }

    public boolean updateHoaDon(CVB_HoaDon hoaDon) {
        String sql = "UPDATE CVB_HoaDon SET idNguoiDung=?, idChiSo=?, tongTien=?, trangThai=? WHERE idHoaDon=?";
        return jdbcTemplate.update(sql, hoaDon.getIdNguoiDung(), hoaDon.getIdChiSo(), hoaDon.getTongTien(), hoaDon.getTrangThai(), hoaDon.getIdHoaDon()) > 0;
    }

    private CVB_HoaDon mapRowToHoaDon(ResultSet rs, int rowNum) throws SQLException {
        return new CVB_HoaDon(
                rs.getInt("idHoaDon"),
                rs.getInt("idNguoiDung"),
                rs.getInt("idChiSo"),
                rs.getDouble("tongTien"),
                rs.getString("trangThai")
        );
    }
}