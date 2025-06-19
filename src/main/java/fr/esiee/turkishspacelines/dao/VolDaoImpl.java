package fr.esiee.turkishspacelines.dao;

import fr.esiee.turkishspacelines.model.Vol;
import fr.esiee.turkishspacelines.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implémentation concrète de l'interface VolDao.
 * Contient la logique JDBC pour communiquer avec la base de données.
 */
public class VolDaoImpl implements VolDao {

    @Override
    public boolean save(Vol vol) {
        String sql = "INSERT INTO vol (name, nbFemme, nbHomme, nbEnfant, idStationDepart, idStationArrivee, idFusee) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vol.getName());
            pstmt.setInt(2, vol.getNbFemme());
            pstmt.setInt(3, vol.getNbHomme());
            pstmt.setInt(4, vol.getNbEnfant());
            pstmt.setInt(5, vol.getIdStationDepart());
            pstmt.setInt(6, vol.getIdStationArrivee());
            pstmt.setInt(7, vol.getIdFusee());

            int affectedRows = pstmt.executeUpdate();

            // L'opération a réussi si au moins une ligne a été modifiée
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde du vol : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Vol> findAll() {
        List<Vol> vols = new ArrayList<>();
        String sql = "SELECT * FROM vol ORDER BY idVol DESC"; // Trier par ID décroissant pour voir les plus récents en premier

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // On boucle sur chaque ligne de résultat retournée par la requête
            while (rs.next()) {
                Vol vol = new Vol();
                vol.setIdVol(rs.getInt("idVol"));
                vol.setName(rs.getString("name"));
                vol.setNbFemme(rs.getInt("nbFemme"));
                vol.setNbHomme(rs.getInt("nbHomme"));
                vol.setNbEnfant(rs.getInt("nbEnfant"));
                vol.setIdStationDepart(rs.getInt("idStationDepart"));
                vol.setIdStationArrivee(rs.getInt("idStationArrivee"));
                vol.setIdFusee(rs.getInt("idFusee"));

                // On ajoute l'objet vol créé à notre liste
                vols.add(vol);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la liste des vols : " + e.getMessage());
            e.printStackTrace();
        }
        
        // On retourne la liste (elle sera vide si aucun vol n'a été trouvé ou en cas d'erreur)
        return vols;
    }
    @Override
    public int getTotalFlightCount() {
        String sql = "SELECT COUNT(*) FROM vol";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); // Récupère la valeur de la première colonne (le COUNT)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retourne 0 en cas d'erreur
    }
    @Override
    public int getTotalPassengerCount() {
        // On additionne la somme des trois colonnes de passagers
        String sql = "SELECT SUM(nbFemme + nbHomme + nbEnfant) FROM vol";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Map<Integer, Integer> getFlightCountByDestination() {
        Map<Integer, Integer> destinationCounts = new HashMap<>();
        // On groupe par station d'arrivée et on compte le nombre de vols pour chaque
        String sql = "SELECT idStationArrivee, COUNT(*) as flight_count FROM vol GROUP BY idStationArrivee";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int stationId = rs.getInt("idStationArrivee");
                int count = rs.getInt("flight_count");
                destinationCounts.put(stationId, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinationCounts;
    }
}