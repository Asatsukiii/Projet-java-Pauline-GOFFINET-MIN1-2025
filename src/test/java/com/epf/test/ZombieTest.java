package com.epf.test;

import com.epf.core.Zombie;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZombieTest {

    @Test
    public void testCreationZombieValide() {
        Zombie zombie = new Zombie(
                1, "Zombie de base", 150, 1.0, 25,
                0.75, "img/zombie.png", 2
        );

        assertEquals(1, zombie.getId_zombie());
        assertEquals("Zombie de base", zombie.getNom());
        assertEquals(150, zombie.getPoint_de_vie());
        assertEquals(1.0, zombie.getAttaque_par_seconde());
        assertEquals(25, zombie.getDegat_attaque());
        assertEquals(0.75, zombie.getVitesse_de_deplacement());
        assertEquals("img/zombie.png", zombie.getChemin_image());
        assertEquals(2, zombie.getId_map());
    }

    @Test
    public void testPointDeVieNegatif() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Zombie(1, "Zombie", -100, 1.0, 10, 1.0, "img.png", 1);
        });
        assertEquals("Les points de vie ne peuvent pas être négatifs", exception.getMessage());
    }

    @Test
    public void testAttaqueParSecondeNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Zombie(2, "Zombie", 100, -1.0, 10, 1.0, "img.png", 1);
        });
        assertEquals("L'attaque par seconde ne peut pas être négative", exception.getMessage());
    }

    @Test
    public void testDegatAttaqueNegatif() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Zombie(3, "Zombie", 100, 1.0, -10, 1.0, "img.png", 1);
        });
        assertEquals("Les dégâts d'attaque ne peuvent pas être négatifs", exception.getMessage());
    }

    @Test
    public void testVitesseDeDeplacementNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Zombie(4, "Zombie", 100, 1.0, 10, -0.5, "img.png", 1);
        });
        assertEquals("La vitesse de déplacement ne peut pas être négative", exception.getMessage());
    }
}
