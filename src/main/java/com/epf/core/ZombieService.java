package com.epf.core;

import com.epf.persistance.ZombieDao;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ZombieService {

    private final ZombieDao zombieDao;
    private final MapService mapService;

    public ZombieService(ZombieDao zombieDao, MapService mapService) {
        this.zombieDao = zombieDao;
        this.mapService= mapService;
    }

    // GetAllZombies : Utilisé dans /zombies.
    // Appelle la fonction get all zombies du zombieDAO qui renvoie l'ensemble des zombies en bdd.
    public List<Zombie> getAllZombies() throws ServiceException {
        try {
            return zombieDao.getAllZombies();
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des zombies", e);
        }
    }

    // Create : utilisé dans la route post de zombie.
    // Appelle la fonction create du zombieDao qui créée un zombie en BDD
    public void create(Zombie zombie) throws ServiceException {
        try {
            if (!mapService.mapExiste(zombie.getId_map())) {
                throw new IllegalArgumentException("La map avec l'ID " + zombie.getId_map() + " n'existe pas");
            }
            zombieDao.create(zombie);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création du zombie", e);
        }
    }

    // GetZombiebyId : Utilisé dans zombies/id.
    // Appelle la fonction getZombiesById du zombieDAO qui renvoie le zombie correspondant à l'id donnée
    public Zombie getZombieById(int id) throws ServiceException {
        try {
            return zombieDao.getZombieById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération du zombie avec l'ID " + id, e);
        }
    }

    // GetZombiebyMapId : Utilisé dans zombies/map/id.
    // Appelle la fonction findByMapId du zombieDAO qui renvoie les zombies liés à la map ayant pour id MapID
    public List<Zombie> getZombiesByMapId(int mapId) throws ServiceException {
        try {
            return zombieDao.findByMapId(mapId);
        } catch (Exception e) {
            throw new ServiceException("Impossible de récupérer les zombies pour la map " + mapId, e);
        }
    }

    // Update : utilisé dans la route put de zombies.
    // Récupère les zombies et sélectionne le zombie correspondant à l'id du zombie du request. Met à jour les valeurs de ses paramètres en bdd
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

            if (!mapService.mapExiste(zombie.getId_map())) {
                existingZombie.setId_map(zombie.getId_map());
            }

            // Mise à jour du zombie dans la base de données
            zombieDao.update(existingZombie);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du zombie", e);
        }
    }

    // Delete : utilisé dans la route delete de zombies.
    // Vérifie si le zombie existe. Si oui, Delete le zombie en bdd.
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
