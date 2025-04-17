package com.epf.test;

import com.epf.core.MapService;
import com.epf.core.ServiceException;
import com.epf.core.Zombie;
import com.epf.core.ZombieService;
import com.epf.persistance.ZombieDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Optional;
// Dans cette classe de test, on teste comment le programme réagit en cas de validité ou non validité d'un ID map
// donné lors de la création d'un zombie.
// 3 cas: la map existe, elle n'existe pas et il y a une erreur serveur.
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZombieServiceTest {

    private ZombieDao zombieDao;
    private MapService mapService;
    private ZombieService zombieService;

    @BeforeEach
    void setUp() {
        zombieDao = mock(ZombieDao.class);
        mapService = mock(MapService.class);
        zombieService = new ZombieService(zombieDao, mapService);
    }

    @Test
    void createZombie_shouldSucceed_whenMapExists() throws Exception {

        Zombie zombie = new Zombie();
        zombie.setId_map(1);

        when(mapService.mapExiste(1)).thenReturn(true);


        zombieService.create(zombie);


        verify(zombieDao, times(1)).create(zombie);
    }

    @Test
    void createZombie_shouldThrowException_whenMapDoesNotExist() {

        Zombie zombie = new Zombie();
        zombie.setId_map(42);

        when(mapService.mapExiste(42)).thenReturn(false);


        Exception exception = assertThrows(ServiceException.class, () -> {
            zombieService.create(zombie);
        });

        assertTrue(exception.getMessage().contains("Erreur lors de la création du zombie"));
        verify(zombieDao, never()).create(any());
    }

    @Test
    void createZombie_shouldWrapException_whenDaoFails() throws Exception {

        Zombie zombie = new Zombie();
        zombie.setId_map(1);

        when(mapService.mapExiste(1)).thenReturn(true);
        doThrow(new RuntimeException("DB failure")).when(zombieDao).create(zombie);

        Exception exception = assertThrows(ServiceException.class, () -> {
            zombieService.create(zombie);
        });

        assertTrue(exception.getMessage().contains("Erreur lors de la création du zombie"));
        verify(zombieDao, times(1)).create(zombie);
    }
}
