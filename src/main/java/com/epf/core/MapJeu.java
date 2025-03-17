package com.epf.core;

public class MapJeu {
    private int id;
    private int ligne;
    private int colonne;
    private String cheminImage;

    public MapJeu() {
    }

    public MapJeu(int id, int ligne, int colonne, String cheminImage) {
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    @Override
    public String toString() {
        return "MapJeu{" +
                "id=" + id +
                ", ligne=" + ligne +
                ", colonne=" + colonne +
                ", cheminImage='" + cheminImage + '\'' +
                '}';
    }
}
