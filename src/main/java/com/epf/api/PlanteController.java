package com.epf.api;

import com.epf.core.PlanteService;
import com.epf.core.Plante;
import com.epf.core.ServiceException;
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
}
