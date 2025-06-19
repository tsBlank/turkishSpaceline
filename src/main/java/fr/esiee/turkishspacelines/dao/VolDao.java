package fr.esiee.turkishspacelines.dao;

import fr.esiee.turkishspacelines.model.Vol;
import java.util.List;
import java.util.Map;

/**
 * Interface pour l'objet d'accès aux données (DAO) des vols.
 * Définit les méthodes standard pour interagir avec la table 'vol' de la base de données.
 */
public interface VolDao {

    /**
     * Sauvegarde un nouveau vol dans la base de données.
     * @param vol L'objet Vol contenant les informations à sauvegarder.
     * @return true si l'insertion a réussi, false sinon.
     */
    boolean save(Vol vol);

    /**
     * Récupère tous les vols enregistrés dans la base de données.
     * @return Une liste d'objets Vol. La liste sera vide s'il n'y a aucun vol.
     */
    List<Vol> findAll();
    int getTotalFlightCount();
    int getTotalPassengerCount();
    Map<Integer, Integer> getFlightCountByDestination();

    // Vous pourriez ajouter d'autres méthodes ici à l'avenir, par exemple :
    // Optional<Vol> findById(int id);
    // boolean update(Vol vol);
    // boolean delete(int id);
}