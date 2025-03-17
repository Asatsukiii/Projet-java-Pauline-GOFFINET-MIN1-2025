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

    public List<MapJeu> getAllMaps() throws ServiceException {
        try {
            return mapDao.getAllMaps();
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la récupération des maps", e);
        }
    }



}
