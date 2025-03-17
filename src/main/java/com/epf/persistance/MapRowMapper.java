package com.epf.persistance;

import com.epf.core.MapJeu;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapRowMapper implements RowMapper<MapJeu> {
    @Override
    public MapJeu mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MapJeu(
                rs.getInt("id_map"),
                rs.getInt("ligne"),
                rs.getInt("colonne"),
                rs.getString("chemin_image")
        );
    }
}
