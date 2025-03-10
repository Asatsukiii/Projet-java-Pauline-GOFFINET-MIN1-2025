package com.epf.core;

import com.epf.persistance.PlanteDao;
import com.epf.core.ServiceException;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanteService {

    private final PlanteDao planteDao;

    public PlanteService(PlanteDao   planteDao) {
        this.planteDao = planteDao;
    }

    public void create(Plante plante) throws ServiceException {
        try {
            planteDao.create(plante);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de la plante", e);
        }
    }

    public List<Plante> getAllPlantes() throws ServiceException {
        try {
            return planteDao.getAllPlantes();
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des plantes", e);
        }
    }
}
