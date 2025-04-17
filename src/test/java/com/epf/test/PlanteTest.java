package com.epf.test;

import com.epf.core.Plante;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Dans cette classe de test, on teste les différents cas de validité ou d'invalidité lors de la création
// d'une instance de Plante.
// 5 cas : valeur données valides, points de vie négatifs, dégâts négatifs, cout négatif, soleils/sec négatifs
public class PlanteTest {

    @Test
    public void testCreationPlanteValide() {
        Plante plante = new Plante(
                1, "Tournesol", 100, 0.0,
                0, 50, 1.5, "génère du soleil",
                "img/tournesol.png"
        );

        assertEquals(1, plante.getId_plante());
        assertEquals("Tournesol", plante.getNom());
        assertEquals(100, plante.getPoint_de_vie());
        assertEquals(0.0, plante.getAttaque_par_seconde());
        assertEquals(0, plante.getDegat_attaque());
        assertEquals(50, plante.getCout());
        assertEquals(1.5, plante.getSoleil_par_seconde());
        assertEquals("génère du soleil", plante.getEffet());
        assertEquals("img/tournesol.png", plante.getChemin_image());
    }

    @Test
    public void testPointDeVieNegatif() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Plante(1, "P", -10, 1.0, 5, 25, 0.5, "effet", "img.png");
        });
        assertEquals("Les points de vie ne peuvent pas être négatifs", exception.getMessage());
    }

    @Test
    public void testAttaqueParSecondeNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Plante(2, "P", 100, -0.5, 5, 25, 0.5, "effet", "img.png");
        });
        assertEquals("L'attaque par seconde ne peut pas être négative", exception.getMessage());
    }

    @Test
    public void testDegatAttaqueNegatif() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Plante(3, "P", 100, 1.0, -5, 25, 0.5, "effet", "img.png");
        });
        assertEquals("Les dégâts d'attaque ne peuvent pas être négatifs", exception.getMessage());
    }

    @Test
    public void testCoutNegatif() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Plante(4, "P", 100, 1.0, 5, -10, 0.5, "effet", "img.png");
        });
        assertEquals("Le coût ne peut pas être négatif", exception.getMessage());
    }

    @Test
    public void testSoleilParSecondeNegatif() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Plante(5, "P", 100, 1.0, 5, 10, -0.5, "effet", "img.png");
        });
        assertEquals("Le soleil généré par seconde ne peut pas être négatif", exception.getMessage());
    }
}
