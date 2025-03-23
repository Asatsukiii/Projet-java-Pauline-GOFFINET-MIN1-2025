package com.epf.persistance;

import com.epf.core.MapJeu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MapDao {

    private final JdbcTemplate jdbcTemplate;

    public MapDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(MapJeu mapJeu) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, mapJeu.getLigne(), mapJeu.getColonne(), mapJeu.getCheminImage());
    }
    public void update(MapJeu mapJeu) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, mapJeu.getLigne(), mapJeu.getColonne(), mapJeu.getCheminImage(), mapJeu.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<MapJeu> getAllMaps() {
        String sql = "SELECT * FROM map";
        return jdbcTemplate.query(sql, new MapRowMapper());
    }
}
