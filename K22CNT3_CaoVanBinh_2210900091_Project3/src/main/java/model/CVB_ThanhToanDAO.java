package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CVB_ThanhToanDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CVB_ThanhToanDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CVB_ThanhToan> getAllThanhToan() {
        String sql = "SELECT * FROM CVB_ThanhToan";
        return jdbcTemplate.query(sql, this::mapRowToThanhToan);
    }

    public CVB_ThanhToan getThanhToanById(int id) {
        String sql = "SELECT * FROM CVB_ThanhToan WHERE idThanhToan = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToThanhToan, id);
    }

    public boolean insertThanhToan(CVB_ThanhToan thanhToan) {
        String sql = "INSERT INTO CVB_ThanhToan (idHoaDon, soTienTT, phuongThuc) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, thanhToan.getIdHoaDon(), thanhToan.getSoTienTT(), thanhToan.getPhuongThuc()) > 0;
    }

    public boolean updateThanhToan(CVB_ThanhToan thanhToan) {
        String sql = "UPDATE CVB_ThanhToan SET idHoaDon=?, soTienTT=?, phuongThuc=? WHERE idThanhToan=?";
        return jdbcTemplate.update(sql, thanhToan.getIdHoaDon(), thanhToan.getSoTienTT(), thanhToan.getPhuongThuc(), thanhToan.getIdThanhToan()) > 0;
    }

    public boolean deleteThanhToan(int id) {
        String sql = "DELETE FROM CVB_ThanhToan WHERE idThanhToan=?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    private CVB_ThanhToan mapRowToThanhToan(ResultSet rs, int rowNum) throws SQLException {
        return new CVB_ThanhToan(
                rs.getInt("idThanhToan"),
                rs.getInt("idHoaDon"),
                rs.getDouble("soTienTT"),
                rs.getString("phuongThuc")
        );
    }
}