package com.epf.core;

public class MapJeu {
    private int id_map;
    private int ligne;
    private int colonne;
    private String chemin_image;

    //constructeur vide : nécessaire pour la bonne instantiation d'un json en objet MapJeu (request post et map)
    public MapJeu() {
    }

    //constructeur
    public MapJeu(int id_map, int ligne, int colonne, String chemin_image) {
        // On vérifie si les chiffres donnés pour les ligne et colonne ne sont pas négatifs.
        // S'ils le sont, lance une erreur
        if (ligne < 0 || colonne < 0) {
            throw new IllegalArgumentException("Les dimensions de la carte ne peuvent pas être négatives");
        }
        this.id_map = id_map;
        this.ligne = ligne;
        this.colonne = colonne;
        this.chemin_image = chemin_image;
    }


    // Getters
    public int getId_map() {
        return id_map;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public String getChemin_image() {
        return chemin_image;
    }

    // Setters
    public void setId_map(int id_map) {
        this.id_map = id_map;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }

    //fonction Tostring pour l'affichage
    @Override
    public String toString() {
        return "MapJeu{" +
                "id=" + id_map +
                ", ligne=" + ligne +
                ", colonne=" + colonne +
                ", cheminImage='" + chemin_image + '\'' +
                '}';
    }
}
