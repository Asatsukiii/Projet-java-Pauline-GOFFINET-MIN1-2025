package com.epf.api;

import com.epf.core.ZombieService;
import com.epf.core.Zombie;
import com.epf.core.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zombies") //pour les requetes get
public class ZombieController {

    //initialisation du zombie service
    private final ZombieService zombieService;

    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    // /Zombies : affiche un json composé de l'ensemble des zombies en bdd
    @GetMapping
    public List<Zombie> getAllZombies() throws ServiceException {
        return zombieService.getAllZombies();
    }

    // route post de plantes : permet de créer une plante en bdd en donnant dans la request les valeurs souhaitées pour les paramètres.
    @PostMapping
    public void addZombie(@RequestBody Zombie zombie) throws ServiceException {
        System.out.println(zombie.toString());
        zombieService.create(zombie);
    }

    // /zombies/id : affiche les informations du zombie ayant pour id celui mis dans l'url
    @GetMapping("/{id}")
    public ResponseEntity<?> getZombiesById(@PathVariable int id) {
        try {
            Zombie Zombie = zombieService.getZombieById(id);
            return ResponseEntity.ok(Zombie);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("zombie non trouvée : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }

    // /maps/id : affiche les zombies ayant pour Mapid celui mis dans l'url
    @GetMapping("/map/{id}")
    public ResponseEntity<?> getZombiesByMapId(@PathVariable int id) {
        try {
            List<Zombie> zombies = zombieService.getZombiesByMapId(id);
            if (zombies.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun zombie trouvé pour cette map.");
            }
            return ResponseEntity.ok(zombies);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur de service : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }

    // /validation: route permettant de verifier si le format de données renvoyé par les routes est valide.
    @GetMapping("/validation")
    public ResponseEntity<Zombie> validateZombieFormat() {
        Zombie exampleZombie = new Zombie(1, "Zombie Basique", 150, 1, 25, 1, "chemin/vers/image.png", 2);
        return ResponseEntity.ok(exampleZombie);
    }

    // route put de zombies: permet la modification d'un zombie en récupérant dans la request un id du zombie à modifier
    // et les nouvelles valeurs des paramètres du zombie.
    @PutMapping("/{id}")
    public ResponseEntity<String> updateZombie(@PathVariable int id, @RequestBody Zombie zombie) throws ServiceException {
        try {
            zombie.setId_zombie(id);
            zombieService.update(zombie);
            return ResponseEntity.ok(" zombie mise à jour avec succès !"); // 200 OK
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour de la map : " + e.getMessage()); // 400 Bad Request
        }
    }

    // route delete de zombies: permet d'effacer un zombie.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZombie(@PathVariable int id) {
        try {
            zombieService.deleteZombie(id);
            return ResponseEntity.ok("Zombie supprimé avec succès !");
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression du zombie : " + e.getMessage());
        }
    }


}
