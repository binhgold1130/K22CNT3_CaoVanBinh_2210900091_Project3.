package model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CVB_ChiSoDienDAO {
    private final JdbcTemplate jdbcTemplate;

    public CVB_ChiSoDienDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CVB_ChiSoDien> getAllChiSoDien() {
        String query = "SELECT * FROM CVB_ChiSoDien";
        return jdbcTemplate.query(query, new ChiSoDienRowMapper());
    }

    public void addChiSoDien(CVB_ChiSoDien chiSoDien) {
        if (!checkCongToExists(chiSoDien.getIdCongTo())) {
            throw new IllegalArgumentException("ID công tơ không tồn tại.");
        }

        String query = "INSERT INTO CVB_ChiSoDien (idCongTo, chiSoCu, chiSoMoi) VALUES (?, ?, ?)";
        jdbcTemplate.update(query,
                chiSoDien.getIdCongTo(),
                chiSoDien.getChiSoCu(),
                chiSoDien.getChiSoMoi()
        );
    }

    public CVB_ChiSoDien getChiSoDienById(int id) {
        String query = "SELECT * FROM CVB_ChiSoDien WHERE idChiSo = ?";
        try {
            return jdbcTemplate.queryForObject(query, new ChiSoDienRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateChiSoDien(CVB_ChiSoDien chiSoDien) {
        String query = "UPDATE CVB_ChiSoDien SET idCongTo = ?, chiSoCu = ?, chiSoMoi = ? WHERE idChiSo = ?";
        jdbcTemplate.update(query,
                chiSoDien.getIdCongTo(),
                chiSoDien.getChiSoCu(),
                chiSoDien.getChiSoMoi(),
                chiSoDien.getIdChiSo()
        );
    }

    public void updateChiSoMoi(int idChiSo, int chiSoMoi) {
        CVB_ChiSoDien chiSoDien = getChiSoDienById(idChiSo);
        if (chiSoDien == null) return;

        int dienTieuThu = Math.max(0, chiSoMoi - chiSoDien.getChiSoCu());

        String query = "UPDATE CVB_ChiSoDien SET chiSoMoi = ?, dienTieuThu = ? WHERE idChiSo = ?";
        jdbcTemplate.update(query, chiSoMoi, dienTieuThu, idChiSo);
    }

    public void deleteChiSoDien(int id) {
        String query = "DELETE FROM CVB_ChiSoDien WHERE idChiSo = ?";
        jdbcTemplate.update(query, id);
    }

    public boolean checkCongToExists(int idCongTo) {
        String sql = "SELECT COUNT(*) FROM cvb_congtodien WHERE idCongTo = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idCongTo);
        return count != null && count > 0;
    }

    private static class ChiSoDienRowMapper implements RowMapper<CVB_ChiSoDien> {
        @Override
        public CVB_ChiSoDien mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CVB_ChiSoDien(
                    rs.getInt("idChiSo"),
                    rs.getInt("idCongTo"),
                    rs.getInt("chiSoCu"),
                    rs.getInt("chiSoMoi"),
                    rs.getInt("dienTieuThu")
            );
        }
    }
}