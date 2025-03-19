package model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CVB_CongToDienDAO {
    private final JdbcTemplate jdbcTemplate;

    public CVB_CongToDienDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CVB_CongToDien> getAllCongTo() {
        String sql = "SELECT * FROM CVB_CongToDien";
        return jdbcTemplate.query(sql, new RowMapper<CVB_CongToDien>() {
            @Override
            public CVB_CongToDien mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CVB_CongToDien(
                        rs.getInt("idCongTo"),
                        rs.getInt("idNguoiDung"),
                        rs.getString("soCongTo"),
                        rs.getString("trangThai")
                );
            }
        });
    }

    public void addCongTo(CVB_CongToDien congTo) {
        System.out.println("Dữ liệu nhận được: " + congTo.getSoCongTo() + ", " + congTo.getTrangThai());

        String sql = "INSERT INTO CVB_CongToDien (idNguoiDung, soCongTo, trangThai) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, congTo.getIdNguoiDung(), congTo.getSoCongTo(), congTo.getTrangThai());
    }

    public void updateCongTo(CVB_CongToDien congTo) {
        String sql = "UPDATE CVB_CongToDien SET idNguoiDung=?, soCongTo=?, trangThai=? WHERE idCongTo=?";
        jdbcTemplate.update(sql, congTo.getIdNguoiDung(), congTo.getSoCongTo(), congTo.getTrangThai(), congTo.getIdCongTo());
    }

    public void deleteCongTo(int id) {
        String sql = "DELETE FROM CVB_CongToDien WHERE idCongTo=?";
        jdbcTemplate.update(sql, id);
    }

    public CVB_CongToDien getCongToById(int id) {
        String sql = "SELECT * FROM CVB_CongToDien WHERE idCongTo=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new CVB_CongToDien(
                        rs.getInt("idCongTo"),
                        rs.getInt("idNguoiDung"),
                        rs.getString("soCongTo"),
                        rs.getString("trangThai")
                )
        );
    }
}