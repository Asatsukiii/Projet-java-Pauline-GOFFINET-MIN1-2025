package com.epf.core;

import com.epf.persistance.MapDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {

    private final MapDao mapDao;

    public MapService(MapDao mapDao) {
        this.mapDao = mapDao;
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
                    .filter(map -> map.getId() == mapJeu.getId())
                    .findFirst()
                    .orElseThrow(() -> new ServiceException("Map non trouvée pour l'ID : " + mapJeu.getId()));

            // Mettre à jour les champs seulement si la nouvelle valeur est valide
            if (mapJeu.getLigne() != 0) {
                existingMap.setLigne(mapJeu.getLigne());
            }

            if (mapJeu.getColonne() != 0) {
                existingMap.setColonne(mapJeu.getColonne());
            }

            if (mapJeu.getCheminImage() != null && !mapJeu.getCheminImage().isEmpty()) {
                existingMap.setCheminImage(mapJeu.getCheminImage());
            }

            mapDao.update(existingMap); // On met à jour la map dans la base de données
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour de la map", e);
        }
    }



    public void delete(int id) throws ServiceException {
        try {
            mapDao.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de la map avec l'ID : " + id, e);
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
                    .filter(map -> map.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new ServiceException("Map non trouvée pour l'ID : " + id));
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des maps", e);
        }
    }


}
