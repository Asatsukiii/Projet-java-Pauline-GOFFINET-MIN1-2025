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

    // Récupérer un zombie par son ID
    public Zombie getZombieById(int id) throws ServiceException {
        try {
            return zombieDao.getZombieById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération du zombie avec l'ID " + id, e);
        }
    }
}
