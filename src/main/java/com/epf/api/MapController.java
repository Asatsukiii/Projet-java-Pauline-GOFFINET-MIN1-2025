package com.epf.api;

import com.epf.core.MapService;
import com.epf.core.Map;
import com.epf.core.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping
    public List<Map> getAllMaps() throws ServiceException {
        return mapService.getAllMaps();
    }

    @PostMapping
    public void addMap(@RequestBody Map map) throws ServiceException {
        mapService.create(map);
    }

//    @DeleteMapping("/{id}")
//    public void deleteMap(@PathVariable int id) throws ServiceException {
//        mapService.delete(id);
//    }
}
