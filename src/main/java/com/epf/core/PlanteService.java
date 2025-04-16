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

    // GetAllPlantes: Utilisé dans /plantes.
    // Appelle la fonction get all plantes du planteDAO qui renvoie l'ensemble des plantes en bdd.
    public List<Plante> getAllPlantes() throws ServiceException {
        try {
            return planteDao.getAllPlantes();
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des plantes", e);
        }
    }

    // Create : utilisé dans la route post de plantes. appelle la fonction create du planteDao qui créée une plante en BDD
    public void create(Plante plante) throws ServiceException {
        try {
            planteDao.create(plante);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de la plante", e);
        }
    }

    // GetPlantebyId : Utilisé dans plantes/id.
    // Appelle la fonction getPlanteById du planteDAO qui renvoie la plante correspondant à l'id donnée
    public Plante getPlantebyId(int id) throws ServiceException {
        try {
            return planteDao.getPlanteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération de la plante avec l'ID " + id, e);
        }
    }



    // Update : utilisé dans la route put de plantes.
    // Récupère les plantes et sélectionne la plante correspondant à l'id de la plante du request. Met à jour les valeurs de ses paramètres en bdd
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

    // Delete : utilisé dans la route delete de plantes.
    // Vérifie si la plante existe. Si oui, Delete la plante en bdd.
    public void deletePlante(int id) throws ServiceException {
        try {
            // Vérifier si la plante existe
            Plante plante = planteDao.getPlanteById(id);
            if (plante == null) {
                throw new ServiceException("Plante non trouvée pour l'ID : " + id);
            }
            // Appeler la méthode DAO pour supprimer la plante
            planteDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de la plante avec l'ID : " + id, e);
        }
    }


}
