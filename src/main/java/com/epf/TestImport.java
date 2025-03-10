package com.epf;

import com.epf.config.DatabaseConfig;
import com.epf.core.PlanteService;
import com.epf.core.ZombieService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestImport {

    public static void main(String[] args) {
        // Initialisation du contexte avec la configuration de la base de données
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);

        // Get PlanteService bean
        PlanteService planteService = context.getBean(PlanteService.class);

        ZombieService zombieService = context.getBean(ZombieService.class);


        // utiliser le service pour get et montrer les plantes
        try {
            System.out.println("Plantes:\n");
            planteService.getAllPlantes().forEach(System.out::println);
            System.out.println("Zombies:\n");
            zombieService.getAllZombies().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ferme le contexte après utilisation
        context.close();
    }
}
