package com.epf.core;

public class Zombie {
    private int id_zombie;
    private String nom;
    private int point_de_vie;
    private double attaque_par_seconde;
    private int degat_attaque;
    private double vitesse_de_deplacement;
    private String chemin_image;
    private int id_map;

    //constructeur vide : nécessaire pour la bonne instantiation d'un json en objet Zombie (request post et map)
    public Zombie() {
    }

    // Constructeur
    public Zombie(int id_zombie, String nom, int point_de_vie, double attaque_par_seconde, int degat_attaque, double vitesse_de_deplacement, String chemin_image, int id_map) {
        //on vérifie que l'attaque, les point de vie, les dégâts et la vitesse ne sont pas négatifs,
        // sinon génère erreur
        if (point_de_vie < 0) {
            throw new IllegalArgumentException("Les points de vie ne peuvent pas être négatifs");
        }
        if (attaque_par_seconde < 0) {
            throw new IllegalArgumentException("L'attaque par seconde ne peut pas être négative");
        }
        if (degat_attaque < 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque ne peuvent pas être négatifs");
        }
        if (vitesse_de_deplacement < 0) {
            throw new IllegalArgumentException("La vitesse de déplacement ne peut pas être négative");
        }

        this.id_zombie = id_zombie;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.vitesse_de_deplacement = vitesse_de_deplacement;
        this.chemin_image = chemin_image;
        this.id_map = id_map;
    }

    // Getters
    public int getId_zombie() {
        return id_zombie;
    }

    public String getNom() {
        return nom;
    }

    public int getPoint_de_vie() {
        return point_de_vie;
    }

    public double getAttaque_par_seconde() {
        return attaque_par_seconde;
    }

    public int getDegat_attaque() {
        return degat_attaque;
    }

    public double getVitesse_de_deplacement() {
        return vitesse_de_deplacement;
    }

    public String getChemin_image() {
        return chemin_image;
    }

    public int getId_map() {
        return id_map;
    }

    // Setters
    public void setId_zombie(int id_zombie) {
        this.id_zombie = id_zombie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoint_de_vie(int point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    public void setAttaque_par_seconde(double attaque_par_seconde) {
        this.attaque_par_seconde = attaque_par_seconde;
    }

    public void setDegat_attaque(int degat_attaque) {
        this.degat_attaque = degat_attaque;
    }

    public void setVitesse_de_deplacement(double vitesse_de_deplacement) {
        this.vitesse_de_deplacement = vitesse_de_deplacement;
    }

    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }

    public void setId_map(int id_map) {
        this.id_map = id_map;
    }

    // Méthode ToString
    @Override
    public String toString() {
        return "Zombie{" +
                "idZombie=" + id_zombie +
                ", nom='" + nom + '\'' +
                ", pointDeVie=" + point_de_vie +
                ", attaqueParSeconde=" + attaque_par_seconde +
                ", degatAttaque=" + degat_attaque +
                ", vitesseDeDeplacement=" + vitesse_de_deplacement +
                ", cheminImage='" + chemin_image + '\'' +
                ", idMap=" + id_map +
                '}';
    }
}

