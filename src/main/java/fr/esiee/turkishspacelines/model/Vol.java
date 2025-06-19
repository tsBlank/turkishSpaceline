package fr.esiee.turkishspacelines.model;

/**
 * Classe modèle représentant un vol.
 * Chaque instance de cette classe correspond à une ligne de la table 'vol'
 * de la base de données. Les attributs de la classe correspondent aux
 * colonnes de la table.
 */
public class Vol {

    // Attributs privés correspondant aux colonnes de la table 'vol'
    private int idVol;
    private String name;
    private int nbFemme;
    private int nbHomme;
    private int nbEnfant;
    private int idStationDepart;
    private int idStationArrivee;
    private int idFusee;

    /**
     * Constructeur par défaut (vide).
     * Il est souvent utile pour les bibliothèques et frameworks qui peuvent
     * avoir besoin de créer des instances de cette classe sans paramètres.
     */
    public Vol() {
    }

    // --- Getters et Setters ---
    // Les getters permettent de lire la valeur d'un attribut.
    // Les setters permettent de modifier la valeur d'un attribut.
    // C'est une convention standard en Java (Encapsulation).
    // Vous pouvez les générer rapidement dans VS Code avec :
    // Clic droit > Source Action... > Generate Getters and Setters...

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbFemme() {
        return nbFemme;
    }

    public void setNbFemme(int nbFemme) {
        this.nbFemme = nbFemme;
    }

    public int getNbHomme() {
        return nbHomme;
    }

    public void setNbHomme(int nbHomme) {
        this.nbHomme = nbHomme;
    }

    public int getNbEnfant() {
        return nbEnfant;
    }

    public void setNbEnfant(int nbEnfant) {
        this.nbEnfant = nbEnfant;
    }

    public int getIdStationDepart() {
        return idStationDepart;
    }

    public void setIdStationDepart(int idStationDepart) {
        this.idStationDepart = idStationDepart;
    }

    public int getIdStationArrivee() {
        return idStationArrivee;
    }

    public void setIdStationArrivee(int idStationArrivee) {
        this.idStationArrivee = idStationArrivee;
    }

    public int getIdFusee() {
        return idFusee;
    }

    public void setIdFusee(int idFusee) {
        this.idFusee = idFusee;
    }

    /**
     * Redéfinition de la méthode toString() pour un affichage lisible de l'objet,
     * très utile pour le débogage (ex: avec System.out.println(monVol);).
     */
    @Override
    public String toString() {
        return "Vol{" +
                "idVol=" + idVol +
                ", name='" + name + '\'' +
                ", nbFemme=" + nbFemme +
                ", nbHomme=" + nbHomme +
                ", nbEnfant=" + nbEnfant +
                ", idStationDepart=" + idStationDepart +
                ", idStationArrivee=" + idStationArrivee +
                ", idFusee=" + idFusee +
                '}';
    }
}