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

    // GetAllMaps : Utilisé dans /maps.
    // Appelle la fonction get all maps du MapDAO qui renvoie l'ensemble des maps en bdd.
    public List<MapJeu> getAllMaps() throws ServiceException {
        try {
            return mapDao.getAllMaps();
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des maps", e);
        }
    }

    // Create : utilisé dans la route post de maps. appelle la fonction create du mapDao qui créée une map en bdd.
    public void create(MapJeu mapJeu) throws ServiceException {
        try {
            mapDao.create(mapJeu);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de la map", e);
        }
    }

    // getMap: Utilisé dans maps/id.
    // Renvoie la map correspondant à l'id donnée.
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

    // MapExiste: utilisé .
    // Retourne un boolean vérifiant si l'ID fournit a la fonction est un ID de map valide
    public boolean mapExiste(int id_map)  {
        List<MapJeu> maps = mapDao.getAllMaps();
        return maps.stream().anyMatch(map -> map.getId_map() == id_map);
    }

    // Update : utilisé dans la route put de maps.
    // Récupère les maps et sélectionne la map correspondant à l'id de la map du request et met à jour les valeurs de ses paramètres en bdd
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
            if (mapJeu.getLigne() > 0) {
                existingMap.setLigne(mapJeu.getLigne());
            }

            if (mapJeu.getColonne() > 0) {
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


    // Delete : utilisé dans la route delete de maps.
    // Vérifie si la map existe. appelle d'abord la fonction du zombieDAO permettant de delete les zombies lié à la map,
    // puis delete la map.
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
