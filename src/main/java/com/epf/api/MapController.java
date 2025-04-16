package com.epf.api;

import com.epf.core.MapService;
import com.epf.core.MapJeu;
import com.epf.core.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapController {

    //initialisation du map service
    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }
    // /maps : affiche un json composé de l'ensemble des maps en bdd
    @GetMapping
    public List<MapJeu> getAllMaps() throws ServiceException {
        return mapService.getAllMaps();
    }

    // route post de maps : permet de créer une map en bdd en donnant dans la request les valeurs souhaitées pour les paramètres.
    @PostMapping
    public ResponseEntity<String> addMap(@RequestBody MapJeu map) {
        try {
            mapService.create(map);
            return ResponseEntity.ok("Map créée avec succès !"); // 200 OK
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création de la map : " + e.getMessage()); // 400 Bad Request
        }
    }

    // /maps/id : affiche les information de la map ayant pour id celui mis dans l'url
    @GetMapping("/{id}")
    public ResponseEntity<?> getMapById(@PathVariable int id) {
        try {
            MapJeu foundMap = mapService.getMap(id);
            return ResponseEntity.ok(foundMap); // Retourne la map trouvée en JSON
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Map non trouvée : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }

    // /validation: route permettant de verifier si le format de données renvoyé par les routes est valide.
    @GetMapping("/validation")
    public ResponseEntity<MapJeu> validateMapFormat() {
        MapJeu exampleMap = new MapJeu(1, 5, 9, "chemin/vers/image.png");
        return ResponseEntity.ok(exampleMap);
    }

    // route put de maps: permet la modification d'une map en récupérant dans la request un id de la map à modifier
    // et les nouvelles valeurs des paramètres de la map.
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMap(@PathVariable int id, @RequestBody MapJeu mapJeu) {
        try {
            // Mise à jour de la map en utilisant le service
            mapJeu.setId_map(id);
            mapService.update(mapJeu); // Appel à la méthode de mise à jour dans le service
            return ResponseEntity.ok("Map mise à jour avec succès !"); // 200 OK
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour de la map : " + e.getMessage()); // 400 Bad Request
        }
    }

    // route delete de maps: permet d'effacer une map. efface aussi les zombies liés a la map.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMap(@PathVariable int id) {
        try {
            mapService.deleteMap(id);
            return ResponseEntity.ok("Map et ses zombies supprimés avec succès !");
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression de la map : " + e.getMessage());
        }
    }


}
