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

    // Get all map : utilisé dans la fonction getAllMap de MapService.
    // Fait une requète à la bdd pour récupérer toute les map.
    public List<MapJeu> getAllMaps() {
        String sql = "SELECT * FROM map";
        return jdbcTemplate.query(sql, new MapRowMapper());
    }

    // Create : utilisé par create dans MapService.
    // Insère dans la bdd une nouvelle map ayant pour valeur celle transmise dans le body de la request post
    public void create(MapJeu mapJeu) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, mapJeu.getLigne(), mapJeu.getColonne(), mapJeu.getChemin_image());
    }

    // getMapById : utilisé par getMapById dans MapService.
    // Cherche en bdd la map ayant pour ID l'id récupéré dans l'url
    public MapJeu getMapById(int id) {
        String sql = "SELECT * FROM map WHERE id_map = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new MapJeu(
                rs.getInt("id_map"),
                rs.getInt("ligne"),
                rs.getInt("colonne"),
                rs.getString("chemin_image")
        ), id);
    }

    //  update : utilisé par update dans MapService.
    // envoie une requete en bdd pour modifier les champs de la map dont l'id correspond a celui de la request
    public void update(MapJeu mapJeu) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, mapJeu.getLigne(), mapJeu.getColonne(), mapJeu.getChemin_image(), mapJeu.getId_map());
    }

    //  delete : utilisé par delete dans MapService.
    // envoie une requete en bdd pour supprimer la map dont l'id correspond à celui donné dans l'url
    public void delete(int id) {
        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, id);
    }

}
