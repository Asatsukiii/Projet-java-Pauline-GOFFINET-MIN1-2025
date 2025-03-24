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

    private final ZombieService zombieService;

    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @GetMapping
    public List<Zombie> getAllZombies() throws ServiceException {
        return zombieService.getAllZombies();
    }

    @PostMapping //requetes add
    public void addZombie(@RequestBody Zombie zombie) throws ServiceException {
        zombieService.create(zombie);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getZombiesById(@PathVariable int id) {
        try {
            Zombie Zombie = zombieService.getZombieById(id);
            return ResponseEntity.ok(Zombie);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Map non trouvée : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }
    @GetMapping("/validation")
    public ResponseEntity<Zombie> validateZombieFormat() {
        Zombie exampleZombie = new Zombie(1, "Zombie Basique", 150, 1, 25, 1, "chemin/vers/image.png", 2);
        return ResponseEntity.ok(exampleZombie);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateZombie(@PathVariable int id, @RequestBody Zombie zombie) throws ServiceException {
        try {
            zombie.setId_zombie(id);
            zombieService.update(zombie); // Appel à la méthode de mise à jour dans le service
            return ResponseEntity.ok("Map mise à jour avec succès !"); // 200 OK
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour de la map : " + e.getMessage()); // 400 Bad Request
        }
    }

//    @DeleteMapping("/{id}")
//    public void deleteMap(@PathVariable int id) throws ServiceException {
//        zombieService.delete(id);
//    }

}
