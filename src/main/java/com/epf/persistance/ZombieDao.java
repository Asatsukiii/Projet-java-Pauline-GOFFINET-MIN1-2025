package com.epf.persistance;

import com.epf.core.Zombie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZombieDao {

    private final JdbcTemplate jdbcTemplate;

    public ZombieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get all zombie : utilisé dans la fonction getAllzombie de zombieService.
    // Fait une requète à la bdd pour récupérer tous les zombies.
    public List<Zombie> getAllZombies() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Zombie(
                rs.getInt("id_zombie"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getDouble("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getDouble("vitesse_de_deplacement"),
                rs.getString("chemin_image"),
                rs.getInt("id_map")
        ));
    }

    // Create : utilisé par create dans zombieService.
    // Insère dans la bdd un nouveau zombie ayant pour valeurs celles transmises dans le body de la request post
    public void create(Zombie zombie) {
        String sql = "INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPoint_de_vie(), zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(), zombie.getVitesse_de_deplacement(), zombie.getChemin_image(), zombie.getId_map());
    }

    // getzombieById : utilisé par getzombieById dans zombieService.
    // Cherche en bdd le zombie ayant pour ID l'id récupéré dans l'url
    public Zombie getZombieById(int id) {
        String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Zombie(
                rs.getInt("id_zombie"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getDouble("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getDouble("vitesse_de_deplacement"),
                rs.getString("chemin_image"),
                rs.getInt("id_map")
        ), id);
    }

    //  update : utilisé par update dans zombieService.
    // envoie une requete en bdd pour modifier les champs du zombie dont l'id correspond à celui de la request
    public void update(Zombie zombie) {
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";

        jdbcTemplate.update(sql,
                zombie.getNom(),
                zombie.getPoint_de_vie(),
                zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(),
                zombie.getVitesse_de_deplacement(),
                zombie.getChemin_image(),
                zombie.getId_map(),
                zombie.getId_zombie()
        );
    }

    // findByMapId: utilisé dans findByMapId.
    // Cherche dans la base de donnée les zombies correspondant a un ID map donné dans l'URL
    // retourne la liste des zombies correspondant à ce critère
    public List<Zombie> findByMapId(int mapId) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Zombie(
                rs.getInt("id_zombie"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getDouble("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getDouble("vitesse_de_deplacement"),
                rs.getString("chemin_image"),
                rs.getInt("id_map")
        ), mapId);
    }

    //  Delete : utilisé par delete dans zombieService.
    // Envoie une requete en bdd pour supprimer le zombie dont l'id correspond à celui donné dans l'url
    public void delete(int id) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, id);
    }

    // deleteZombiebyMapId:
    public void deleteZombiesByMapId(int idMap) {
        String sql = "DELETE FROM zombie WHERE id_map = ?";
        jdbcTemplate.update(sql, idMap);
    }


}
