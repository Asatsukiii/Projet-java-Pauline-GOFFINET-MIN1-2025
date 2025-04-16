package com.epf.core;

import com.epf.persistance.ZombieDao;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ZombieService {

    private final ZombieDao zombieDao;

    public ZombieService(ZombieDao zombieDao) {
        this.zombieDao = zombieDao;
    }

    // Récupérer tous les zombies
    public List<Zombie> getAllZombies() throws ServiceException {
        try {
            return zombieDao.getAllZombies();
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des zombies", e);
        }
    }

    // Ajouter un zombie
    public void create(Zombie zombie) throws ServiceException {
        try {
            zombieDao.create(zombie);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création du zombie", e);
        }
    }


    public Zombie getZombieById(int id) throws ServiceException {
        try {
            return zombieDao.getZombieById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération du zombie avec l'ID " + id, e);
        }
    }

    public List<Zombie> getZombiesByMapId(int mapId) throws ServiceException {
        try {
            return zombieDao.findByMapId(mapId);
        } catch (Exception e) {
            throw new ServiceException("Impossible de récupérer les zombies pour la map " + mapId, e);
        }
    }


    public void update(Zombie zombie) throws ServiceException {
        try {
            // Récupérer le zombie existant depuis la base de données
            Zombie existingZombie = zombieDao.getZombieById(zombie.getId_zombie());

            // Mettre à jour les champs seulement si la nouvelle valeur est valide
            if (zombie.getNom() != null && !zombie.getNom().isEmpty()) {
                existingZombie.setNom(zombie.getNom());
            }

            if (zombie.getPoint_de_vie() > 0) {
                existingZombie.setPoint_de_vie(zombie.getPoint_de_vie());
            }

            if (zombie.getAttaque_par_seconde() > 0) {
                existingZombie.setAttaque_par_seconde(zombie.getAttaque_par_seconde());
            }

            if (zombie.getDegat_attaque() > 0) {
                existingZombie.setDegat_attaque(zombie.getDegat_attaque());
            }

            if (zombie.getVitesse_de_deplacement() > 0) {
                existingZombie.setVitesse_de_deplacement(zombie.getVitesse_de_deplacement());
            }

            if (zombie.getChemin_image() != null && !zombie.getChemin_image().isEmpty()) {
                existingZombie.setChemin_image(zombie.getChemin_image());
            }

            if (zombie.getId_map() != 0) {
                existingZombie.setId_map(zombie.getId_map());
            }

            // Mise à jour du zombie dans la base de données
            zombieDao.update(existingZombie);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du zombie", e);
        }
    }
    public void deleteZombie(int id) throws ServiceException {
        try {
            // Vérifier si le zombie existe
            Zombie zombie = zombieDao.getZombieById(id);
            if (zombie == null) {
                throw new ServiceException("Zombie non trouvé pour l'ID : " + id);
            }
            // Appeler la méthode DAO pour supprimer le zombie
            zombieDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression du zombie avec l'ID : " + id, e);
        }
    }


}
