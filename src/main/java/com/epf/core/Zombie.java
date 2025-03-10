package com.epf.core;



public class Zombie {
    private int idZombie;
    private String nom;
    private int pointDeVie;
    private double attaqueParSeconde;
    private int degatAttaque;
    private double vitesseDeDeplacement;
    private String cheminImage;
    private int idMap;

    // Constructeur
    public Zombie(int idZombie, String nom, int pointDeVie, double attaqueParSeconde, int degatAttaque, double vitesseDeDeplacement, String cheminImage, int idMap) {
        this.idZombie = idZombie;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.vitesseDeDeplacement = vitesseDeDeplacement;
        this.cheminImage = cheminImage;
        this.idMap = idMap;
    }

    // Getters
    public int getIdZombie() {
        return idZombie;
    }

    public String getNom() {
        return nom;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public double getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    public int getDegatAttaque() {
        return degatAttaque;
    }

    public double getVitesseDeDeplacement() {
        return vitesseDeDeplacement;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public int getIdMap() {
        return idMap;
    }

    // Setters
    public void setIdZombie(int idZombie) {
        this.idZombie = idZombie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public void setAttaqueParSeconde(double attaqueParSeconde) {
        this.attaqueParSeconde = attaqueParSeconde;
    }

    public void setDegatAttaque(int degatAttaque) {
        this.degatAttaque = degatAttaque;
    }

    public void setVitesseDeDeplacement(double vitesseDeDeplacement) {
        this.vitesseDeDeplacement = vitesseDeDeplacement;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }

    // Méthode pour afficher les infos du zombie
    @Override
    public String toString() {
        return "Zombie{" +
                "idZombie=" + idZombie +
                ", nom='" + nom + '\'' +
                ", pointDeVie=" + pointDeVie +
                ", attaqueParSeconde=" + attaqueParSeconde +
                ", degatAttaque=" + degatAttaque +
                ", vitesseDeDeplacement=" + vitesseDeDeplacement +
                ", cheminImage='" + cheminImage + '\'' +
                ", idMap=" + idMap +
                '}';
    }
}

