package com.epf.test;

import com.epf.core.MapJeu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    // Dans cette classe de test, on teste les différents cas de validité ou d'invalidité lors de la création
    // d'une instance de Mapjeu.
    // 2 cas : colonne et ligne positives, colonne et ligne négative
public class MapJeuTest {

    // test de création d'une instance de map jeu avec lignes et colonnes valides
    @Test
    public void testCreationMapJeuAvecDimensionsValides() {
        MapJeu map = new MapJeu(1, 9, 15, "img/map1.png");

        assertEquals(1, map.getId_map());
        assertEquals(10, map.getLigne());
        assertEquals(15, map.getColonne());
        assertEquals("img/map1.png", map.getChemin_image());
    }

    // test de création d'une instance de map jeu avec lignes et colonnes invalides
    @Test
    public void testCreationMapJeuAvecDimensionsNegatives() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new MapJeu(1, -5, -10, "img/map2.png");
        });

        assertEquals("Les dimensions de la carte ne peuvent pas être négatives", exception.getMessage());
    }

}
