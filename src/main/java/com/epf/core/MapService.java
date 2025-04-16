package com.epf.core;

import com.epf.persistance.MapDao;
import com.epf.persistance.ZombieDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {

    private final MapDao mapDao;
    private final ZombieDao zombieDao;

    public MapService(MapDao mapDao, ZombieDao zombieDao) {
        this.mapDao = mapDao;
        this.zombieDao = zombieDao;
    }

    public void create(MapJeu mapJeu) throws ServiceException {
        try {
            mapDao.create(mapJeu);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de la map", e);
        }
    }
    public void update(MapJeu mapJeu) throws ServiceException {
        try {
            // Récupérer toutes les maps
            List<MapJeu> allMaps = mapDao.getAllMaps();

            // Trouver la map existante avec l'ID correspondant
            MapJeu existingMap = allMaps.stream()
                    .filter(map -> map.getId_map() == mapJeu.getId_map())
                    .findFirst()
                    .orElseThrow(() -> new ServiceException("Map non trouvée pour l'ID : " + mapJeu.getId_map()));

            // Mettre à jour les champs seulement si la nouvelle valeur est valide
            if (mapJeu.getLigne() != 0) {
                existingMap.setLigne(mapJeu.getLigne());
            }

            if (mapJeu.getColonne() != 0) {
                existingMap.setColonne(mapJeu.getColonne());
            }

            if (mapJeu.getChemin_image() != null && !mapJeu.getChemin_image().isEmpty()) {
                existingMap.setChemin_image(mapJeu.getChemin_image());
            }

            mapDao.update(existingMap); // On met à jour la map dans la base de données
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour de la map", e);
        }
    }


    public List<MapJeu> getAllMaps() throws ServiceException {
        try {
            return mapDao.getAllMaps();
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des maps", e);
        }
    }

    public MapJeu getMap(int id) throws ServiceException {
        try {
            List<MapJeu> maps = mapDao.getAllMaps();
            return maps.stream()
                    .filter(map -> map.getId_map() == id)
                    .findFirst()
                    .orElseThrow(() -> new ServiceException("Map non trouvée pour l'ID : " + id));
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des maps", e);
        }
    }


    public void deleteMap(int id) throws ServiceException {
        try {
            MapJeu map = mapDao.getMapById(id);
            if (map == null) {
                throw new ServiceException("Map non trouvée pour l'ID : " + id);
            }

            zombieDao.deleteZombiesByMapId(id);
            mapDao.delete(id);

        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de la map avec l'ID : " + id, e);
        }
    }

}
