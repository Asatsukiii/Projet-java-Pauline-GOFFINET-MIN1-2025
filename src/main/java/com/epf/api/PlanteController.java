package com.epf.api;

import com.epf.core.PlanteService;
import com.epf.core.Plante;
import com.epf.core.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantes")
public class PlanteController {

    //initialisation du plante service
    private final PlanteService planteService;

    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }

    // /plantes : affiche un json composé de l'ensemble des plantes en bdd
    @GetMapping
    public List<Plante> getAllPlantes() throws ServiceException {
        return planteService.getAllPlantes();
    }

    // route post de plantes : permet de créer une plante en bdd en donnant dans la request les valeurs souhaitées pour les paramètres.
    @PostMapping
    public void addPlante(@RequestBody Plante plante) throws ServiceException {
        planteService.create(plante);
    }

    // /plantes/id : affiche les informations de la plante ayant pour id celui mis dans l'url
    @GetMapping("/{id}")
    public ResponseEntity<?> getMapById(@PathVariable int id) {
        try {
            Plante foundMap = planteService.getPlantebyId(id);
            return ResponseEntity.ok(foundMap); // Retourne la map trouvée en JSON
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }

    // /validation: route permettant de verifier si le format de données renvoyé par les routes est valide.
    @GetMapping("/validation")
    public ResponseEntity<Plante> validatePlanteFormat() {
        Plante examplePlante = new Plante(1, "Tournesol", 100, 2, 1, 50, 25, "Régénération", "chemin/vers/image.png");
        return ResponseEntity.ok(examplePlante);
    }

    // route put de plantes: permet la modification d'une plante en récupérant dans la request un id de la plante à modifier
    // et les nouvelles valeurs des paramètres de la plante.
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlante(@PathVariable int id, @RequestBody Plante plante) {
        try {
            // Assigner l'ID à la plante reçue
            plante.setId_plante(id);

            // Appeler le service pour mettre à jour la plante
            planteService.update(plante);

            return ResponseEntity.ok("Plante mise à jour avec succès !");
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour de la plante : " + e.getMessage());
        }
    }

    // route delete de plantes: permet d'effacer une plante.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlante(@PathVariable int id) {
        try {
            planteService.deletePlante(id);
            return ResponseEntity.ok("Plante supprimée avec succès !");
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression de la plante : " + e.getMessage());
        }
    }


}
