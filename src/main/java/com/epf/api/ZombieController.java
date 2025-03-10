package com.epf.api;

import com.epf.core.ZombieService;
import com.epf.core.Zombie;
import com.epf.core.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zombies")
public class ZombieController {

    private final ZombieService zombieService;

    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @GetMapping
    public List<Zombie> getAllZombies() throws ServiceException {
        return zombieService.getAllZombies();
    }

    @PostMapping
    public void addZombie(@RequestBody Zombie zombie) throws ServiceException {
        zombieService.create(zombie);
    }
}
