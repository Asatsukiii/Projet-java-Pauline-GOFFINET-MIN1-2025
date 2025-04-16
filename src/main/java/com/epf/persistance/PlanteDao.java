package com.epf.persistance;

import com.epf.core.Plante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanteDao {

    private final JdbcTemplate jdbcTemplate;

    public PlanteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get all plante : utilisé dans la fonction getAllplante de planteService.
    // Fait une requète à la bdd pour récupérer toutes les plantes.
    public List<Plante> getAllPlantes() {
        String sql = "SELECT * FROM plante";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Plante(
                rs.getInt("id_plante"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getDouble("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getInt("cout"),
                rs.getDouble("soleil_par_seconde"),
                rs.getString("effet"),
                rs.getString("chemin_image")
        ));
    }

    // Create : utilisé par create dans planteService.
    // Insère dans la bdd une nouvelle plante ayant pour valeurs celles transmise dans le body de la request post
    public void create(Plante plante) {
        String sql = "INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, plante.getNom(), plante.getPoint_de_vie(), plante.getAttaque_par_seconde(),
                plante.getDegat_attaque(), plante.getCout(), plante.getSoleil_par_seconde(), plante.getEffet(),
                plante.getChemin_image());
    }

    // getplanteById : utilisé par getplanteById dans planteService.
    // Cherche en bdd la plante ayant pour ID l'id récupéré dans l'url
    public Plante getPlanteById(int id) {
        String sql = "SELECT * FROM plante WHERE id_plante = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Plante(
                rs.getInt("id_plante"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getDouble("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getInt("cout"),
                rs.getInt("soleil_par_seconde"),
                rs.getString("effet"),
                rs.getString("chemin_image")
        ), id);
    }

    //  update : utilisé par update dans planteService.
    // envoie une requete en bdd pour modifier les champs de la plante dont l'id correspond a celui de la request
    public void update(Plante plante) {
        String sql = "UPDATE plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?";

        jdbcTemplate.update(sql,
                plante.getNom(),
                plante.getPoint_de_vie(),
                plante.getAttaque_par_seconde(),
                plante.getDegat_attaque(),
                plante.getCout(),
                plante.getSoleil_par_seconde(),
                plante.getEffet(),
                plante.getChemin_image(),
                plante.getId_plante()
        );
    }

    //  Delete : utilisé par delete dans planteService.
    // Envoie une requete en bdd pour supprimer la plante dont l'id correspond à celui donné dans l'url
    public void delete(int id) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, id);
    }

}
