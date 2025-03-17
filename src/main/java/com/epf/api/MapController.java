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

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping
    public List<MapJeu> getAllMaps() throws ServiceException {
        return mapService.getAllMaps();
    }
    //GetMapping{/id}
    //response entity (contener de la réponse°

    @PostMapping("/create")
    public ResponseEntity<String> addMap(@RequestBody MapJeu map) {
        try {
            mapService.create(map);
            return ResponseEntity.ok("Map créée avec succès !"); // 200 OK
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création de la map : " + e.getMessage()); // 400 Bad Request
        }
    }
    @GetMapping("/get/{id}")
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


//    @DeleteMapping("/{id}")
//    public void deleteMap(@PathVariable int id) throws ServiceException {
//        mapService.delete(id);
//    }
}
