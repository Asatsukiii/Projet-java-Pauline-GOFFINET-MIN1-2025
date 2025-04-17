package com.epf.core;

public class Plante {
    private int id_plante;
    private String nom;
    private int point_de_vie;
    private double attaque_par_seconde;
    private int degat_attaque;
    private int cout;
    private double soleil_par_seconde;
    private String effet;
    private String chemin_image;

    //constructeur vide : nécessaire pour la bonne instantiation d'un json en objet Plantes (request post et map)
    public Plante() {

    }

    // Constructeur
    public Plante(int id_plante, String nom, int point_de_vie, double attaque_par_seconde,
                  int degat_attaque, int cout, double soleil_par_seconde, String effet, String chemin_image) {
        // On vérifie d'abord que les point de vie, l'attaque, les degat, le cout et les soleil/seconde ne sont pas négatifs
        // si l'un d'entre eux est négatif génère erreur.
        if (point_de_vie < 0) {
            throw new IllegalArgumentException("Les points de vie ne peuvent pas être négatifs");
        }
        if (attaque_par_seconde < 0) {
            throw new IllegalArgumentException("L'attaque par seconde ne peut pas être négative");
        }
        if (degat_attaque < 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque ne peuvent pas être négatifs");
        }
        if (cout < 0) {
            throw new IllegalArgumentException("Le coût ne peut pas être négatif");
        }
        if (soleil_par_seconde < 0) {
            throw new IllegalArgumentException("Le soleil généré par seconde ne peut pas être négatif");
        }

        this.id_plante = id_plante;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.cout = cout;
        this.soleil_par_seconde = soleil_par_seconde;
        this.effet = effet;
        this.chemin_image = chemin_image;
    }



    // Getters
    public int getId_plante() { return id_plante; }

    public String getNom() { return nom; }

    public int getPoint_de_vie() { return point_de_vie; }

    public double getAttaque_par_seconde() { return attaque_par_seconde; }

    public int getDegat_attaque(){ return degat_attaque; }

    public int getCout() { return cout; }

    public double getSoleil_par_seconde() { return soleil_par_seconde; }

    public String getEffet() { return effet; }

    public String getChemin_image() { return chemin_image; }


    // Setters
    public void setId_plante(int id_plante) { this.id_plante = id_plante; }

    public void setNom(String nom) { this.nom = nom; }

    public void setPoint_de_vie(int point_de_vie) { this.point_de_vie = point_de_vie; }

    public void setAttaque_par_seconde(double attaque_par_seconde) { this.attaque_par_seconde = attaque_par_seconde; }

    public void setDegat_attaque(int degat_attaque) { this.degat_attaque = degat_attaque; }

    public void setCout(int cout) { this.cout = cout; }

    public void setSoleil_par_seconde(double soleil_par_seconde) { this.soleil_par_seconde = soleil_par_seconde; }

    public void setEffet(String effet) { this.effet = effet; }

    public void setChemin_image(String chemin_image) { this.chemin_image = chemin_image; }


    // méthode ToString
    @Override
    public String toString() {
        return "Plante{" +
                "id=" + id_plante +
                ", nom='" + nom + '\'' +
                ", pointDeVie=" + point_de_vie +
                ", attaqueParSeconde=" + attaque_par_seconde +
                ", degatAttaque=" + degat_attaque +
                ", cout=" + cout +
                ", soleilParSeconde=" + soleil_par_seconde +
                ", effet='" + effet + '\'' +
                ", cheminImage='" + chemin_image + '\'' +
                '}';
    }
}
