package com.epf.core;

import com.epf.persistance.PlanteDao;

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

    public Plante getPlantebyId(int id) throws ServiceException {
        try {
            return planteDao.getPlanteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération du zombie avec l'ID " + id, e);
        }
    }

    public void update(Plante plante) throws ServiceException {
        try {
            // Récupérer la plante existante pour ne pas écraser les champs vides
            Plante existingPlante = planteDao.getPlanteById(plante.getId_plante());

            // Mettre à jour les champs seulement si la nouvelle valeur est valide
            if (plante.getNom() != null && !plante.getNom().isEmpty()) {
                existingPlante.setNom(plante.getNom());
            }
            if (plante.getPoint_de_vie() > 0) {
                existingPlante.setPoint_de_vie(plante.getPoint_de_vie());
            }
            if (plante.getAttaque_par_seconde() > 0) {
                existingPlante.setAttaque_par_seconde(plante.getAttaque_par_seconde());
            }
            if (plante.getDegat_attaque() > 0) {
                existingPlante.setDegat_attaque(plante.getDegat_attaque());
            }
            if (plante.getCout() > 0) {
                existingPlante.setCout(plante.getCout());
            }
            if (plante.getSoleil_par_seconde() > 0) {
                existingPlante.setSoleil_par_seconde(plante.getSoleil_par_seconde());
            }
            if (plante.getEffet() != null && !plante.getEffet().isEmpty()) {
                existingPlante.setEffet(plante.getEffet());
            }
            if (plante.getChemin_image() != null && !plante.getChemin_image().isEmpty()) {
                existingPlante.setChemin_image(plante.getChemin_image());
            }

            // Mettre à jour dans la base de données
            planteDao.update(existingPlante);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour de la plante", e);
        }
    }

}
