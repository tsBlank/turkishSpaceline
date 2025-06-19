package fr.esiee.turkishspacelines.controller;

import fr.esiee.turkishspacelines.dao.VolDao;
import fr.esiee.turkishspacelines.dao.VolDaoImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class StatisticsController {

    @FXML private Label totalFlightsLabel;
    @FXML private Label totalPassengersLabel;
    @FXML private BarChart<String, Integer> destinationChart;
    @FXML private Button backButton;

    private final VolDao volDao = new VolDaoImpl();

    /**
     * Méthode d'initialisation, appelée automatiquement par JavaFX.
     * C'est ici qu'on charge et affiche toutes les statistiques.
     */
    @FXML
    public void initialize() {
        loadStatistics();
    }

    private void loadStatistics() {
        // Charger les statistiques globales
        totalFlightsLabel.setText(String.valueOf(volDao.getTotalFlightCount()));
        totalPassengersLabel.setText(String.valueOf(volDao.getTotalPassengerCount()));

        // Charger les données pour le graphique
        Map<Integer, Integer> destinationData = volDao.getFlightCountByDestination();
        
        // Créer une série de données pour le BarChart
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Vols");

        // Remplir la série avec les données de la Map
        for (Map.Entry<Integer, Integer> entry : destinationData.entrySet()) {
            // On affiche "Station " + ID. Pour afficher le nom, il faudrait un StationDao.
            series.getData().add(new XYChart.Data<>("Station " + entry.getKey(), entry.getValue()));
        }

        // Ajouter la série de données au graphique
        destinationChart.getData().add(series);
    }
    
    @FXML
    private void handleBackButton() {
        try {
            // On récupère la fenêtre actuelle pour y charger la vue précédente
            Stage stage = (Stage) backButton.getScene().getWindow();
            // On suppose que l'on revient à OperateurLancement.fxml
            // ATTENTION : Si on revient, il faut repasser l'objet User ! C'est plus complexe.
            // Pour l'instant, on va juste fermer cette fenêtre.
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}