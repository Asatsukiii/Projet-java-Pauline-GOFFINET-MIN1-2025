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

    private final PlanteService planteService;

    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }

    @GetMapping
    public List<Plante> getAllPlantes() throws ServiceException {
        return planteService.getAllPlantes();
    }

    @PostMapping
    public void addPlante(@RequestBody Plante plante) throws ServiceException {
        planteService.create(plante);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getMapById(@PathVariable int id) {
        try {
            Plante foundMap = planteService.getPlantebyId(id);
            return ResponseEntity.ok(foundMap); // Retourne la map trouvée en JSON
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }
    @GetMapping("/validation")
    public ResponseEntity<Plante> validatePlanteFormat() {
        Plante examplePlante = new Plante(1, "Tournesol", 100, 2, 1, 50, 25, "Régénération", "chemin/vers/image.png");
        return ResponseEntity.ok(examplePlante);
    }

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

}
